package com.yz.git.sc.product.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * MemoryReadParam
 *
 * @author xuyang
 * @version 1.0
 * @description 记忆阅览参数类
 * @date 2020/7/13 12:38
 */
@Builder
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MemoryReadParam {

    /**
     * 材料Id
     */
    private String materialId;
    /**
     * 记忆阅览数据串
     */
    private String content;

}
 