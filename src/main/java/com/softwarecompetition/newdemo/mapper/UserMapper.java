package com.softwarecompetition.newdemo.mapper;
import com.softwarecompetition.newdemo.Pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    //全查
    List<User> list();

    @Insert("insert into user(userId, password) " +
            "values (#{userId},#{password})")
    void insert(User user);

    @Update("update user set password =  #{password}, userName = #{userName}, email = #{email}, phone = #{phone}, sex = #{sex}, isAdmin = #{isAdmin} " +
            "where userId = #{userId} ")
    void update(User user);

    @Delete("delete from user where userId = #{userId}")
    void delete(String userId);

    @Select("select * from user where userId = #{userId}")
    User selectByUserId(String userId);

    @Select("select * from user order by userId desc")
    List<User> selectAll();
}
