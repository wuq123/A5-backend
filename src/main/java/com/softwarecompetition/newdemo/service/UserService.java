package com.softwarecompetition.newdemo.service;

import com.softwarecompetition.newdemo.Pojo.User;
import com.softwarecompetition.newdemo.Pojo.UserCheckPsw;
import com.softwarecompetition.newdemo.exception.ServiceException;
import com.softwarecompetition.newdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    //增
    public void insertUser(User user){
        userMapper.insert(user);
    }

    //验证账号是否合法
    public User login(User user){
        User dbUser = userMapper.selectByUserId(user.getUserId());
        if(dbUser == null){
            throw new ServiceException("用户名不存在");
        }
        if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("密码错误");
        }
        return dbUser;
    }

    //改
    public User updateUser(User user) {
        userMapper.update(user);
        return userMapper.selectByUserId(user.getUserId());
    }

    //删
    public void deleteUser(String userId){
        userMapper.delete(userId);
    }

    public void batchDeleteUser(List<String> userIds) {
        for (String userId : userIds){
            userMapper.delete(userId);
        }
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectById(String userId) {
        return userMapper.selectByUserId(userId);
    }
}
