package stepdefinitions;

import base_urls.GMBaseUrl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.UserPojo;

public class UserServiceStepDef {
//    private static UserPojo userPojo = new UserPojo(); // Ensure this is shared across steps
    private static Response response;

    public static void setResponse(Response response) {
        UserServiceStepDef.response = response;//encup:niye?bu userpojoya cağırıyorum baska bir clasa cagırınca ordaki userpojoyla karısmıyor. hali hazirda bir userpojo ,oop
    }

    public static Response getResponse() {
        return response;
    }

    @Given("I set the base specification for GM API")
    public void iSetTheBaseSpecificationForGMAPI() {
        GMBaseUrl.setSpec();
        RestAssured.requestSpecification = GMBaseUrl.spec;
    }

    @When("I send a POST request to {string} with the following body")
    public void iSendAPOSTRequestToWithTheFollowingBody(String endpoint, String body) {
        UserPojo userPojo = UserInfoSteps.getUserPojo();

        String updatedBody = body
                .replace("{{org_ID}}", String.valueOf(userPojo.getOrganization_id()))
                .replace("{{app_ID}}", String.valueOf(userPojo.getApp_id()))
                .replace("{{sub_ID}}", userPojo.getSubscription_id());
        //        String orgId =userPojo.getOrganization_id();
//        String appId =userPojo.getApp_id();


        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .post(endpoint);


        //id icin
        String newUserId = response.jsonPath().getString("id");
        if (newUserId != null) {
            userPojo.setSavedUserId(newUserId); // Update userPojo with the new user ID
        } else {
            throw new RuntimeException("New user ID not found in response.");
        }
        UserGroupServicesStepDef.setResponse(response); // Set response for further steps
    }

    @When("I send a PUT request to {string} with the following body")
    public void iSendAPUTRequestToWithTheFollowingBody(String endpoint, String body) {
        UserPojo userPojo = UserInfoSteps.getUserPojo();
        String updatedBody = body
                .replace("{{id}}", userPojo.getSavedUserId())
                .replace("{{org_ID}}", String.valueOf(userPojo.getOrganization_id()))
                .replace("{{app_ID}}", String.valueOf(userPojo.getApp_id()))
                .replace("{{sub_ID}}", userPojo.getSubscription_id());

        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .put(endpoint);

    }


    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        UserPojo userPojo = UserInfoSteps.getUserPojo();
//        System.out.println("userPojo.getSavedUserId() = " + userPojo.getSavedUserId());
        String updatedEndpoint = endpoint.replace("{{id}}", userPojo.getSavedUserId());
        this.response = RestAssured.delete(updatedEndpoint);

    }

    @Then("the response should have a field {string} with value {string}")
    public void theResponseShouldHaveAFieldWithValue(String fieldName, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldName);
        Assert.assertEquals(expectedValue, actualValue);

    }


}