package com.yz.git.sc.product.controller;

import com.yz.git.sc.product.domain.ScProductInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyang
 * @date 2019/08/20
 */
@RestController
@RequestMapping("/product")
public class TryController {

    @GetMapping("/msg")
    public String tryMsg(){
        return "try Msg success083!";
    }

    @GetMapping("/pathv/{path}")
    public String tryPath(@PathVariable("path")String path){
        String path1 = path;
        return path1;
    }

    @PostMapping("/request/{path}/{name}/{age}")
    public String tryRequest(@RequestParam("path")String path,@RequestParam("name")String name,@RequestParam("path")Integer age){
        String path1 = path;
        String name1 = name;
        Integer age1 = age;
        return path1;
    }
    @PostMapping("/rb")
    public ScProductInfo tryRequestBody(@RequestBody ScProductInfo sc){
        ScProductInfo sc1 = sc;
        return sc1;
    }
}
