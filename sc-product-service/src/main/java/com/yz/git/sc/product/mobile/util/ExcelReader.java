package com.yz.git.sc.product.mobile.util;

import com.yz.git.sc.product.mobile.bean.AnalyzeDataVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * poi 工具类
 * <p>
 * 作者: guo yao
 * 日期: 2020年04月23日
 * 时间: 下午17:10
 */
public class ExcelReader {

    private static Logger logger = Logger.getLogger(ExcelReader.class.getName()); // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @param filePath 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static Set<String> readExcel(String filePath) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            // 获取Excel文件
            File excelFile = new File(filePath);
            if (!excelFile.exists()) {
                logger.warning("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            Set<String> resultSet = parseExcel(workbook);

            return resultSet;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + filePath + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }


    /**
     * 读取Excel文件内容
     *
     * @param filePath 要读取的Excel文件所在路径
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<AnalyzeDataVo> readResultExcel(String filePath) {

        Workbook workbook = null;
        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
            String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
            // 获取Excel文件
            File excelFile = new File(filePath);
            if (!excelFile.exists()) {
                logger.warning("指定的Excel文件不存在！");
                return null;
            }

            // 获取Excel工作簿
            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<AnalyzeDataVo> result = parseResultExcel(workbook);

            return result;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + filePath + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }


    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<AnalyzeDataVo> parseResultExcel(Workbook workbook) {
        List<AnalyzeDataVo> result = new ArrayList<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warning("解析Excel失败，在第一行没有读取到任何数据！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (null == row) {
                    continue;
                }
                AnalyzeDataVo analyzeDataVo = convertResultRowToData(row);
                if (null == analyzeDataVo) {
                    logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                result.add(analyzeDataVo);
            }
        }

        return result;
    }

    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static Set<String> parseExcel(Workbook workbook) {
        Set<String> result = new HashSet<>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warning("解析Excel失败，在第一行没有读取到任何数据！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (null == row) {
                    continue;
                }
                String resultData = convertRowToData(row);
                if (null == resultData) {
                    logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                result.add(resultData);
            }
        }

        return result;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellType()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }


    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static AnalyzeDataVo convertResultRowToData(Row row) {
        AnalyzeDataVo result = new AnalyzeDataVo();
        // 读取请求连接
        String api = convertCellValueToString(row.getCell(0));
        // 获取请求方式
        String method = convertCellValueToString(row.getCell(1));
        // 获取功能说明
        String desc = convertCellValueToString(row.getCell(2));
        // 获取功能说明
        String count = convertCellValueToString(row.getCell(3));
        // 获取百分比
        String percent = convertCellValueToString(row.getCell(4));

        result.setApi(api);
        result.setMethod(method);
        result.setDesc(desc);
        result.setCount(Double.valueOf(count));
        result.setPercent(percent);

        return result;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static String convertRowToData(Row row) {
        StringBuffer result = new StringBuffer();
        // 读取请求连接
        String requestPath = convertCellValueToString(row.getCell(0));
        // 获取功能说明
        String desc = convertCellValueToString(row.getCell(1));
        // 获取请求方式
        String requestType = convertCellValueToString(row.getCell(2));

        result.append(requestType).append(" ").append(requestPath).append(" ").append(desc);

        return result.toString();
    }


}
