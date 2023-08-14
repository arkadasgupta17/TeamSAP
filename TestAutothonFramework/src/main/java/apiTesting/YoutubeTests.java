package apiTesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class YoutubeTests {
	
	@Test
	public void wikipediaContentReadTest() {
		
		// open google
		String googleRes = RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri("https://www.google.com")
				.and().get()
				.then().statusCode(200)
				.extract().asPrettyString();
		
		Document doc = convertStringToXMLDocument(googleRes);
		String title = getHtmlTitle(doc);
		assertEquals(title, "Google");
		
		// search wikipedia in google
		String searchRes = RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri("https://www.google.com/search")
				.and().queryParam("q", "youtube")
				.and().get()
				.then().statusCode(200)
				.extract().asPrettyString();

		Document doc2 = convertStringToXMLDocument(searchRes);
		String title2 = getHtmlTitle(doc2);
		assertTrue(title2.contains("youtube"));
		String link = getAnchorTagUrl(doc2);
		link = link.substring((link.indexOf("?q=") + 3), link.indexOf("/&"));
		System.out.println("Link: " + link);
		
		String youtubeRes = RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri("https://www.youtube.com/")
				.and().get()
				.then().statusCode(200)
				.extract().asPrettyString();

		Document doc3 = convertStringToXMLDocument(youtubeRes);
		String title3 = getHtmlTitle(doc3);
		assertTrue(title3.contains("YouTube"));
		
		String youTubeSearch = RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri("https://www.youtube.com/results")
				.and().queryParam("search_query", "selenium")
				.and().get()
				.then().statusCode(200)
				.extract().asPrettyString();
	}
	
	private static Document convertStringToXMLDocument(String xmlString) {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = null;
	    try {
	      builder = factory.newDocumentBuilder();
	      Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
	      return doc;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	private static String getHtmlTitle(Document doc) {
		String title = "";
		try {
			NodeList headNode = doc.getElementsByTagName("head");
			NodeList childNodes = headNode.item(0).getChildNodes();
			for (int index = 0; index < childNodes.getLength(); index++) {
				if (childNodes.item(index).getNodeName().equalsIgnoreCase("title")) {
					title = childNodes.item(index).getTextContent();
				}
			}
		} catch(Exception e) {
			title = ""; 
		}
		return title;
	}
	
	private static String getAnchorTagUrl(Document doc) {
		String link = "";
		try {
			NodeList anchorNodeList = doc.getElementsByTagName("a");
			for (int index = 0; index < anchorNodeList.getLength(); index++) {
				NamedNodeMap attributes = anchorNodeList.item(index).getAttributes();
				if (attributes.getNamedItem("href").getNodeValue().contains("www.youtube.com")) {
					return attributes.getNamedItem("href").getNodeValue();
				}
			}
		} catch(Exception e) {
			link = ""; 
		}
		return link;
	}

}
