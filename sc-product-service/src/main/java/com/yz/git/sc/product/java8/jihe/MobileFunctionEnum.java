package com.yz.git.sc.product.java8.jihe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * MobileFunctionEnum
 *
 * @author xuyang
 * @version 1.0
 * @description 移动阅卷功能枚举
 * @date 2020/8/4 16:36
 */
@Getter
@ToString
@AllArgsConstructor
public enum MobileFunctionEnum {
    GET_CONFIG("GET","/rest/v4/config/configvalue","配置数据获取接口"),
    GET_MEMORY("GET","/rest/v4beta2/memory_read","记忆阅览读取接口"),
    POST_MEMORY("POST","/rest/v4beta2/memory_read","记忆阅览存储接口"),
    POST_COMPARE("POST","/rest/v4beta2/compare","向对比池添加材料接口"),
    DELETE_COMPARE("DELETE","/rest/v4beta2/compare","删除对比材料"),
    PATCH_COMPARE("PATCH","/rest/v4beta2/compare","更新对比材料的左右屏顺序"),
    GET_COMPARE("GET","/rest/v4beta2/compare","获取当前案件对比池中的所有材料");

    private String requestMethod;
    private String requestUrl;
    private String functionDesc;

}