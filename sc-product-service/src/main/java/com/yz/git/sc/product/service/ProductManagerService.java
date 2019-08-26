package com.yz.git.sc.product.service;

import com.yz.git.sc.product.api.param.ProductInfo;

/**
 * @author xuyang
 * @date 2019/08/26
 */
public interface ProductManagerService {

    /**
     * ProductInfoDO
     * 存储 产品信息
     * @param productInfo
     * @return Boolean
     */
    Boolean saveProductInfo(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 爆款推荐管理 更新爆款产品排序信息
     * @param productInfoDO
     * @return Boolean
     */
    Boolean updateHotSaleSort(ProductInfo productInfoDO);
    /**
     * productInfoDO
     * 上架产品操作
     * @param productInfo
     * @return Boolean
     */
    Boolean releaseProduct(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 下架产品操作
     * @param productInfo
     * @return Boolean
     */
    Boolean revokeProduct(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 取消预约产品操作
     * @param productInfo
     * @return Boolean
     */
    Boolean cancelOrderProduct(ProductInfo productInfo);
    /**
     * dProductInfoDO
     * 修改产品操作
     * @param productInfo
     * @return Boolean
     */
    Boolean modifyProductInfo(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 删除产品操作
     * @param productInfo
     * @return Boolean
     */
    Boolean deleteProductInfo(ProductInfo productInfo);
}
