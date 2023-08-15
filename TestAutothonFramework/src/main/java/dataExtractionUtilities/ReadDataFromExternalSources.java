package dataExtractionUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataFromExternalSources {
	
//Method to read data from excel file
	public static ArrayList<String> readExcelData(FileInputStream fs) throws IOException {
		ArrayList<String> measureValue = new ArrayList();
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				XSSFCell cell = row.getCell(0);
				if (cell != null) {
					// Found column and there is value in the cell.
					String value = cell.getStringCellValue();
					measureValue.add(value);

				}
			}
		}
		return measureValue;
	}

	// Method to read data from Json file
	public static List<Article> readJSONData(File file) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		List<Article> articles = Arrays.asList(mapper.readValue(file, Article[].class));
		System.out.println(articles);
		return articles;

	}

	// method to read data from xml file
	public static StudentList readXMLData(File xmlFile) throws IOException {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(StudentList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StudentList studentList = (StudentList) jaxbUnmarshaller.unmarshal(xmlFile);
			return studentList;

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
