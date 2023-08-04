package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.hamcrest.core.Is;

import groovyjarjarantlr4.v4.runtime.dfa.HashEdgeMap;

public class BDDstyleMethod {

    public static void SimpleGETPost(String postNumber){
        given().contentType(ContentType.JSON).
            when().get(String.format("http://localhost:3000/posts/%s",postNumber)).
                then().body("author", is("typicode"));

    }
    public static void PerformContainsCollection(){
        given().contentType(ContentType.JSON).
                when().get("http://localhost:3000/posts/").
                then().body("author", containsInAnyOrder("typicode","Kirtan"))
                .statusCode(200);

    }
    public static void PerformPathParameter(){
        given().contentType(ContentType.JSON).
                with()
                .pathParam("post",1).
                when()
                .get("http://localhost:3000/posts/{post}").
                then()
                .body("author", is("typicode"))
                .statusCode(200);

    }

    public static void PerformQueryParameter(){
        given().contentType(ContentType.JSON)
                .queryParam("id",1).
                when()
                .get("http://localhost:3000/posts/").
                then()
                .body("author", containsInAnyOrder("typicode"))
                .statusCode(200);

    }
    
    public static void performPOSTwithBodyParamters() {
    	
    	HashMap<String, String> postContentHashMap = new HashMap<>();
    	postContentHashMap.put("id", "7");
    	postContentHashMap.put("title", "Rest Assured POST Example");
    	postContentHashMap.put("author", "shrey");
    	given().contentType(ContentType.JSON)
    	.with()
    	.body(postContentHashMap)
        .when()
        .post("http://localhost:3000/posts/").
        then()
        .body("author", Is.is("shrey"));

	}


}
