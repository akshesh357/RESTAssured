package main.utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.CoreMatchers.nullValue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestAssuredExtension {

    public static RequestSpecification Request;
    public RestAssuredExtension(){

        //Arrange
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://localhost:3000/");
        requestSpecBuilder.setContentType(ContentType.JSON);
        var requestSpec = requestSpecBuilder.build();
        Request = RestAssured.given().spec(requestSpec);


    }
    public static  void GetOpsWithParameter(String url, Map<String, String> pathParams) throws URISyntaxException {
        //Act
        Request.pathParams(pathParams);
        Request.get(new URI(url));
    }

    public static ResponseOptions<Response> GetOps(String url) throws URISyntaxException {
        //Act
         return Request.get(new URI(url));
    }
    
    public static ResponseOptions<Response> PostOpsWithBodayAndParameter(String url, Map<String, String> pathParam, Map<String, String> body) {
		Request.pathParams(pathParam);
		Request.body(body);
		
		return Request.post(url);

    	
    }
    public static ResponseOptions<Response> DeleteOpsWithPathParams(String url,Map<String, String> pathParams)  {
        Request.pathParams(pathParams);
        return Request.delete(url);
    }
    public static ResponseOptions<Response> GetWithPathParams(String url,Map<String, String> pathParams)  {
        Request.pathParams(pathParams);
        return Request.get(url);
    }
    
    public static ResponseOptions<Response> PostOpsWithBody(String url,Map<String, String> body)  {
        Request.body(body);
        return Request.post(url);
    }
	public static ResponseOptions<Response> PUTOpsWithBodyAndPathParams(String url, HashMap<String, String> body, HashMap<String, String> pathParams) {
		Request.pathParams(pathParams);
        Request.body(body);
        return Request.put(url);
		
	}
	
	public static ResponseOptions<Response> GetOpsWithToken(String url, String token) {
        //Act
        try {
            Request.header(new Header("Authorization", "Bearer " + token));
            return Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
	}
}

