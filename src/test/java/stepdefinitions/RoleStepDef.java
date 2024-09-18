package stepdefinitions;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

public class RoleStepDef {
    Response response= Common.getResponse();

    @And("each permission in the response should contain {string}")
    public void eachPermissionInTheResponseShouldContain(String fieldName) {

    }
}
