package com.yz.git.sc.product.controller;

import com.google.common.collect.Maps;
import com.yz.git.sc.product.annotation.ControllerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xuyang
 * @date 2019/08/20
 */
@RestController
@RequestMapping("/product")
public class TryController {

    @GetMapping("/msg")
    @ControllerRecord(operationUser = "xy" ,operationType = "1")
    public ResponseEntity<String> tryMsg(){
        return new ResponseEntity<>("try Msg success083!", HttpStatus.OK);
                
    }



    @GetMapping("/msg/old")
    public String tryMsgOld(){
        Map map = Maps.newHashMap();
        map.getOrDefault("a","default");
        return "try Msg success0888!";
    }
}
