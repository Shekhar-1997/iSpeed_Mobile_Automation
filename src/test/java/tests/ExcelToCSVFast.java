package tests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.Iterator;

public class ExcelToCSVFast {

    public static void main(String[] args) {
        String excelFilePath = "D:\\ST_QA\\PushDB_Ispeed_Automation\\DBMigAutomationProject\\DBMigration_Automation\\textExcelout.xlsx";
        String csvFilePath = "output.csv";

        try (FileInputStream excelFile = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(excelFile);
             FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                int lastColumn = row.getLastCellNum();
                String[] rowData = new String[lastColumn];

                for (int i = 0; i < lastColumn; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = getCachedCellValue(cell);
                }
                csvWriter.writeNext(rowData, false); // false = no quote escaping unless needed
            }

            System.out.println("Fast Excel to CSV conversion complete.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCachedCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC:
                return DateUtil.isCellDateFormatted(cell)
                        ? cell.getDateCellValue().toString()
                        : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula(); // return formula string instead of evaluating
            case BLANK: return "";
            default: return "";
        }
    }
}


