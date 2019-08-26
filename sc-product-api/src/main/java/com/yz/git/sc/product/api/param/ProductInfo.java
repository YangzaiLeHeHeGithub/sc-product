package com.yz.git.sc.product.api.param;

import lombok.Data;

import java.util.Date;

/**
 * @author xuyang
 * @date 2019/08/23
 */
@Data
public class ProductInfo {
    /**
     *
     * 对应字段 : id
     */
    private Long id;

    /**
     * 产品编号
     * 对应字段 : product_no
     */
    private String productNo;

    /**
     * 产品代码
     * 对应字段 : product_code
     */
    private String productCode;

    /**
     * 厂家代码
     * 对应字段 : product_factory_code
     */
    private String productFactoryCode;

    /**
     * 产品等级 01-食用级别 02-医用级别 03-工厂级别 04-a 05-b
     * 对应字段 : product_grade
     */
    private String productGrade;

    /**
     * 产品标签
     * 对应字段 : product_label
     */
    private String productLabel;

    /**
     * 产品状态
     * 对应字段 : product_status
     */
    private String productStatus;

    /**
     * 厂家状态0-待业 1-开业 2-停业
     * 对应字段 : product_factory_status
     */
    private String productFactoryStatus;

    /**
     * 生产日期
     * 对应字段 : product_creat_date
     */
    private Date productCreatDate;

    /**
     * 过期时间
     * 对应字段 : product_out_date
     */
    private Date productOutDate;

    /**
     * 产品上架时间
     * 对应字段 : releas_release_date
     */
    private Date releasReleaseDate;

    /**
     * 产品下架时间
     * 对应字段 : product_revoke_date
     */
    private Date productRevokeDate;

    /**
     * 上架处理 0-待上架 1-正常上架
     * 对应字段 : release_management
     */
    private String releaseManagement;

    /**
     * 商品排序
     * 对应字段 : product_sort
     */
    private Integer productSort;

    /**
     * 是否爆款 0-是 1-否
     * 对应字段 : is_hot_sale
     */
    private String isHotSale;

    /**
     * 是否删除 0-否 1-是
     * 对应字段 : is_delete
     */
    private String isDelete;

    /**
     * 爆款排序
     * 对应字段 : sale_sort
     */
    private Integer saleSort;

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
