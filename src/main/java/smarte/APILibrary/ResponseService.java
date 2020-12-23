package smarte.APILibrary;

import static com.jayway.restassured.RestAssured.given;
import java.util.Map;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class ResponseService {
	
	public String baseURL;

	public enum RequestMethodType {
		POST, GET, DELETE, PUT
	}

	public Response getResponse(String url, RequestSpecification request, RequestMethodType methodType) {
		switch (methodType) {
		case POST:
			return request.post(url);
		case GET:
			return request.get(url);
		case DELETE:
			return request.delete(url);
		case PUT:
			return request.put(url);
		default:
			return null;
		}
	}

	public Response getResponse(String url, String jsonBody, Map<String, ? extends Object> queryParam,
			String contentType, Map<String, ? extends Object> headers, RequestMethodType methodType) {
		//this.baseURL = CommonUtils.getBaseURL();

		String requestURL = url;
		RequestSpecification request = given();

		if (jsonBody != null) {
			if (!jsonBody.equals(""))
				request = request.body(jsonBody);
		}

		if (queryParam != null) {
			if (!queryParam.isEmpty()) {
				request = request.queryParams(queryParam);
			}
		}
		request = request.when();

		if (contentType != null) {
			if (!contentType.equals(""))
				request = request.contentType(contentType);
		}

		if (headers != null) {
			if (!headers.isEmpty())
				request = request.headers(headers);
		}
		Response res = getResponse(requestURL, request, methodType);
		return res;
	}


}
