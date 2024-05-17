package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.junit.Assert.*;

public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
    public ResponseBody body;
    public JSONObject requestParams;
    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint(){
        RestAssured.baseURI = "https://fakestoreapi.com/";

    }
    @When("I pass the url in the request")
    public void i_pass_the_url_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }
    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 200);
        System.out.println("Response OK Code is: "+ResponseCode);
    }
    @When("I pass the incorrect url in the request")
    public void i_pass_the_incorrect_url_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("product");
    }
    @Then("I receive the invalid response code as {int}")
    public void i_receive_the_invalid_response_code_as(Integer int1) {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 404);
        System.out.println("Response invalid Code is: "+ResponseCode);
    }

    @Then("I verify that the rate of the first product is {}")
    public void i_verify_that_the_rate_of_the_first_product_is(String rate) {
        //get the response body first
        body = response.getBody();

        //convert response body to string
        String responseBody = body.asString();

        //this is a json representation from response body
        JsonPath path = response.jsonPath();

        //looking for object "rating"
        String s = path.getJsonObject("rating[0].rate").toString();

        assertEquals(rate, s);
    }
    @Given("I hit the url of post product api endpoint")
    public void i_hit_the_url_of_post_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();
    }
    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitle(String title) {
        requestParams.put("title",title);
        requestParams.put("price", 15.5);
        requestParams.put("description","sky blue all star");
        requestParams.put("image","https://conlog.co.za");
        requestParams.put("category","electronic");
        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        ResponseBody body = response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAs(String id) {
        JsonPath path = response.jsonPath();

        //extract the id at index 19 from the JSON array
        String s = path.getString("id[19]");

        //check if extracted id matches the expected id
        assertEquals("20", s);
        System.out.println(s);
    }

    @Given("I hit the url of put product api endpoint")
    public void iHitTheUrlOfPutProductApiEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        requestParams = new JSONObject();
    }

    @When("I pass the url of products in the request with {}")
    public void iPassTheUrlOfProductsInTheRequestWith(String productnumber) {
        httpRequest = RestAssured.given();

        requestParams.put("title","Test title product");
        requestParams.put("price", "36.6");
        requestParams.put("description","sky blue all star");
        requestParams.put("image","https://conlog.co.za");
        requestParams.put("category","electronic");

        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/"+ productnumber);
        ResponseBody body = response.getBody();
        JsonPath path = response.jsonPath();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }
}
