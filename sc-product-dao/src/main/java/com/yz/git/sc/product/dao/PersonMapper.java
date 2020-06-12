package com.yz.git.sc.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yz.git.sc.product.domain.Person;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 *
 */
public interface PersonMapper extends BaseMapper<Person> {
    /**
     * 插入数据库记录
     *
     * @param person
     */
    int insert(Person person);

    /**
     * 插入数据库记录
     *
     * @param person
     */
    int insertSelective(Person person);


}
