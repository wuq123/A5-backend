package com.softwarecompetition.newdemo.Controller;

import com.softwarecompetition.newdemo.Pojo.User;
import com.softwarecompetition.newdemo.Pojo.UserCheckPsw;
import com.softwarecompetition.newdemo.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.softwarecompetition.newdemo.Pojo.Result;


import java.sql.SQLException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    //新增用户信息
    @PostMapping("/add")
    public Result add(@RequestBody UserCheckPsw userCheckPsw){
        User user = new User();

        //检查密码是否相同
        if(userCheckPsw.getPassword().equals(userCheckPsw.getCheckPassword())) {
            user.setUserId(userCheckPsw.getUserId());
            user.setPassword(userCheckPsw.getPassword());
            try {
                userService.insertUser(user);
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    return Result.error("插入数据库错误");
                } else {
                    return Result.error("系统错误");
                }
            }
            return Result.success();
        }else{
            return Result.error("密码不一致");
        }
    }

    //改
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        User newUser;
        try{
            newUser = userService.updateUser(user);
        }catch(Exception e){
            if(e instanceof SQLException){
                return Result.error("修改数据库错误");
            }else{
                return Result.error("系统错误");
            }
        }
        return Result.success(newUser);
    }

    //删
    @DeleteMapping("/delete/{userId}")
    public Result delete(@PathVariable String userId){
        try{
            userService.deleteUser(userId);
        }catch(Exception e){
            if(e instanceof SQLException){
                return Result.error("删除数据错误");
            }else{
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    //批量删除
     @DeleteMapping("/delete/batch")
        public Result delete(@RequestBody List<String> userIds){
            try{
                userService.batchDeleteUser(userIds);
            }catch(Exception e){
                if(e instanceof SQLException){
                    return Result.error("删除数据错误");
                }else{
                    return Result.error("系统错误");
                }
            }
            return Result.success();
        }

        //查询全部用户
    @GetMapping("/selectAll")
        public Result SelectAll(){
        List<User> userList = userService.selectAll();
        return Result.success(userList);
    }

    //通过id查询用户
    @GetMapping("/selectById/{userId}")
    public Result SelectById(@PathVariable String userId){
        User user = userService.selectById(userId);
        return Result.success(user);
    }

}
