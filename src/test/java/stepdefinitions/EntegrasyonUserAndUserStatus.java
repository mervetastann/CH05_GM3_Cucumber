package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.UserPojo;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;


public class EntegrasyonUserAndUserStatus {

    private Response response;
    private static UserPojo userPojo = UserInfoSteps.getUserPojo();//encu

    @And("save a random status id from the response")
    public void saveARandomStatusIdFromTheResponse() {
        List<Map<String, Object>> statuses = response.jsonPath().getList("");

        Random random = new Random();//random bi statu tutmak icin
        Map<String, Object> randomStatus = statuses.get(random.nextInt(statuses.size()));

        // Extract and set the status ID
        Object statusIdObject = randomStatus.get("id");
        if (statusIdObject == null) {
            throw new RuntimeException("Status ID is null. Ensure the response has an 'id' field.");
        }
        String statusId = statusIdObject.toString();

        if (userPojo != null) {
            userPojo.setSub_status_id(statusId);
            System.out.println("Saved Status ID: " + statusId);
        } else {
            throw new RuntimeException("UserPojo is not available.");
        }
    }

    @When("I send a for status GET request to {string}")
    public void iSendAForStatusGETRequestTo(String arg0) {
        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(arg0);
      //  response.prettyPrint();
    }

    @When("I send a POST request with status to {string} with the following body")
    public void iSendAPOSTRequestWithStatusToWithTheFollowingBody(String endpoint, String body) {

        String orgId = userPojo.getOrganization_id() != null ? String.valueOf(userPojo.getOrganization_id()) : "default_org_id";
        String appId = userPojo.getApp_id() != null ? String.valueOf(userPojo.getApp_id()) : "default_app_id";
        String statusId = userPojo.getSub_status_id() != null ? userPojo.getSub_status_id() : "default_status_id";

        String updatedBody = body
                .replace("{{org_ID}}", orgId)
                .replace("{{app_ID}}", appId)
                .replace("{{status_ID}}", statusId);

        this.response = given()
                .contentType("application/json")
                .body(updatedBody)
                .post(endpoint);


        String newUserId = response.jsonPath().getString("id");
        if (newUserId != null) {
            userPojo.setSavedUserId(newUserId);
        } else {
            throw new RuntimeException("New user ID not found in response.");
        }
    }

    @And("the response entegrasyon should have a field {string} with value {string}")
    public void theResponseEntegrasyonShouldHaveAFieldWithValue(String arg0, String arg1) {
        String actualValue = response.jsonPath().getString(arg0);
        Assert.assertEquals(arg1, actualValue);
    }

    @When("I send a PUT request for entegrasyon to {string} with the following body")
    public void iSendAPUTRequestForEntegrasyonToWithTheFollowingBody(String endpoint, String body) {
        System.out.println("userPojo.getSub_status_id() = " + userPojo.getSub_status_id());
        // Gönderilecek
        String updatedBody = body
                .replace("{{id}}", userPojo.getSavedUserId())
                .replace("{{statusId}}", userPojo.getSub_status_id())
                .replace("{{org_ID}}", userPojo.getOrganization_id())
                .replace("{{app_ID}}", userPojo.getApp_id());

        // PUT
        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .put(endpoint);



    }

    @And("the response should have a field entg {string} with value {string}")
    public void theResponseShouldHaveAFieldEntgWithValue(String arg0, String arg1) {
        String actualValue = response.jsonPath().getString(arg0);
        Assert.assertEquals(arg1, actualValue);
    }

    @When("I send a DELETE  entegrasyon request to {string}")
    public void iSendADELETEEntegrasyonRequestTo(String arg0) {
        System.out.println("Silinecek user ID: " + userPojo.getSavedUserId() + " ve status ID: " + userPojo.getSub_status_id());
        String updatedEndpoint = arg0.replace("{{id}}", userPojo.getSavedUserId());

        this.response = given()
                .contentType("application/json")
                .delete(updatedEndpoint);

        response.prettyPrint();
    }

}