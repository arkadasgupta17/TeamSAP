package dataExtractionUtilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class AccessSourceFile {
    private FileInputStream fis;
    private XSSFWorkbook workbook;

    public void close() throws IOException {
        fis.close();
    }

    public String getValue(String filePath, String sheetName, String targetDataSource) throws IOException {
    	fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int dataSourceColNum = 0; // Column index of DataSource
        int valueColNum = 1;      // Column index of Value

        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            if (row != null) {
                XSSFCell cell = row.getCell(dataSourceColNum);

                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(targetDataSource)) {
                    XSSFCell valueCell = row.getCell(valueColNum);
                    if (valueCell != null) {
                        return valueCell.getStringCellValue();
                    }
                }
            }
        }
        return "Value not found";
    }
}
