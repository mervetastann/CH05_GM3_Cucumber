package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pojos.UserPojo;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

public class Common {

    private static Response response;

    public static void setResponse(Response response) {
        Common.response = response;
    }

    public static Response getResponse() {
        return response;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the Content-Type should be {string}")
    public void theContentTypeShouldBe(String expectedContentType) {
        assertThat("Expected Content-Type: " + expectedContentType + " but found: " + response.getContentType(),
                response.getContentType(), equalTo(expectedContentType));
    }

    @And("the response have a field {string} with value {int}")
    public void theResponseHaveAFieldWithValue(String fieldName, int value) {
        response.then().body(fieldName, equalTo(value));
    }

    @Then("the response data length should be {int}")
    public void theResponseDataLengthShouldBe(int expectedLength) {
        List<Object> responseData = response.jsonPath().getList("");
        Assert.assertEquals(expectedLength, responseData.size());
        response.then().body("", hasSize(expectedLength));
    }

    @And("print response body")
    public void printResponseBody() {
        response.prettyPrint();
    }

    @And("the response must have a field {string} with value {string}")
    public void theResponseMustHaveAFieldWithValue(String fieldName, String expectedValue) {
        response.then().body(fieldName, equalTo(expectedValue));
    }
}
