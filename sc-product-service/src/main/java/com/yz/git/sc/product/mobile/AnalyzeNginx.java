package com.yz.git.sc.product.mobile;

import com.yz.git.sc.product.mobile.bean.AnalyzeDataVo;
import com.yz.git.sc.product.mobile.util.AnalyzeUtil;
import com.yz.git.sc.product.mobile.util.ExcelReader;
import com.yz.git.sc.product.mobile.util.ExcelWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 作者: guo yao
 * 日期: 2020年04月23日
 * 时间: 下午15:35
 */
public class AnalyzeNginx {

    private static String ENCODING = "UTF-8";

    private static Double staticCount = 0D;

    private static Double totalCount = 0D;

    private static Double apiCount = 0D;

    private static Double notCount = 0D;

    private static Map<String, Double> exist = new HashMap<>();

    private static Map<String, Double> unknown = new HashMap<>();

    private static String tempString = "";

//    public static void main(String[] args) {
//        // api 功能 excel
//        //String excelPath = "E:\\埋点数据统计\\移动阅卷\\功能列表.xlsx";
//        String excelPath = "E:\\埋点数据统计\\郭尧\\功能列表.xlsx";
//        // 日志 txt
//        //String filePath = "E:\\埋点数据统计\\access.log";
//        String filePath = "E:\\埋点数据统计\\郭尧\\temp3.txt";
////        String filePath = "G:\\日志信息\\河南nginx日志\\temp.txt";
//        Set<String> result = ExcelReader.readExcel(excelPath);
//        readTxtFile(filePath, result);
//        // 有效百分比分析
//        List<AnalyzeDataVo> resultList = resultAnalyze(exist);
//        // 排序
//        resultList.sort((y, x) -> Double.compare(x.getCount(), y.getCount()));
//        //String writeFilePath = "E:\\埋点数据统计\\nginx_log_analyze_result.xlsx";
//        String writeFilePath = "E:\\埋点数据统计\\GY.xlsx";
//        ExcelWriter.writeExcel(resultList, writeFilePath);
//    }
    public static void main(String[] args) {
        // api 功能 excel
        String excelPath = "E:\\埋点数据统计\\移动阅卷\\功能列表.xlsx";
        // 日志 txt
        String filePath = "E:\\埋点数据统计\\access.log";
        Set<String> result = ExcelReader.readExcel(excelPath);
        readTxtFile(filePath, result);
        // 有效百分比分析
        List<AnalyzeDataVo> resultList = resultAnalyze(exist);
        // 排序
        resultList.sort((y, x) -> Double.compare(x.getCount(), y.getCount()));
        String writeFilePath = "E:\\埋点数据统计\\nginx_log_analyze_result.xlsx";
        ExcelWriter.writeExcel(resultList, writeFilePath);
    }

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     *
     * @param filePath
     */
    public static void readTxtFile(String filePath, Set<String> set) {
        String lineTxt = null;
        try {
            int setSize = set.size();
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), ENCODING);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (StringUtils.isBlank(lineTxt)) {
                        continue;
                    }
                    if (lineTxt.contains("\"-\" \"-\" \"-\"")){
                        continue;
                    }
                    totalCount++;
                    System.out.println("读取第[" + totalCount + "]行");
                    String line = AnalyzeUtil.findRequestText(lineTxt);
                    if(Objects.equals(line,"")) {continue;}
//                    System.err.println("扫描行=" + line);
                    // 判断静态
                    if (line.contains("static")) {
                        staticCount++;
                        continue;
                    }
                    Boolean hasMatch = false;
                    int cyclicCount = 0;
                    setLineStatus(line, false);
                    // 已有链接API
                    for (String item : set) {
                        cyclicCount++;
                        // 增加line判断状态
                        if (checkLineStatus(line)) {
                            continue;
                        }
                        // 判断前缀
                        if (AnalyzeUtil.checkMethod(item, line)) {
                            // 有path参数
                            if (item.indexOf("{") != -1) {
                                if (AnalyzeUtil.checkURL(item, line)) {
                                    if (AnalyzeUtil.matchHander(item, line)) {
                                        statistics(item);
                                        apiCount++;
                                        hasMatch = true;
                                        setLineStatus(line, true);
                                        continue;
                                    }
                                }
                            } else {
                                // 无path参数 , 直接对比
                                if (AnalyzeUtil.checkNotPathLine(item, line)) {
                                    statistics(item);
                                    apiCount++;
                                    hasMatch = true;
                                    setLineStatus(line, true);
                                    continue;
                                }
                            }
                        }
                        if (!hasMatch && cyclicCount == setSize) {
                            notCount++;
//                            System.err.println("not line = " + line);
                            System.out.println("line text = " + AnalyzeUtil.analyzeLine(lineTxt));
                        }
                    }
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            System.out.println("line=" + lineTxt);
            e.printStackTrace();
        } finally {
            // 总请求数
            System.err.println("总请求数：" + totalCount);
            System.err.println("静态资源请求：" + staticCount);
            System.err.println("API请求：" + apiCount);
            System.err.println("未知请求：" + notCount);
            // 存在的
            for (Map.Entry<String, Double> map : exist.entrySet()) {
                System.out.println("exist==" + map.getKey() + " " + map.getValue());
            }
            // 不存在的
            for (Map.Entry<String, Double> map : unknown.entrySet()) {
                System.out.println("unknown" + map.getKey() + " " + map.getValue());
            }
        }
    }

    // 分析结果并包装数据
    public static List<AnalyzeDataVo> resultAnalyze(Map<String, Double> result) {
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        List<AnalyzeDataVo> resultList = new ArrayList<>();
        for (Map.Entry<String, Double> map : result.entrySet()) {
            AnalyzeDataVo dataVo = new AnalyzeDataVo();
            String[] info = map.getKey().split(" ");
            Double count = map.getValue();
            dataVo.setMethod(info[0]);
            dataVo.setApi(info[1]);
            dataVo.setDesc(info[2]);
            dataVo.setCount(count);
            // 计算百分比
            dataVo.setPercent(AnalyzeUtil.getPercent(count, apiCount));
            resultList.add(dataVo);

        }
        return resultList;
    }

    // 记录统计数据
    public static void statistics(String key) {
        if (exist.containsKey(key)) {
            Double count = exist.get(key) + 1;
            exist.put(key, count);
        } else {
            exist.put(key, 1D);
        }
    }

    // 设置line状态
    public static void setLineStatus(String line, Boolean status) {
        if (status) {
            tempString = line + "@@true";
        } else {
            tempString = line + "@@false";
        }

    }

    // 判断line状态
    public static Boolean checkLineStatus(String line) {
        String[] temp = tempString.split("@@");
        if (temp[1].equals("true")) {
            return true;
        }
        return false;
    }

}
