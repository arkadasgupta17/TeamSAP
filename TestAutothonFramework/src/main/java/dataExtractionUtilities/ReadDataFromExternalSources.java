package dataExtractionUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadDataFromExternalSources {

	//Method to read data from excel file
		public static String readExcelData(FileInputStream fs, int rowIndex, int columnIndex) throws IOException {
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(0);
			if (rowIndex <= sheet.getLastRowNum()) {
				row = sheet.getRow(rowIndex);
				if (row != null) {
					XSSFCell cell = row.getCell(0);
					if (cell != null) {
						// Found column and there is value in the cell.
						String value = cell.getStringCellValue();
						workbook.close();
						return value;

					}
				}
			}
			workbook.close();
			return null;
		}

	// Method to read data from Json file
	public static String readJSONData(File file, int index, String field) throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> jsonObjects = mapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
		});
		if (index < jsonObjects.size()) {
			return (String) jsonObjects.get(index).get(field);
		}
		return null;

	}

	// method to read data from xml file
	public static String readXMLData(File xmlFile, int index, String tagName)
			throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(xmlFile);

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		Element root = document.getDocumentElement();
		System.out.println(root.getNodeName());

		// Get all elements
		NodeList nList = document.getElementsByTagName(root.getNodeName());
		Node node = nList.item(0);

		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) node;
			return eElement.getElementsByTagName(tagName).item(index).getTextContent();

		}
		return null;

	}

}
