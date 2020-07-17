package com.yz.git.sc.product.controller;

import com.yz.git.sc.product.domain.Person;
import com.yz.git.sc.product.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>@ClassName PersonController<p>
 * <p>@Description Person<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/29 14:13<p>
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @PostMapping
    public ResponseEntity<String>  create(@RequestBody Person person){
       if(null == person.getId()){
           ResponseEntity.status(HttpStatus.NOT_EXTENDED);
       }
       Person.builder().createDate(new Date()).updateDate(new Date()).build();
       int result = personService.insert(person).get();
       if(result == 0){
           return ResponseEntity.ok("Fail");
       }
        return ResponseEntity.ok("SUCCESS");

    }
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") Integer id){
        if(null == id){
            ResponseEntity.status(HttpStatus.NOT_EXTENDED);
        }
        personService.delete(id);
        return  ResponseEntity.ok("DELETE  SUCCESS");
    }



}
