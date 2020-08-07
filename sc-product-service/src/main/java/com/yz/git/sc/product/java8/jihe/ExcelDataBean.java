package com.yz.git.sc.product.java8.jihe;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * ExcelDataBean
 *
 * @author xuyang
 * @version 1.0
 * @description Excel数据导出实体
 * @date 2020/8/4 14:52
 */
@Data
@ExcelTarget("excelDataBean")
@Builder
public class ExcelDataBean implements java.io.Serializable{
    /**
     * id
     */
    private String        id;

    @Excel(name = "请求链接",orderNum="1",width = 100)
    private String requestUrl;

    @Excel(name = "请求方式",orderNum="2",width = 30)
    private String requestMethod;

    @Excel(name = "功能说明",orderNum="3",width = 30)
    private String functionDesc;

    @Excel(name = "请求次数",orderNum="4",width = 30)
    private int count;

    @Excel(name = "百分比",orderNum="5",width = 30)
    private BigDecimal percent;

}
 