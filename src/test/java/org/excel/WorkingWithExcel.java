package org.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * This class demonstrates principle how to work with Excel during testing.
 * You can save your data to Excel sheet, and use this sheet in tests.
 * This class implements 2 utilities methods, that are fetching row/column data in ArrayList format from specific Excel sheet.
 * We can use utilities methods like these in our TestNG tests, and fetch data for tests from Excel.
 */
public class WorkingWithExcel {

    private static final String FILE_NAME = "ExcelData.xls";

    public static void main(String[] args) throws IOException {
        //get excel sheet resource and open excel file and map it to excel object
        URL fileUrl = WorkingWithExcel.class.getResource(FILE_NAME);
        HSSFWorkbook excelFile = new HSSFWorkbook(fileUrl.openStream());

        int sheetsCount = excelFile.getNumberOfSheets();
        System.out.println("Number of sheets in our Excel file: " + sheetsCount);
        for (int i = 0; i < sheetsCount; i++) {
            System.out.println("Sheet no." + i + " is: " + excelFile.getSheetName(i));
        }

        //Get second sheet
        HSSFSheet sheet2 = excelFile.getSheetAt(1);
        assertEquals("Name of second sheet is correct", "Sheet2 - Regression tests", sheet2.getSheetName());

        Row row;
        Cell cell;
        System.out.println("----- Getting Excel sheet data of Sheet2 with FOR loop -----");
        //Print data of whole sheet with FOR cycle
        for (int i = 0; i <= sheet2.getLastRowNum(); i++) {
            System.out.print("Row of sheet2: ");
            row = sheet2.getRow(i);
            for (int j = 0; j < row.getLastCellNum() ; j++) {
                cell = row.getCell(j);
                System.out.print(cell.getStringCellValue() + ", ");
            }
            System.out.print("\n");
        }
        System.out.println("");

        HSSFSheet sheet1 = excelFile.getSheetAt(0);
        assertEquals("Name of first sheet is correct", "Sheet1 - Smoke tests", sheet1.getSheetName());
        Iterator<Row> rowIterator = sheet1.iterator();

        System.out.println("----- Getting Excel sheet data of Sheet1 with iterators -----");
        //Another way is to use cell/row iterators
        while (rowIterator.hasNext()) {
            System.out.print("Row of sheet1: ");
            row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                System.out.print(cell.getStringCellValue() + ", ");
            }
            System.out.print("\n");
        }
        System.out.println("");

        System.out.println("----- Getting Excel data from our util methods -----");
        System.out.println("Sheet with index 0, row 0: " + getDataOfRowWithIndex(excelFile.getSheetAt(0), 0).toString());
        System.out.println("Sheet with index 1, row 0: " + getDataOfRowWithIndex(excelFile.getSheetAt(1), 0).toString());
        System.out.println("Sheet with index 2, row 3: " + getDataOfRowWithIndex(excelFile.getSheetAt(2), 3).toString());

        System.out.println("Sheet with index 0, column 0: " + getDataOfColumnWithIndex(excelFile.getSheetAt(0), 0).toString());
        System.out.println("Sheet with index 0, column 1: " + getDataOfColumnWithIndex(excelFile.getSheetAt(0), 1).toString());
        System.out.println("Sheet with index 0, column 2: " + getDataOfColumnWithIndex(excelFile.getSheetAt(0), 2).toString());
        System.out.println("Sheet with index 2, column 3: " + getDataOfColumnWithIndex(excelFile.getSheetAt(2), 3).toString());
    }

    /**
     * Util method that will get all values from specified row from an Excel sheet and return array of these values
     * @param sheet HSSFSheet that we need to parse values from
     * @param rowIndex index of row from which we are parsing values
     * @return list of Strings that are available in each cell in row of an Excel sheet
     */
    public static List<String> getDataOfRowWithIndex(HSSFSheet sheet, int rowIndex) {
        List<String> returnValue = new ArrayList<>();
        Row row = sheet.getRow(rowIndex);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            returnValue.add(row.getCell(i).getStringCellValue());
        }
        return returnValue;
    }

    /**
     * Util method that will get all values from specified column from an Excel sheet and return array of these values
     * @param sheet HSSFSheet that we need to parse values from
     * @param columnIndex index of column from which we are parsing values
     * @return list of Strings that are available in each cell in column of an Excel sheet
     */
    public static List<String> getDataOfColumnWithIndex(HSSFSheet sheet, int columnIndex) {
        List<String> returnValue = new ArrayList<>();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            returnValue.add(sheet.getRow(i).getCell(columnIndex).getStringCellValue());
        }
        return returnValue;
    }
}
