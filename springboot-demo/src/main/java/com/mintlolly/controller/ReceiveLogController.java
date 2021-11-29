package com.mintlolly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("applog")
    public String applog(@RequestParam("param") String log){
        //写入 Kafka
        kafkaTemplate.send("ods_base_log", log);
        return "success";
    }
}
