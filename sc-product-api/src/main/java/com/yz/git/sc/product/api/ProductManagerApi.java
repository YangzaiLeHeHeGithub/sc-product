package com.yz.git.sc.product.api;


import com.yz.git.sc.product.api.param.ProductInfo;
import com.yz.git.sc.product.common.message.Message;

import java.util.List;

/**
 * @Interface ProductManagerApi
 * @Description 对于产品管理部分提供接口服务
 * @Author xuyang7
 * @Date 2018/8/14 15:34
 * @Version 1.0
 **/

public interface ProductManagerApi {

    /**
     * ProductInfoDO
     * 存储 产品信息
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> saveProductInfo(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 爆款推荐管理 更新爆款产品排序信息
     * @param productInfoDO
     * @return Message<Boolean>
     */
    Message<Boolean> updateHotSaleSort(ProductInfo productInfoDO);
    /**
     * productInfoDO
     * 上架产品操作
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> releaseProduct(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 下架产品操作
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> revokeProduct(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 取消预约产品操作
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> cancelOrderProduct(ProductInfo productInfo);
    /**
     * dProductInfoDO
     * 修改产品操作
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> modifyProductInfo(ProductInfo productInfo);
    /**
     * ProductInfoDO
     * 删除产品操作
     * @param productInfo
     * @return Message<Boolean>
     */
    Message<Boolean> deleteProductInfo(ProductInfo productInfo);

}
