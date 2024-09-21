package stepdefinitions;

import base_urls.GMBaseUrl;
import io.cucumber.java.en.And;
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
    public static String savedValue;

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

        if (userPojo == null) {
            throw new RuntimeException("User information not available. Ensure GET request is made first.");
        }
        String updatedBody = body
                .replace("{{org_ID}}", String.valueOf(userPojo.getOrganization_id()))
                .replace("{{app_ID}}", String.valueOf(userPojo.getApp_id()))
                .replace("{{sub_ID}}", userPojo.getSubscription_id());

        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .post(endpoint);

        // Print response for debugging
        System.out.println("POST Response Body: " + response.getBody().asString());

        // Save the new user ID from the response
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
        if (userPojo == null || userPojo.getSavedUserId() == null) {
            throw new RuntimeException("User ID is null. Ensure a POST request is made first.");
        }
        String updatedBody = body
                .replace("{{id}}", userPojo.getSavedUserId())
                .replace("{{org_ID}}", String.valueOf(userPojo.getOrganization_id()))
                .replace("{{app_ID}}", String.valueOf(userPojo.getApp_id()))
                .replace("{{sub_ID}}", userPojo.getSubscription_id());

        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .put(endpoint);

        // Print response for debugging
        System.out.println("PUT Response Body: " + response.getBody().asString());

    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        UserPojo userPojo = UserInfoSteps.getUserPojo();

        System.out.println("userPojo.getSavedUserId() = " + userPojo.getSavedUserId());

        if (userPojo == null || userPojo.getSavedUserId() == null) {
            throw new RuntimeException("User ID is null. Ensure a POST request is made first.");
        }
        String updatedEndpoint = endpoint.replace("{{id}}", userPojo.getSavedUserId());
        this.response = RestAssured.delete(updatedEndpoint);

        // Print response for debugging
        System.out.println("DELETE Response Status Code: " + response.getStatusCode());
    }

    @Then("the response should have a field {string} with value {string}")
    public void theResponseShouldHaveAFieldWithValue(String fieldName, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldName);
        Assert.assertNotNull("Field '" + fieldName + "' not found in response.", actualValue);
        Assert.assertEquals(expectedValue, actualValue);

    }

    // Bilgiyi almak ve kaydetmek
    @And("get and saved information from field {string}")
    public void get_and_saved_information(String field) {
        savedValue = response.jsonPath().getString(field);
        System.out.println("Saved Value: " + savedValue);  // İsteğe bağlı olarak konsola yazdırabiliriz
    }


}