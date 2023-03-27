package com.ey.il.appium.core;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.*;

public class TestdataManger {
    String fileName = "TestData";
    String sheetName;
    public TestdataManger(String sheetName) {
        this.sheetName = sheetName;
    }
    public TestdataManger(String fileName, String sheetName) {
        this.sheetName = sheetName;
        this.fileName = fileName;
    }

    //This method reads the data based on the heading provided in the excel
    public String readData(String header) {
        InputStream input = null;
        XSSFWorkbook workbook = null;
        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream("Testdata.xlsx");
            workbook = new XSSFWorkbook(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            Assert.fail("File not found");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Assert.fail("File not found");
                }
            }
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        XSSFRow row = sheet.getRow(0);
        int col_num = -1;

        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equals(header))
                col_num = i;
        }
        if (col_num == -1) {
            Assert.fail("No heading found in TestData file");
            System.out.println("No heading found in TestData file");
        }

        row = sheet.getRow(1);
        XSSFCell cell = row.getCell(col_num);
        DataFormatter formatter = new DataFormatter();
        String value = formatter.formatCellValue(cell);
        //System.out.println("Value of the Excel Cell is - " + value);

        return value.trim();

    }
}
