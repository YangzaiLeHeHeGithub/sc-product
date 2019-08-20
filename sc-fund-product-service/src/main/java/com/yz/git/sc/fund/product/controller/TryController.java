package com.yz.git.sc.fund.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuyang
 * @date 2019/08/20
 */
@RestController
@RequestMapping("/product")
public class TryController {

    @GetMapping("/msg")
    public String tryMsg(){
        return "try Msg success!";
    }
}
