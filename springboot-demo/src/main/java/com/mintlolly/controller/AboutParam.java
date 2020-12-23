package com.mintlolly.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mintlolly.model.entity.Student;
import com.mintlolly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Create by on jiangbo 2020/9/11 9:34
 * <p>
 * Description:关于传参的用法
 * 请求路径传参 ：
 *  @PathVariable 获取路径参数。即url/{id}这种形式
 *  @RequestParam 获取查询参数。即url?name= 这种形式
 *
 * body参数
 */
@RestController
public class AboutParam {

    @Autowired
    private StudentService studentService;

    @GetMapping("/demo/{id}")
    public String demo(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name){
        return "id = " + id +" name = " + name;
    }

    @RequestMapping("/userList")
    public List<Student> userList() {
        List<Student> userList = studentService.getStudentList();
        return userList;
    }

    @RequestMapping("/demo/save")
    public String save(@RequestBody Student student){
        studentService.save(student);
        return "success";
    }

    @RequestMapping("/demo/test")
    public String test(@RequestBody JSONObject student){

        JSONObject.parseObject(student.toJSONString(),Student.class);


        return  student.get("sName").toString();
    }
}
