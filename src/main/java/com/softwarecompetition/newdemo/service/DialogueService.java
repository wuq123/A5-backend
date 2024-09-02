package com.softwarecompetition.newdemo.service;

import com.softwarecompetition.newdemo.Pojo.Dialogue;
import com.softwarecompetition.newdemo.mapper.DialogueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DialogueService {
    @Autowired
    DialogueMapper dialogueMapper;

    String url = "jdbc:mysql://localhost:3306/db1";
    String username = "root";
    String password = "root";

    //增
    public int insertDialogue(String userId, String dialogueName){
//        try (Connection conn = DriverManager.getConnection(url, username, password);
//        CallableStatement cs = conn.prepareCall("{call InsertIntoDialogueAndUserDialogue())){
        dialogueMapper.insert(userId, dialogueName);
        return dialogueMapper.selectNewDialogueId();
    }

    //删除单个对话
    public void deleteDialogue(int dialogueId) {
        dialogueMapper.delete(dialogueId);
    }

    //清空用户对话记录
    public void batchDeleteDialogueByUserId(String userId) {
        dialogueMapper.deleteAll(userId);
    }

    public List<Dialogue> selectByUserId(String userId) {
        return dialogueMapper.selectByUserId(userId);
    }

    public List<Dialogue> selectAll() {
        return dialogueMapper.selectAll();
    }
}
