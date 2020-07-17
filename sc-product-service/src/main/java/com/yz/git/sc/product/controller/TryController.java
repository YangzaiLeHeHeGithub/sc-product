package com.yz.git.sc.product.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yz.git.sc.product.annotation.ControllerRecord;
import com.yz.git.sc.product.domain.Person;
import com.yz.git.sc.product.util.RedisUtil;
import lombok.javac.apt.Processor;
import org.apache.commons.configuration.ConfigurationMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.BooleanSupplier;

/**
 * @author xuyang
 * @date 2019/08/20
 */
@RestController
@RequestMapping("/product")
public class TryController {

    @Resource
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/msg")
    @ControllerRecord(operationUser = "xy" ,operationType = "1")
    public ResponseEntity<String> tryMsg(){
        redisUtil.set("123","123");
        redisUtil.set("aaa","123");
        redisUtil.set("bbb","123");
        Map<String,String> map = new HashMap<>();

        Person person = new Person(1,"1",12,22,"222",new Date(),new Date());
        map.put("person", JSON.toJSONString(person));

        redisUtil.hset("321","user","zhangsan");
        redisUtil.hset("321","age",11);
        redisUtil.hset("321","shengri",new Date());



        return new ResponseEntity<>("try Msg success083!", HttpStatus.OK);
                
    }


    public <T> List<T> getByPipelined(List<String> keys, Class<T> t){
        List<T> resultList = new ArrayList();
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                keys.forEach(key -> {
                    byte[] bytes = redisConnection.get(key.getBytes());
                    String value = new String(bytes);
                    resultList.add((T) JSON.parseObject(value, t.getClass()));
                });
                return null;
            }
        });
        BooleanSupplier bs = () -> true;
        return resultList;
    }

    public static void main(String[] args) {
        BooleanSupplier bs = () -> true;
        System.out.println(bs.getAsBoolean());
    }



    @GetMapping("/msg/old")
    public String tryMsgOld(){
        Map map = Maps.newHashMap();
        map.getOrDefault("a","default");
        return "try Msg success0888!";
    }
}
