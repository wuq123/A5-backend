package com.softwarecompetition.newdemo.Pojo;

import lombok.Data;
import java.util.*;

@Data
public class Course {
    private String name;
    private String url;
    private List<Course> children;

    public Course(String url, String name, List<Course> children) {
        this.name = name;
        this.url = url;
        this.children = children;
    }
}
