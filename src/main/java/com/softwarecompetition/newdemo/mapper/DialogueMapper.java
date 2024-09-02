package com.softwarecompetition.newdemo.mapper;

import com.softwarecompetition.newdemo.Pojo.Dialogue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DialogueMapper {
    @Select("select * from record")
        //全查
    List<Record> list();

    @Insert("insert into dialogue(userId,dialogueName) values (#{userId},#{dialogueName})")
    void insert(String userId, String dialogueName);

    @Delete("delete from dialogue where dialogueId = #{dialogueId}")
    void delete(int dialogueId);

    @Delete("delete from dialogue where userId = #{userId}" )
    void deleteAll(String userId);

    @Select("select dialogueId, dialogueName, jsonData from dialogue where userId = #{userId} order by dialogueId desc")
    List<Dialogue> selectByUserId(String userId);

    @Select("select dialogueId from dialogue order by dialogueId desc")
    List<Dialogue> selectAll();

    @Select("select dialogueId from dialogue order by dialogueId desc limit 1")
    int selectNewDialogueId();
}

