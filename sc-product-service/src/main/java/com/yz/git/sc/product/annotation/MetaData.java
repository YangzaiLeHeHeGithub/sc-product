package com.yz.git.sc.product.annotation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>@ClassName MetaData<p>
 * <p>@Description shuju<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/12 16:55<p>
 */
@Setter
@Getter
@ToString
public class MetaData {
    /**
     * 是否需要记录日志
     */
    private boolean needRecord = false;
    private String operationUser = "default";
    private String operationType = "default";
    /**
     * 获取监控的方法
     */
    private String clazz;

    /**
     * 获取监控的方法名称
     */
    private String methodName;
    /**
     * 操作日志注解
     */
    private ControllerRecord controllerRecord;
    /**
     * 获取开始时间
     */
    private long beginTime;
    /**
     * 构造方法
     */
    public MetaData() {
        this.beginTime = System.currentTimeMillis();
    }
}
