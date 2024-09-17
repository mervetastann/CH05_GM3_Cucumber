package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;


public class EntegrasyonUserUserGroup {

    private static UserPojo userPojo = UserInfoSteps.getUserPojo(); // Ensure this is shared across steps
    private Response response;

    @When("I take user id send with a POST request for group to {string} with the following body")
    public void iTakeUserIdSendWithAPOSTRequestForGroupToWithTheFollowingBody(String endpoint, String body) {
        String updatedBody = body.replace("\"id\": {{id}}", "\"id\": " + UserInfoSteps.getUserPojo().getSavedUserId())  // iki tane id var o yuzden boyle
                .replace("{{org_ID}}", UserInfoSteps.getUserPojo().getOrganization_id());  // orgId dinamik aldım

        // POST
        response = given()
                .contentType("application/json")
                .body(updatedBody)
                .post(endpoint);


        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("POST Status Code: " + response.getStatusCode());

        if (response.getStatusCode() == 201) {
            String groupId = response.jsonPath().getString("id");
            String groupTypeId = response.jsonPath().getString("group_type_id");
            UserInfoSteps.getUserPojo().setGroupId(groupId);
            UserInfoSteps.getUserPojo().setGroup_type_id(groupTypeId);
        } else {
            throw new RuntimeException("Failed to create user group. Status code: " + response.getStatusCode());
        }

        UserGroupServicesStepDef.setResponse(response); // Set the response
    }

    @And("get and save information from field after create {string}")
    public void getAndSaveInformationFromFieldAfterCreate(String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldName);

        if (fieldValue != null) {
            if (fieldName.equals("id")) {
                userPojo.setGroupId(fieldValue);
            } else if (fieldName.equals("group_type_id")) {
                userPojo.setGroup_type_id(fieldValue);
            } else {
                throw new RuntimeException("Invalid field name. Supported fields are 'id' and 'group_type_id'.");
            }

            // Save and print the field value
            System.out.println("Saved " + fieldName + ": " + fieldValue);
        } else {
            System.out.println("Field '" + fieldName + "' not found in the response.");
        }
    }

}