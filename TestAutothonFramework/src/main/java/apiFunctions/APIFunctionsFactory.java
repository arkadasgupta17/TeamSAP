package apiFunctions;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIFunctionsFactory {
	
	
	public Response post(String uri, String requestBody) {
		return RestAssured.given().contentType(ContentType.JSON).body(requestBody).post(uri);
	}

	public Response getWithHeadersAndQueryParams(String uri, Map<String, String> headers,
			Map<String, String> queryParams) {
		return RestAssured.given().headers(headers).queryParams(queryParams).get(uri);
	}

	public Response getMethod(String uri) {
		return RestAssured.given().contentType(ContentType.JSON).when().get(uri);
	}
	
	public boolean verifyStatusCode(Response response, int statusCode) {
		 if(response.getStatusCode()== statusCode) {
			 return true;
		 } else {
			 return false;
		 }
	}

}
