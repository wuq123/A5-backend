package com.softwarecompetition.newdemo.Pojo;
import lombok.Data;
@Data
public class User {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String sex;
    private int isAdmin;
}
