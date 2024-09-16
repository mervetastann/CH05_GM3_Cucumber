package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.UserPojo;
import base_urls.GMBaseUrl;

public class UserInfoSteps {

    private Response response;
    private static UserPojo userPojo;

    public static UserPojo getUserPojo() {
        return userPojo;
    }

    @Given("I set the base specification for User Info API")
    public void iSetTheBaseSpecificationForUserInfoAPI() {
        GMBaseUrl.setUserInfoSpec();
        RestAssured.requestSpecification = GMBaseUrl.spec;
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = RestAssured.given().when().get(endpoint);
        Common.setResponse(response);
    }

    @And("I save the {string} from the response")
    public void iSaveFieldFromTheResponse(String fieldName) {
        String fieldValue = response.jsonPath().getString(fieldName);
        if (userPojo == null) {
            userPojo = new UserPojo();
        }

        switch (fieldName) {
            case "sub_app":
                userPojo.setApp_id(fieldValue);
                break;
            case "sub_default_org_id":
                userPojo.setOrganization_id(fieldValue);
                break;
            case "sub_default_subscription_id":
                userPojo.setSubscription_id(fieldValue);
                break;
            case "id":
                userPojo.setSavedUserId(fieldValue);
                break;
            default:
                throw new IllegalArgumentException("Unexpected field name: " + fieldName);
        }
    }

    @And("I print the response body")
    public void iPrintTheResponseBody() {
        if (response != null) {
            System.out.println("Response Body: " + response.getBody().asString());
        } else {
            System.out.println("Response is null. Cannot print the response body.");
        }
    }
}
