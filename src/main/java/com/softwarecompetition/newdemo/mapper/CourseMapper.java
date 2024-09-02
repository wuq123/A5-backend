package com.softwarecompetition.newdemo.mapper;

import com.softwarecompetition.newdemo.Pojo.Course;
import com.softwarecompetition.newdemo.Pojo.User;
import com.softwarecompetition.newdemo.Pojo.UserCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert("insert into course(courseName, courseURL, userId) " +
            "values (#{name},#{url},#{userId})")
    void insert(String name, String url, String userId);

    @Update("update dialogue set jsonData =  #{jsonData} " +
            "where dialogueId =  #{dialogueId} and userId = #{userId}")
    void updateDialogueTree(String userId, String dialogueId, String jsonData);

    @Select("select courseName, courseURL from course where userId = #{userId}")
    List<UserCourse> selectUserCourse(String userId);

    @Select("select jsonData from dialogue where dialogueId = #{dialogueId}")
    String selectDialogueTree(String dialogueId);
    @Select("select * from course where courseName = #{courseName}")
    List<UserCourse> selectCourseByName(String courseName);
}
