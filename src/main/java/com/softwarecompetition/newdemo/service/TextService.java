package com.softwarecompetition.newdemo.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwarecompetition.newdemo.Pojo.Course;
import com.softwarecompetition.newdemo.Pojo.Text;
import com.softwarecompetition.newdemo.Pojo.Regex;
import com.softwarecompetition.newdemo.Pojo.UserCourse;
import com.softwarecompetition.newdemo.mapper.CourseMapper;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextService {
    @Resource
    CourseMapper courseMapper;
    public String extractText(Text text){

        String fullText = text.getTextContent();
        List<String> urls = Regex.extractURLs(fullText);
        List<String> courses = Regex.extractCourseName(fullText);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < urls.size(); i++){
            sb.append(courses.get(i));
            sb.append("：");
            sb.append(urls.get(i));
            if (i < urls.size() - 1) {
                sb.append("\n");
            }
        }
        String courseText = sb.toString();
        return courseText;
    }

    public Course courseTree(@NotNull Text text){//将课程填入树形结构json
        List<String> urls = Regex.extractURLs(text.getTextContent());//存储url
        List<String> courses = Regex.extractCourseName(text.getTextContent());//存储课程名称
        List<Course> courseList = new ArrayList<>();//用一个数组存储七个节点
        List<Course> children = new ArrayList<>();//子节点
        List<Course> grandchildren = new ArrayList<>();//孙节点

        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        //以上将课程填入对应树形结构
        courseList.add(new Course(urls.get(0), courses.get(0), children));
        Course father = courseList.get(0);
        courseList.add(new Course(urls.get(1), courses.get(1), null));
        children.add(courseList.get(1));
        courseList.add(new Course(urls.get(2), courses.get(2), grandchildren));
        children.add(courseList.get(2));
        for(int i = 3; i < 6; i++){
            courseList.add(new Course(urls.get(i), courses.get(i), null));
            grandchildren.add(courseList.get(i));
        }
        courseList.add(new Course(urls.get(6), courses.get(6), null));
        children.add(courseList.get(6));

        //将json数据存入表中
        try {
            json = mapper.writeValueAsString(father);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        courseMapper.updateDialogueTree(text.getUserId(), text.getDialogueId(), json);
        for(Course course: courseList){
            if (CollectionUtil.isEmpty(courseMapper.selectCourseByName(course.getName()))){
                courseMapper.insert(course.getName(),course.getUrl(),text.getUserId());
            }
        }
        return courseList.get(0);
    }

    public List<UserCourse> selectUserCourse(String userId){
        return courseMapper.selectUserCourse(userId);
    }
}
