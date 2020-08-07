package com.yz.git.sc.product.mobile.util;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者: guo yao
 * 日期: 2020年04月26日
 * 时间: 下午14:19
 */
public class AnalyzeUtil {

    // 获取请求内容
    public static String findRequestText(String text) {
        String[] str = text.split("\\|");
        return str[2].length()>3 ? str[2] : "";
    }

    // 获取包括状态码的text
    public static String analyzeLine(String text) {
        String reg = "\"(.*?)\"http";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String result = matcher.group(1);
            String temp = result.replace(" HTTP/1.1\"", "").replace(" HTTP/1.0\"", "").trim();
            int index = temp.lastIndexOf(" ");
            return temp.trim().substring(0, index);
        }
        return "";
    }

    // 带"{"的匹配检查
    public static Boolean matchHander(String line1, String line2) {
        if (line1.indexOf("{") == -1) {
            return line2.contains(line1);
        }
        String h_line1 = replaceText(line1);
        String h_line2 = handlerText(line1, line2);
        return matchHander(h_line1, h_line2);
    }

    // 替换path的{param}
    public static String replaceText(String text) {
        if (text.indexOf("{") == -1) {
            return text;
        }
        int index1 = text.indexOf("{");
        int index2 = text.indexOf("}") + 1;
        String param = text.substring(index1, index2);
//        String temp = text.replace(param, "#");
        String[] line1s = text.replace(param, "#").split(" ");
        return line1s[0] + " " + line1s[1];
    }

    // 处理2条line
    public static String handlerText(String line1, String line2) {
        line2 = line2.replace(" HTTP/1.1", "");
        int index1 = line1.indexOf("{");
        if (index1 >= line2.length()) {
            return line2;
        }
        int index2 = line2.indexOf("/", index1);
        if (index2 == -1) {
            index2 = line2.length();
        }
        // 获取第一个参数
        String param = line2.substring(index1, index2);
        int index4 = param.indexOf("?");
        String line2_1 = "";
        if (index4 != -1) {
//            param = param.substring(0,index4);
            String temp1 = line2.substring(0, index1);
            String temp2 = line2.substring(index1 + index4, line2.length());
            line2_1 = temp1 + "#" + temp2;
        }else{
            String temp1 = line2.substring(0, index1);
            String temp2 = line2.substring(index2, line2.length());
            line2_1 = temp1 + "#" + temp2;
        }
//        line2_1 = line2.replace(param, "#");
        // 去掉后面?的内容
        /*int index3 = line2_1.indexOf("?");
        if (index3 != -1) {
            line2_1 = line2_1.substring(0, index3);
        }*/
        return line2_1;
    }

    // 检查前缀方法
    public static Boolean checkMethod(String line1, String line2) {
        int index1 = line1.indexOf(" ");
        int index2 = line1.indexOf(" ");
        String linePrefix1 = line1.substring(0, index1);
        String linePrefix2 = line2.substring(0, index2);
        return linePrefix1.equals(linePrefix2);
    }

    // 检查匹配第一个"{"号前的uri
    public static Boolean checkURL(String line1, String line2) {
        int index = line1.indexOf("{");
        // 判断index长度是否大于line2
        if (line2.length() < index) {
            return false;
        }
        String lineURL1 = line1.substring(0, index);
        String lineURL2 = line2.substring(0, index);
        return lineURL1.equals(lineURL2);
    }

    // 不带"{"的匹配
    public static Boolean checkNotPathLine(String api, String line) {
        String[] temp = api.split(" ");
        api = temp[0] + " " + temp[1];
        int index = line.indexOf("?");
        if (index == -1) {
            index = line.length();
        }
        String lineURL = line.substring(0, index);
        return api.equals(lineURL.replace(" HTTP/1.1", ""));
    }

    // 计算百分比
    public static String getPercent(Double num, Double totalPeople) {
        String percent;
        Double d = 0.0;
        if (totalPeople == 0) {
            d = 0.0;
        } else {
            d = num * 1.0 / totalPeople;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        // 控制保留小数点后几位, 2:表示保留2位小数点
        nf.setMinimumFractionDigits(4);
        percent = nf.format(d);
        return percent;
    }
}
