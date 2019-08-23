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
     * 查询基金产品信息列表
     * FundProductInfoDO
     * @param param
     * @return Message<Page<FundProductInfoVO>>
     */
    Message<ProductInfo> queryFundProductInfoList(ProductInfo param);

    /**
     * FundProductDynamicInfoDO
     * 存储 基金动态信息
     * @param fundProductDynamicInfo
     * @return Message<Boolean>
     */
    Message<Boolean> saveOfd07(ProductInfo fundProductDynamicInfo);

    /**
     * FundProductBasicParamDO
     * 存储 基金基础信息
     * @param fundProductBasicParam
     * @return Message<Boolean>
     */
    Message<Boolean> saveOfdC1(ProductInfo fundProductBasicParam);

    /**
     * FundProductInfoDO
     * 存储 基金产品信息
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> saveFundProductInfo(ProductInfo fundProductInfo);
    /**
     * FundProductInfoDO
     * 爆款推荐管理 更新爆款产品排序信息
     * @param fundProductInfoDO
     * @return Message<Boolean>
     */
    Message<Boolean> updateHotSaleSort(ProductInfo fundProductInfoDO);
    /**
     * FundProductInfoDO
     * 上架基金产品操作
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> releaseFundProduct(ProductInfo fundProductInfo);
    /**
     * FundProductInfoDO
     * 下架基金产品操作
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> revokeFundProduct(ProductInfo fundProductInfo);
    /**
     * FundProductInfoDO
     * 取消预约基金产品操作
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> cancelOrderFundProduct(ProductInfo fundProductInfo);
    /**
     * FundProductInfoDO
     * 修改基金产品操作
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> modifyFundProductInfo(ProductInfo fundProductInfo);
    /**
     * FundProductInfoDO
     * 删除基金产品操作
     * @param fundProductInfo
     * @return Message<Boolean>
     */
    Message<Boolean> deleteFundProductInfo(ProductInfo fundProductInfo);
    /**
     * 后台管理动态TACode 和 FundCode 查询
     * @return Message<List<FundProductInfoDO>>
     */
    Message<List<ProductInfo>> queryTaCodeAndFundCode();

    /**
     * * 使用 开始时间和结束时间 查询 动态的基金净值信息
     * * @return  Message<List<FundNetValueInfo>>
     * @param beginDate
     * @param endDate
     * @param fundCode
     * @param pageSize
     * @param currentPageNo
     * @return
     */
    Message<ProductInfo> selectDynamicInfoByBeginTimeOrEndTime(String beginDate, String endDate, String fundCode, String pageSize, String currentPageNo);
    /**
     * Boolean
     * 查询基金产品是否已经存在。
     * @param taCode,fundCode
     * @return Message<Boolean>
     * @param taCode
     * @param fundCode
     * @param fundName
     * @return
     */
    Message<Boolean> queryFundProductIsExist(String taCode, String fundCode, String fundName);
    /**
     * 查询基金动态信息
     *
     * @param taCode TA代码
     * @param fundCode 基金代码
     * @param fundName 基金名称
     * @return Message<FundProductDynamicInfo>
     */
    Message<ProductInfo> queryOfd07(String taCode, String fundCode, String fundName);
    /**
     * 查询基金动态信息通过净值日期
     *
     * @param taCode TA代码
     * @param fundCode 基金代码
     * @param updateDate 基金名称
     * @return Message<FundProductDynamicInfo>
     */
    Message<ProductInfo> queryOfd07ByUpdateDate(String taCode, String fundCode, String updateDate);

    /**
     * 查询基金基础信息
     *
     * @param taCode TA代码
     * @param fundCode 基金代码
     * @param fundName 基金名称
     * @return Message<FundProductBasicParam>
     */
    Message<ProductInfo> queryOfdC1(String taCode, String fundCode, String fundName);
    //-----------资金清算部分->资金交收规则---------//

    /**
     * 新增资金交收规则信息
     * @param fundProductDeliveryRuleVO
     * @return Message<Boolean>
     */
    Message<Boolean> saveFundProductDeliveryRuleInfo(ProductInfo fundProductDeliveryRuleVO);
    /**
     * 修改资金交收规则
     * @param fundProductDeliveryRuleVO
     * @return Message<Boolean>
     */
    Message<Boolean> modifyFundProductDeliveryRuleInfo(ProductInfo fundProductDeliveryRuleVO);
    /**
     * 删除资金交收规则
     * @param id
     * @return Message<Boolean>
     */
    Message<Boolean> deleteFundProductDeliveryRuleInfo(String id);
    /**
     * 复制资金交收规则
     * @param fromTaCode
     * @param fromFundCode
     * @param toTaCode
     * @param toFundCode
     * @return Message<Boolean>
     */
    Message<Boolean> copyFundProductDeliveryRuleInfo(String fromTaCode, String fromFundCode, String toTaCode, String toFundCode);
    /**
     * 查询资金交收规则列表
     * @param fundCode 基金代码
     * @param fundName 基金名称
     * @param businessType 业务类型(一个业务类型对应一条数据，为空则构造所有数据)
     * @return Message<List<FundProductDeliveryRuleVO>>
     */
    Message<List<ProductInfo>> queryFundProductDeliveryRuleList(String fundCode, String fundName, String businessType);
    //检查 TA费率是否设置 以及 资金交收规则
    /**
     * 检查是否设置TA账户
     * @param taCode
     * @param fundCode
     * @return
     */
    Message<Boolean> checkSettingTaAccountIsNull(String taCode, String fundCode);
    /**
     * 检查是否设置TA费率
     * @param taCode
     * @param fundCode
     * @return
     */
    Message<Boolean> checkSettingTaRateIsNull(String taCode, String fundCode);

    /**
     * 检查是否设置交收规则
     * @param taCode
     * @param fundCode
     * @return
     */
    Message<Boolean> checkSettingDeliveryRuleIsNull(String taCode, String fundCode);

    /**
     * 根据交收规则取得交收日期
     * @param taCode TA代码
     * @param fundCode 基金代码
     * @param tradeDate 交易日 格式YYYYMMDD
     * @param rule 交易规则 0-申购确认扣款 1-申购失败退款 2- 赎回确认资金增加 3-赎回确认回款 4-现金分红资金增加 5-现金分红回款
     * @return 交收日期 格式YYYYMMDD
     */
    Message<String> getDeliveryDate(String taCode, String fundCode, String tradeDate, String rule);

    /**
     * 根据TACode 查询基金代码 fundCode
     * @param taCode
     * @param type 1 基金账户的fundCode
     *             2 基金标准费率的fundCode
     *             3 基金交收规则的fundCode
     * @return fundCode 列表
     */
    Message<List<String>> queryFundCodeByTaCode(String taCode, String type);

    /**
     * 查询所有基金代码 fundCode
     * @return
     */
    Message<List<String>> queryFundCodeListAll(ProductInfo fundProductInfo);

    /**
     * 根据基金代码和taCode 查询基金产品 录入修改信息。
     * @par taCode
     * @param fundCode
     * @return
     */
    Message<ProductInfo> queryModifyFundProductInputInfo(String taCode, String fundCode);

    /**
     * 更新基金产品基础信息
     * @param fundProductBasicParam
     * @return
     */
    Message<Boolean> updateFundProductBasicParam(ProductInfo fundProductBasicParam);

    /**
     * 更新基金产品动态信息
     * @param fundProductDynamicInfo
     * @return
     */
    Message<Boolean> updateFundProductDynamicInfo(ProductInfo fundProductDynamicInfo);

    /**
     * 更新基金产品表数据
     * @param fundProductInfo
     * @return
     */
    Message<Boolean> updateFundProductInfo(ProductInfo fundProductInfo);

    /**
     * 根据基金代码查询基金单条产品信息。
     * @param taCode
     * @param fundCode
     * @return
     */

    Message<ProductInfo> queryFundProductInfo(String taCode, String fundCode);


    /**
     * 查询基金产品费率详细信息
     * @param fundCode
     * @param taCode
     * @return
     */
    Message<Boolean> queryFundProductRatedetailInfo(String taCode, String fundCode);

    /**
     * 查询基金产品费率基础信息
     * @param fundCode
     * @param taCode
     * @return
     */

    Message<Boolean> queryFundProductRatebasicInfo(String taCode, String fundCode);


    /**
     * 保存基金产品费率详细信息
     * @param fundProductRatedetailInfoVO
     * @return
     */
    Message<Boolean> modifyFundProductRatedetailInfo(ProductInfo fundProductRatedetailInfoVO);

    /**
     * 查询基金产品标准费率展示List
     * @param fundCode
     * @param fundName
     * @return
     */
    Message<List<ProductInfo>> queryFundProductTARateList(String fundCode, String fundName);
    /**
     * 查询基金产品标准费率modify部分展示List
     * @param fundCode
     * @param taCode
     * @return
     */
    Message<List<ProductInfo>> queryFundProductRateModifyShowVo(String fundCode, String taCode);
    /**
     * 复制TA标准费率
     * @param fromTaCode
     * @param fromFundCode
     * @param toTaCode
     * @param toFundCode
     * @return Message<Boolean>
     */
    Message<Boolean> copyFundProductTaRateInfo(String fromTaCode, String fromFundCode, String toTaCode, String toFundCode);

}
