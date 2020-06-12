package com.yz.git.sc.product.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>@ClassName Person<p>
 * <p>@Description Person<p>
 * <p>@Author xuYang<p>
 * <p>@Date 2020/6/29 13:59<p>
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@Builder
@Accessors(chain = true)
public class Person implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private Integer weight;

    private String descp;
    /**
     * 创建时间
     * 对应字段 : create_date
     */
    private Date createDate;

    /**
     * 更新时间
     * 对应字段 : update_date
     */
    private Date updateDate;


}
