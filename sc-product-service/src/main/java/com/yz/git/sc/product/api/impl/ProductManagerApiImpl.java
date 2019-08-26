package com.yz.git.sc.product.api.impl;

import com.yz.git.sc.product.api.ProductManagerApi;
import com.yz.git.sc.product.api.param.ProductInfo;
import com.yz.git.sc.product.common.message.Message;
import com.yz.git.sc.product.common.message.Messages;
import com.yz.git.sc.product.common.message.Result;
import com.yz.git.sc.product.service.ProductManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author xuyang
 * @date 2019/08/26
 */
@Slf4j
@Service
public class ProductManagerApiImpl implements ProductManagerApi{
    @Autowired
    ProductManagerService productManagerService;

    @Override
    public Message<Boolean> saveProductInfo(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.saveProductInfo(productInfo));
        } catch (Exception e) {
            log.error("保存产品信息失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> updateHotSaleSort(ProductInfo productInfoDO) {
        Objects.requireNonNull(productInfoDO,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfoDO);
        try {
            return Messages.success(productManagerService.updateHotSaleSort(productInfoDO));
        } catch (Exception e) {
            log.error("更新产品信息失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> releaseProduct(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.releaseProduct(productInfo));
        } catch (Exception e) {
            log.error("上架产品信息失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> revokeProduct(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.revokeProduct(productInfo));
        } catch (Exception e) {
            log.error("下架产品信息失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> cancelOrderProduct(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.cancelOrderProduct(productInfo));
        } catch (Exception e) {
            log.error("取消预约产品失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> modifyProductInfo(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.modifyProductInfo(productInfo));
        } catch (Exception e) {
            log.error("修改产品信息失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }

    @Override
    public Message<Boolean> deleteProductInfo(ProductInfo productInfo) {
        Objects.requireNonNull(productInfo,"传入的产品信息为空！");
        log.info("传入的产品参数，{}",productInfo);
        try {
            return Messages.success(productManagerService.deleteProductInfo(productInfo));
        } catch (Exception e) {
            log.error("删除产品失败",e);
            return Messages.failed(Result.ERROR.getValue(),"系统错误");
        }
    }
}
