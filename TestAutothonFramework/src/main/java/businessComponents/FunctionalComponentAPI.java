package businessComponents;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertyConfiguration;

public class FunctionalComponentAPI {
	
	private final RequestSpecification requestSpecification;
	private final Method method;
	
	public FunctionalComponentAPI(String basePath, Method method) {
		this.requestSpecification = RestAssured.given()
								.and().filter(new RequestLoggingFilter())
								.and().baseUri(PropertyConfiguration.getConfig().baseUri())
								.and().basePath(basePath);
		this.method = method;
	}
	
	public FunctionalComponentAPI(String baseUri, String basePath, Method method) {
		this.requestSpecification = RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri(PropertyConfiguration.getConfig().baseUri())
				.and().basePath(basePath);
		this.method = method;
	}

	public FunctionalComponentAPI() {
		this.requestSpecification = null;
		this.method = null;
	}

	public void setBody(Object body) {
		this.requestSpecification.body(body);
	}
	
	public void setHeader(String headerName, String value) {
		this.requestSpecification.header(headerName, value);
	}
	
	public void setContentType(ContentType contentType) {
		this.requestSpecification.contentType(contentType);
	}
	
	public void logResponse() {
		this.requestSpecification.filter(new ResponseLoggingFilter());
	}
	
	public void setPathParams(String paramName, Object value, Object... pathParamValuePairs) {
		this.requestSpecification.pathParams(paramName, value, pathParamValuePairs);
	}
	
	public Response sendRequest() {
		switch (this.method) {
		case GET:
			return this.requestSpecification.when().get();
		case PUT:
			return this.requestSpecification.when().put();
		case POST:
			return this.requestSpecification.when().post();
		case DELETE:
			return this.requestSpecification.when().delete();
		case PATCH:
			return this.requestSpecification.when().patch();
		default:
			throw new IllegalStateException("Unexpected value: " + method);
		}
	}
	
	public String getStringResponseFromGetAPIandCheckStatusCode(String baseUri, int statusCode) {
		return RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri(baseUri)
				.and().get()
				.then().statusCode(statusCode)
				.extract().asPrettyString();
	}
	
	public String getStringResponseFromGetAPIQueryParamsandCheckStatusCode(String baseUri, int statusCode, 
			String queryName, String queryValue) {
		return RestAssured.given()
				.and().filter(new RequestLoggingFilter())
				.and().baseUri(baseUri)
				.and().queryParam(queryName, queryValue)
				.and().get()
				.then().statusCode(statusCode)
				.extract().asPrettyString();
	}
	
	public Document convertStringToXMLDocument(String xmlString) {
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
	
	public String getHtmlTitle(Document doc) {
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
	
	public String getAnchorTagUrl(Document doc) {
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
