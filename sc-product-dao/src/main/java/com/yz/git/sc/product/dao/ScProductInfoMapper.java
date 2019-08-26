package com.yz.git.sc.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yz.git.sc.product.domain.ScProductInfo;

public interface ScProductInfoMapper extends BaseMapper<ScProductInfo>{
    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(ScProductInfo record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(ScProductInfo record);
}