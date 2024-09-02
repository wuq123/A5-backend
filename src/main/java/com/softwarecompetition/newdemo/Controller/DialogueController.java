package com.softwarecompetition.newdemo.Controller;

import com.softwarecompetition.newdemo.Pojo.Dialogue;
import com.softwarecompetition.newdemo.Pojo.Result;
import com.softwarecompetition.newdemo.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/dialogue")
public class DialogueController {

    @Autowired
    DialogueService dialogueService;

    //新增对话
    @PostMapping("/add/{userId}")
    public Result add(@RequestBody Dialogue dialogue, @PathVariable String userId){
        int dialogueId;
        try {
            dialogueId = dialogueService.insertDialogue(userId, dialogue.getDialogueName());
        } catch (Exception e) {
            if (e instanceof SQLException) {
                return Result.error("插入数据库错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(dialogueId);
    }

    //删除单个对话
    @DeleteMapping("/delete/{dialogueId}")
    public Result delete(@PathVariable int dialogueId){
        try{
            dialogueService.deleteDialogue(dialogueId);
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
    @DeleteMapping("/delete/batch/{userId}")
    public Result delete(@PathVariable String userId){
        try{
            dialogueService.batchDeleteDialogueByUserId(userId);
        }catch(Exception e){
            if(e instanceof SQLException){
                return Result.error("删除数据错误");
            }else{
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    //查询当前用户的所有对话
    @GetMapping("/select/{userId}")
    public Result Select(@PathVariable String userId){
        List<Dialogue> dialogueList;
        try {
            dialogueList = dialogueService.selectByUserId(userId);
        }catch (Exception e){
            if(e instanceof SQLException){
                return Result.error("删除数据错误");
            }else{
                return Result.error("系统错误");
            }
        }
        return Result.success(dialogueList);
    }

    //查询所有对话
    @GetMapping("/selectAll")
    public Result SelectAll(){
        List<Dialogue> dialogueList;
        try {
            dialogueList = dialogueService.selectAll();
        }catch (Exception e){
            if(e instanceof SQLException){
                return Result.error("删除数据错误");
            }else{
                return Result.error("系统错误");
            }
        }
        return Result.success(dialogueList);
    }

}
