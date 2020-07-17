package com.yz.git.sc.product.service;

import com.alibaba.fastjson.JSONObject;
import com.yz.git.sc.product.controller.MemoryReadController;
import com.yz.git.sc.product.domain.MemoryReadParam;
import com.yz.git.sc.product.filter.GlobalControllerAdvice;
import com.yz.git.sc.product.util.RedisUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MemoryReadControllerTest
 *
 * @author xuyang
 * @version 1.0
 * @description MockTest
 * @date 2020/7/14 20:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = MemoryReadController.class,
        properties = {
                "spring.cloud.config.enabled: false"
        }
)
public class MemoryReadControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    MemoryReadController controller;
    @MockBean
    MemoryReadParam memoryReadParam;
    @MockBean
    RedisTemplate<String, Object> redisTemplate;
    @MockBean
    RedisUtil redisUtil;

    @Before
    public void go() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalControllerAdvice())
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @Test
    public void mockDemo() {
        ArrayList arrayListMock = Mockito.mock(ArrayList.class);
        Assert.assertTrue(arrayListMock instanceof List);
        Assert.assertTrue(arrayListMock instanceof ArrayList);
        Mockito.when(arrayListMock.add("1")).thenReturn(true);
        Mockito.when(arrayListMock.size()).thenReturn(2);
        Assert.assertFalse(arrayListMock.add("2"));
        Assert.assertTrue(arrayListMock.add("1"));
        Assert.assertEquals(arrayListMock.size(), 2);
        Iterator iterator = Mockito.mock(Iterator.class);
        Mockito.when(iterator.next()).thenReturn("1").thenReturn("2");
        String result = iterator.next() + "" + iterator.next();
        Assert.assertTrue(result.equals("12"));
        Mockito.doThrow(new NoSuchElementException()).when(iterator.next());
        iterator.next();


    }

    @Test
    public void read() throws Exception {
        JSONObject result = new JSONObject();
        result.put("message", "记忆阅览存储成功");
        Mockito.doNothing().when(controller).storeReadMode(anyString(), anyString(), any(MemoryReadParam.class));
        MemoryReadParam param = MemoryReadParam.builder().content("12312312").materialId("222222").build();
        this.mockMvc.perform(post("/rest/v4beta2/memory_read/{userId}/{dossierId}", "123", "qwe").contentType(MediaType.APPLICATION_JSON_UTF8).content(JSONObject.toJSONString(param))).andExpect(status().isBadRequest());

    }

    @Test
    public void storeMemoryReadInfo() throws Exception {
        MemoryReadParam memoryReadParam = new MemoryReadParam();
        Mockito.when(MemoryReadParam.builder().build()).thenReturn(memoryReadParam);
        this.mockMvc.perform(post("/rest/v4beta2/memory_read/123/qqq")).andExpect(status().isBadRequest());
    }

}
 