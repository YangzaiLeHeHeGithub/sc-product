package com.yz.git.sc.product.domain;

import java.util.Date;

public class ScProductInfo {
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

    /**
     * get method 
     *
     * @return sc_product_info.id：
     */
    public Long getId() {
        return id;
    }

    /**
     * set method 
     *
     * @param id  
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_no：产品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * set method 
     *
     * @param productNo  产品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_code：产品代码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * set method 
     *
     * @param productCode  产品代码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_factory_code：厂家代码
     */
    public String getProductFactoryCode() {
        return productFactoryCode;
    }

    /**
     * set method 
     *
     * @param productFactoryCode  厂家代码
     */
    public void setProductFactoryCode(String productFactoryCode) {
        this.productFactoryCode = productFactoryCode == null ? null : productFactoryCode.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_grade：产品等级 01-食用级别 02-医用级别 03-工厂级别 04-a 05-b
     */
    public String getProductGrade() {
        return productGrade;
    }

    /**
     * set method 
     *
     * @param productGrade  产品等级 01-食用级别 02-医用级别 03-工厂级别 04-a 05-b
     */
    public void setProductGrade(String productGrade) {
        this.productGrade = productGrade == null ? null : productGrade.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_label：产品标签
     */
    public String getProductLabel() {
        return productLabel;
    }

    /**
     * set method 
     *
     * @param productLabel  产品标签
     */
    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel == null ? null : productLabel.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_status：产品状态
     */
    public String getProductStatus() {
        return productStatus;
    }

    /**
     * set method 
     *
     * @param productStatus  产品状态
     */
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus == null ? null : productStatus.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_factory_status：厂家状态0-待业 1-开业 2-停业
     */
    public String getProductFactoryStatus() {
        return productFactoryStatus;
    }

    /**
     * set method 
     *
     * @param productFactoryStatus  厂家状态0-待业 1-开业 2-停业
     */
    public void setProductFactoryStatus(String productFactoryStatus) {
        this.productFactoryStatus = productFactoryStatus == null ? null : productFactoryStatus.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_creat_date：生产日期
     */
    public Date getProductCreatDate() {
        return productCreatDate;
    }

    /**
     * set method 
     *
     * @param productCreatDate  生产日期
     */
    public void setProductCreatDate(Date productCreatDate) {
        this.productCreatDate = productCreatDate;
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_out_date：过期时间
     */
    public Date getProductOutDate() {
        return productOutDate;
    }

    /**
     * set method 
     *
     * @param productOutDate  过期时间
     */
    public void setProductOutDate(Date productOutDate) {
        this.productOutDate = productOutDate;
    }

    /**
     * get method 
     *
     * @return sc_product_info.releas_release_date：产品上架时间
     */
    public Date getReleasReleaseDate() {
        return releasReleaseDate;
    }

    /**
     * set method 
     *
     * @param releasReleaseDate  产品上架时间
     */
    public void setReleasReleaseDate(Date releasReleaseDate) {
        this.releasReleaseDate = releasReleaseDate;
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_revoke_date：产品下架时间
     */
    public Date getProductRevokeDate() {
        return productRevokeDate;
    }

    /**
     * set method 
     *
     * @param productRevokeDate  产品下架时间
     */
    public void setProductRevokeDate(Date productRevokeDate) {
        this.productRevokeDate = productRevokeDate;
    }

    /**
     * get method 
     *
     * @return sc_product_info.release_management：上架处理 0-待上架 1-正常上架
     */
    public String getReleaseManagement() {
        return releaseManagement;
    }

    /**
     * set method 
     *
     * @param releaseManagement  上架处理 0-待上架 1-正常上架
     */
    public void setReleaseManagement(String releaseManagement) {
        this.releaseManagement = releaseManagement == null ? null : releaseManagement.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.product_sort：商品排序
     */
    public Integer getProductSort() {
        return productSort;
    }

    /**
     * set method 
     *
     * @param productSort  商品排序
     */
    public void setProductSort(Integer productSort) {
        this.productSort = productSort;
    }

    /**
     * get method 
     *
     * @return sc_product_info.is_hot_sale：是否爆款 0-是 1-否
     */
    public String getIsHotSale() {
        return isHotSale;
    }

    /**
     * set method 
     *
     * @param isHotSale  是否爆款 0-是 1-否
     */
    public void setIsHotSale(String isHotSale) {
        this.isHotSale = isHotSale == null ? null : isHotSale.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.is_delete：是否删除 0-否 1-是
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * set method 
     *
     * @param isDelete  是否删除 0-否 1-是
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * get method 
     *
     * @return sc_product_info.sale_sort：爆款排序
     */
    public Integer getSaleSort() {
        return saleSort;
    }

    /**
     * set method 
     *
     * @param saleSort  爆款排序
     */
    public void setSaleSort(Integer saleSort) {
        this.saleSort = saleSort;
    }

    /**
     * get method 
     *
     * @return sc_product_info.create_date：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * set method 
     *
     * @param createDate  创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * get method 
     *
     * @return sc_product_info.update_date：更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * set method 
     *
     * @param updateDate  更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}