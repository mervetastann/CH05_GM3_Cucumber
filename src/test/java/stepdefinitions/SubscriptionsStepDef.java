package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class SubscriptionsStepDef {
    Response response = Common.getResponse();

    @Then("Log All User ID Values")
    public void log_all_user_ıd_values() {
        List<Map<String, Object>> jsonData = response.as(List.class);
        for (Map<String, Object> item : jsonData) {
            System.out.println("User ID: " + item.get("user_id"));
        }
    }

    @And("Get User by ID {int}")
    public void getUserByID(int id) {
        List<Map<String, Object>> jsonData = response.as(List.class);

        for (Map<String, Object> item : jsonData) {
            if (item.get("id").equals(673)) {
                System.out.println(item);}
        }
    }

    @And("Check if no user with id 100 exists")
    public void checkIfNoUserWithid100exist() {
        List<Integer> userIds = response.jsonPath().getList("id");
        Assert.assertFalse("User with id 100 should not exist", userIds.contains(100));}


}
