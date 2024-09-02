package com.softwarecompetition.newdemo.Controller;

import com.softwarecompetition.newdemo.Pojo.*;
import com.softwarecompetition.newdemo.service.TextService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/text")
public class TextController {
    @Resource
    TextService textService;

    @PostMapping
    public Result extractText(@RequestBody Text text){
        String courseText = textService.extractText(text);
        return Result.success(courseText);
    }

    @PostMapping("/tree")
    public Result extractCourse(@RequestBody Text text){
        Course course = textService.courseTree(text);
        return Result.success(course);
    }

//    @GetMapping("/tree/{userId}")
//    public Result selectUserTree(@PathVariable String userId){
//        List<String> treeList = textService.selectUserTree(userId);
//        return Result.success(treeList);
//    }

    @GetMapping("/{userId}")
    public Result selectUserCourse(@PathVariable String userId){
        List<UserCourse> courseList = textService.selectUserCourse(userId);
        return Result.success(courseList);
    }
}
