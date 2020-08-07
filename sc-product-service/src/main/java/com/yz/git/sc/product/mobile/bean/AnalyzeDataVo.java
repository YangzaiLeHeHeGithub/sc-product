package com.yz.git.sc.product.mobile.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 作者: guo yao
 * 日期: 2020年04月26日
 * 时间: 下午13:59
 * @author huyu
 */
@Data
@ToString
public class AnalyzeDataVo {
    private String api;
    private String method;
    private String desc;
    private Double count;
    private String percent;

}
