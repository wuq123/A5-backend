package com.softwarecompetition.newdemo.Controller;

import cn.hutool.core.util.StrUtil;
import com.softwarecompetition.newdemo.Pojo.User;
import com.softwarecompetition.newdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import com.softwarecompetition.newdemo.Pojo.Result;

@RestController
public class WebController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUserId())){
            return Result.error("请输入用户名");
        }else if(StrUtil.isBlank(user.getPassword())){
            return Result.error("请输入密码");
        }
        user = userService.login(user);
        return Result.success(user);
    }


}
