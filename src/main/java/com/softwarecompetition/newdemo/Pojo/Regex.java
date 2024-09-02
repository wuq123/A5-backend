package com.softwarecompetition.newdemo.Pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Regex {
    static String urlRegex = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))";
    //static String courseNameRegex = "课程名称：([^\\n]*)";
    static String courseNameRegex = "课程名称：(《)*([^<*》]*)";
    private static final Pattern URL_PATTERN = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
    private static final Pattern COURSENAME_PATTERN = Pattern.compile(courseNameRegex, Pattern.CASE_INSENSITIVE);

    public static List<String> extractURLs(String text) {
        List<String> urls = new ArrayList<>();
        Matcher matcher = URL_PATTERN.matcher(text);
        while (matcher.find()) {
            String url = matcher.group();
            urls.add(url);
        }
        return urls;
    }
    public static List<String> extractCourseName(String text) {
        List<String> courses = new ArrayList<>();
        Matcher matcher = COURSENAME_PATTERN.matcher(text);
        while (matcher.find()) {
            String course = matcher.group(2);
            courses.add(course);
        }
        return courses;
    }
}
