package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class OrganizationServiceStepDef {
    Response response = Common.getResponse();

    @Then("user should check if Incoming id has expected id")
    public void user_should_check_if_ıncoming_id_has_expected_id() {
        long expectedOrgId = Long.parseLong("1724253527891397");
        response.then().body("id", equalTo(expectedOrgId));
    }

    @And("Ensure totalUsers is greater than Zero")
    public void ensureTotalUsersIsGreaterThanZero() {

        response.then().body("user_list.size()", greaterThan(0));
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> userList = jsonPath.getList("user_list");

        for (Map<String, Object> user : userList) {
            System.out.println(user);
        }

    }

    @And("Validate if group_types list has {int} items")
    public void validateIfGroup_typesListHasItems(int size) {

        response.then().body("group_types.size()", equalTo(size));

        // Grup tiplerini yazdırma
        List<Map<String, Object>> groupTypes = response.jsonPath().getList("group_types");
        System.out.println("groupTypes = " + groupTypes);

        // ID'leri yazdırma
        for (Map<String, Object> groupType : groupTypes) {
            System.out.println("groupTypeid = " + groupType.get("id"));
        }
    }
}

