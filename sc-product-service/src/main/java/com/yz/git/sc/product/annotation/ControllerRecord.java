package com.yz.git.sc.product.annotation;

import java.lang.annotation.*;

/**
 * @ClassName ControllerRecord
 * @Description 控制器操作记录类
 * @Author xuYang
 * @Date 2020/6/12 16:23
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerRecord {

    String operationUser();

    String operationType();

}
