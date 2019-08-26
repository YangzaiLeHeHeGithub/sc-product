package com.yz.git.sc.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yz.git.sc.product.api.param.ProductInfo;
import com.yz.git.sc.product.dao.ScProductInfoMapper;
import com.yz.git.sc.product.domain.ScProductInfo;
import com.yz.git.sc.product.service.ProductManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuyang
 * @date 2019/08/26
 */
@Service
public class ProductManagerServiceImpl implements ProductManagerService{
    @Autowired
    ScProductInfoMapper scProductInfoMapper;

    @Override
    public Boolean saveProductInfo(ProductInfo productInfo) {
        ScProductInfo scProductInfo = new ScProductInfo();
        BeanUtils.copyProperties(productInfo,scProductInfo);
        return scProductInfoMapper.insert(scProductInfo) > 0 ? true : false;
    }

    @Override
    public Boolean updateHotSaleSort(ProductInfo productInfoDO) {
        ScProductInfo scProductInfo = new ScProductInfo();
        BeanUtils.copyProperties(productInfoDO,scProductInfo);
        LambdaUpdateWrapper<ScProductInfo> wrapper = new LambdaUpdateWrapper<ScProductInfo>()
                .eq(ScProductInfo::getProductGrade,productInfoDO.getProductGrade())
                .eq(ScProductInfo::getIsDelete,productInfoDO.getIsDelete());
        return scProductInfoMapper.update(scProductInfo,wrapper) > 0 ? true : false;
    }

    @Override
    public Boolean releaseProduct(ProductInfo productInfo) {
        return null;
    }

    @Override
    public Boolean revokeProduct(ProductInfo productInfo) {
        return null;
    }

    @Override
    public Boolean cancelOrderProduct(ProductInfo productInfo) {
        return null;
    }

    @Override
    public Boolean modifyProductInfo(ProductInfo productInfo) {
        return null;
    }

    @Override
    public Boolean deleteProductInfo(ProductInfo productInfo) {
        return null;
    }
}
