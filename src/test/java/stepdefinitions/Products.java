package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.junit.Assert.*;

public class Products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
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
}
