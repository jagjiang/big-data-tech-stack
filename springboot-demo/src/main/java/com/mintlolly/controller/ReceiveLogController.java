package com.mintlolly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2021/11/21
 *
 * @author jiangbo
 * Description:
 */
@RestController
public class ReceiveLogController {

    @RequestMapping("applog")
    public String applog(@RequestParam("param") String log){
        System.out.println(log);
        return "success";
    }
}
