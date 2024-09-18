package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MembershipStepDef {


    Response response = Common.getResponse();

    @And("the response count is displayed")
    public void theResponseCountIsDisplayed() {
        int actualSize = response.jsonPath().getList("user_id").size();
        System.out.println("The size of user_id list is: " + actualSize);

    }



}
