package com.yz.git.sc.product.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yz.git.sc.product.domain.MemoryReadParam;
import com.yz.git.sc.product.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * MemoryReadController
 * 记忆阅览
 *
 * @author xuyang
 * @version 1.0
 * @description
 * @date 2020/7/13 11:32
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest/v4beta2/memory_read")
public class MemoryReadController {
    @Autowired
    RedisUtil redisUtil;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 记忆阅览存储接口
     *
     * @param userId    用户编号
     * @param dossierId 卷宗ID
     * @param data
     * @return
     */
    @PostMapping(value = "{userId}/{dossierId}")
    @ApiOperation(value = "移动阅卷记忆阅览功能-存储接口", notes = "用户阅卷停留2秒【根据配置】以上调用，存储页码和阅卷时间；key：userId+dossierId")
    public ResponseEntity<String> storeReadMode(
            @PathVariable("userId") @ApiParam(value = "用户编号.eg:123123") String userId,
            @PathVariable("dossierId") @ApiParam(value = "卷宗ID.eg:34A236DF8233468F93D8627318331A0F") String dossierId,
            @RequestBody() MemoryReadParam data) {
        log.debug("移动阅卷记忆阅览存储, 用户编号:{},卷宗ID:{}, 数据:{}", userId, dossierId, data);
        if (StringUtils.isEmpty(data.getContent())) {
            throw new IllegalArgumentException("content 存储数据为空！");
        }
        try {
            redisTemplate.opsForValue().set(userId + dossierId, JSON.toJSONString(data));
        } catch (Exception e) {
            log.error("移动阅卷记忆阅览存入redis出错！", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("记忆阅览存储失败");
        }
        JSONObject result = new JSONObject();
        result.put("message", "记忆阅览存储成功");
        return ResponseEntity.ok(result.toString());
    }

    /**
     * 记忆阅览读取接口
     *
     * @param userId 用户id
     * @return 阅览模式数据（json）
     */
    @GetMapping(value = "{userId}/{dossierId}")
    @ApiOperation(value = "记忆阅览读取", notes = "根据key：userId+dossierId 读取记忆阅览数据")
    public ResponseEntity<MemoryReadParam> recoverReadMode(
            @PathVariable("userId") @ApiParam(value = "用户编号.eg:123123") String userId,
            @PathVariable("dossierId") @ApiParam(value = "卷宗ID.eg:34A236DF8233468F93D8627318331A0F") String dossierId) {
        log.debug("记忆阅览数据读取, 用户编号:{}，卷宗ID:{}", userId, dossierId);
        //key
        String memoryKey = userId + dossierId;
        MemoryReadParam data = MemoryReadParam.builder().build();
//        MemoryReadParam da = new MemoryReadParam();
        if (redisTemplate.hasKey(memoryKey)) {
            //jsonStr
            String result = redisTemplate.opsForValue().get(memoryKey).toString();
            data = JSON.parseObject(result, MemoryReadParam.class);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    public static void main(String[] args) {

    }
}
 