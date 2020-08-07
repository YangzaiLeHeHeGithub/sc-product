package com.yz.git.sc.product.java8;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yz.git.sc.product.java8.jihe.ExcelDataBean;
import com.yz.git.sc.product.java8.jihe.MobileFunctionEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * FunctionStatisticalUntil
 *
 * @author xuyang
 * @version 1.0
 * @description 数据埋点统计工具类
 * @date 2020/8/4 14:09
 */
public class FunctionStatisticalUntil {
    private static final Map<String, List<ExcelDataBean>> map = Maps.newHashMap();

    /**
     * 初始化Map
     */
    static {
        map.put("/rest/v4/config/configvalue", null);
        map.put("/rest/v4beta2/memory_read", null);
        map.put("/rest/v4beta2/compare", null);

    }

    /**
     * 填充数据
     *
     * @return
     */
    public static List<ExcelDataBean> countUtil() {
        int i = 0;
        File dir = new File("E://埋点数据统计/access.log");
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(dir));) {
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTXT = null;
            while ((lineTXT = bufferedReader.readLine()) != null) {

                for (String key : map.keySet()) {
                    if (lineTXT.contains(key)) {
                        String[] str = lineTXT.replace("|", " ").split(" ");
                        ExcelDataBean dataBean = ExcelDataBean.builder().requestUrl(key).requestMethod(str[2]).count(1).build();
                        dealMap(map, key, dataBean);

                    }
                }

            }
            System.out.println(i);
        } catch (Exception e) {

        }
        List<ExcelDataBean> finalExcelData = Lists.newArrayList();
        map.values().forEach(list -> finalExcelData.addAll(list));
        return finalExcelData;
    }

    /**
     * 处理请求次数
     *
     * @param map
     * @param key
     * @param dataBean
     */
    private static void dealMap(Map<String, List<ExcelDataBean>> map, String key, ExcelDataBean dataBean) {
        List<ExcelDataBean> excelDataBeans = map.get(key);
        if (CollectionUtils.isEmpty(excelDataBeans)) {
            dealFunctionDesc(dataBean);
            List<ExcelDataBean> list = Lists.newArrayList(dataBean);
            map.put(key, list);
            return;
        }

        excelDataBeans.forEach(excelDataBean -> {
            if (Objects.equals(dataBean.getRequestMethod(), excelDataBean.getRequestMethod()) && Objects.equals(dataBean.getRequestUrl(), excelDataBean.getRequestUrl())) {
                Integer origenValue = excelDataBean.getCount();
                excelDataBean.setCount(++origenValue);
                return;
            }
            excelDataBeans.add(dataBean);
            map.put(key, excelDataBeans);
        });

    }

    /**
     * 处理接口功能描述
     *
     * @param dataBean
     */
    private static void dealFunctionDesc(ExcelDataBean dataBean) {
        for (MobileFunctionEnum mobileEnum : MobileFunctionEnum.values()) {
            if (Objects.equals(mobileEnum.getRequestUrl(), dataBean.getRequestUrl()) && Objects.equals(mobileEnum.getRequestMethod(), dataBean.getRequestMethod())) {
                dataBean.setFunctionDesc(mobileEnum.getFunctionDesc());
            }
        }

    }

    private static void exportExecl(List<ExcelDataBean> excelDataBeans) {
        BigDecimal countMax = new BigDecimal(excelDataBeans.stream().mapToInt(ExcelDataBean::getCount).sum());
        List<ExcelDataBean> excelData = excelDataBeans.stream().map(dataBean -> {
            dataBean.setPercent(new BigDecimal(dataBean.getCount()).divide(countMax, 4, BigDecimal.ROUND_UP));
            return dataBean;
        }).collect(Collectors.toList());
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("移动阅卷应用功效", "移动阅卷"),
                ExcelDataBean.class, excelData);

        try (FileOutputStream fos = new FileOutputStream("E://埋点数据统计/abc.xls")) {
            workbook.write(fos);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        exportExecl(countUtil());
    }

}
 