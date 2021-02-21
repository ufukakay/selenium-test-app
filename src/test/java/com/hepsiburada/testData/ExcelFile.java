package com.hepsiburada.testData;

import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFile {

    File file;
    FileInputStream inputStream;
    FileOutputStream outputStream;
    Workbook workbook;
    Sheet sheet;
    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelData";
    String fileName = "TestData.xlsx";
    String sheetName = "Sheet1";

    public void setExcelFile() throws IOException {

        file = new File(filePath + "\\" + fileName);

        inputStream = new FileInputStream(file);

        workbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));


        if (fileExtensionName.equals(".xlsx")) {

            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {

            workbook = new HSSFWorkbook(inputStream);

        }

        sheet = workbook.getSheet(sheetName);
    }


    public String[] getDataCell() {
        String[] columns = new String[2];
        Row row = sheet.getRow(sheet.getLastRowNum());
        columns[0] = row.getCell(0).getStringCellValue();
        columns[1] = row.getCell(1).getStringCellValue();

        return columns;
    }

    public void setDataCell(String result) throws IOException {

        sheet.getRow(sheet.getLastRowNum()).createCell(2).setCellValue(result);
        outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();

    }


}