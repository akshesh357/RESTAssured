package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import main.utilities.RestAssuredExtension;
import pojo.Posts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.net.URISyntaxException;
import java.util.HashMap;

import javax.swing.plaf.TableHeaderUI;

import org.hamcrest.core.IsNot;

import  io.cucumber.datatable.DataTable;



public class GetPostSteps {

    private static ResponseOptions<Response> response;

    @Given("^I Perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable{
        response =  RestAssuredExtension.GetOpsWithToken(url, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InNocmV5QGVtYWlsLmNvbSIsInBhc3N3b3JkIjoiMTIzIiwiaWF0IjoxNjkxMDk2OTc5LCJleHAiOjE2OTExMDA1Nzl9.duxN_yfwuvbsk_XG5RJbgO5RXgWvBYncF4x_9hFK4ys");
//    given().contentType(ContentType.JSON); 

    }
    @And("^I perform GET for the post number \"([^\"]*)\"$")
    public void iPerformGETOperationForPostNumber(String postNumber) throws Throwable{
//        when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
//                then().body("author", is("typicode"));
        BDDstyleMethod.SimpleGETPost(postNumber);

    }

    @Then("^I should see the author Name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable{
    	var posts = response.getBody().as(Posts.class);
    	assertThat(posts.getAuthor(), equalTo("typicode"));
        //assertThat(response.getBody().jsonPath().get("author"), equalTo("typicode"));
//        BDDstyleMethod.PerformContainsCollection();
//        when().get(String.format("http://localhost:3000/posts/%s", url)).
//                then().body("author", is("Shrey"));

    }

    @Then("I perform Path parameter")
    public void iPerformPathParameter() {
        BDDstyleMethod.PerformPathParameter();
    }

    @Then("I perform Query parameter")
    public void iPerformQueryParameter() {
        BDDstyleMethod.PerformQueryParameter();
    }
    
    @Given("I Perform POST operation for {string}")
    public void i_perform_post_operation_for(String string) {
        BDDstyleMethod.performPOSTwithBodyParamters();
    }
    
    @Given("I Perform POST operation for {string} with body")
    public void i_perform_post_operation_for_with_body(String url, DataTable table) throws Exception {
        var data = table.cells();
        
        HashMap<String, String> body = new HashMap<>();
        body.put("name", data.get(1).get(0));
        
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", data.get(1).get(1));
        
        response = RestAssuredExtension.PostOpsWithBodayAndParameter(url, pathParams, body);
        
    }

    @Then("I should see the body has name as {string}")
    public void i_should_see_the_body_has_name_as(String name) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }
    
    
    @Given("I ensure to Perform POST operation for {string} with body as")
    public void i_ensure_to_perform_post_operation_for_with_body_as(String url, DataTable table) {
    	
    	var data = table.cells();
    	
    	HashMap<String, String> body = new HashMap<>();
    	body.put("id", data.get(1).get(0));
    	body.put("title", data.get(1).get(1));
    	body.put("author", data.get(1).get(2));
        
        RestAssuredExtension.PostOpsWithBody(url, body);
        
    }

    @Given("I Perform DELETE operation for {string}")
    public void i_perform_delete_operation_for(String url, io.cucumber.datatable.DataTable dataTable) {
    	 var data = dataTable.cells();
         HashMap<String, String> pathParams = new HashMap<>();
         pathParams.put("postid", data.get(1).get(0));
         RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @Given("I perform GET operation with path parameter for {string}")
    public void i_perform_get_operation_with_path_parameter_for(String url, DataTable dataTable) {
    	var data = dataTable.cells();

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    	
    }

    @Then("I should not see the body with title as {string}")
    public void i_should_not_see_the_body_with_title_as(String title) {
    	assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
    
    @Given("I Perform PUT operation for {string}")
    public void i_perform_put_operation_for(String url, DataTable dataTable) {
    	var data = dataTable.cells();

        HashMap<String, String> body = new HashMap<>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("postid", data.get(1).get(0));

        //Perform post operation
        RestAssuredExtension.PUTOpsWithBodyAndPathParams(url, body, pathParams);
    	
    }

    @Then("I should see the body with title as {string}")
    public void i_should_see_the_body_with_title_as(String title) {
        
    	assertThat(response.getBody().jsonPath().get("title"), is(title));
    }
    @Given("I perform authentication operation for {string} with body")
    public void i_perform_authentication_operation_for_with_body(String string, io.cucumber.datatable.DataTable dataTable) {
       
    }
    
    @Given("I perform GET operation with path parameter for address {string}")
    public void i_perform_get_operation_with_path_parameter_for_address(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see the street name as {string}")
    public void i_should_see_the_street_name_as(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

        
}
