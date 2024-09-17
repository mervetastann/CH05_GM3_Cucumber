package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MembershipTypeStepDef {


    Response response = Common.getResponse();

    @Then("the user ID should be 5")
    public void the_user_ID_should_be_5() {
        // Verify that the user ID in the response is 5
        JsonPath jsonPath = response.jsonPath();
        int userId = jsonPath.getInt("id");
        assertEquals(5, userId);
    }


    @And("the name field should be {string}")
    public void theNameFieldShouldBe(String expectedName) {
        JsonPath jsonPath = response.jsonPath();

        // Eğer 'name' alanı bir liste içeriyorsa
        List<String> names = jsonPath.getList("name"); // 'name' alanının bir liste olduğunu varsayalım

        // Beklenen adın bu listede olup olmadığını kontrol ediyoruz
        boolean containsExpectedName = names.contains(expectedName);

        // 'name' listesinde beklenen adın olup olmadığını doğruluyoruz
        assertTrue("The name field does not contain the expected name!", containsExpectedName);
    }


    @Then("the user ID should be 6")
    public void the_user_ID_should_be_6() {
        // Verify that the user ID in the response is 6
        JsonPath jsonPath = response.jsonPath();
        int userId = jsonPath.getInt("id");
        assertEquals(6, userId);
    }


    @And("the body attribute count should be less than or equal to {int}")
    public void theBodyAttributeCountShouldBeLessThanOrEqualTo(int expectedCount) {
        // Kök elemanın bir liste olup olmadığını kontrol ediyoruz
        List<Map<String, Object>> jsonResponse = response.jsonPath().getList("$");

        // Attribute sayısını liste içindeki her bir eleman için ayrı ayrı kontrol edebiliriz
        int actualCount = jsonResponse.size(); // Listenin boyutunu alıyoruz

        // Beklenen ve gerçek attribute sayısını karşılaştırıyoruz
        assertTrue("The body attribute count should be less than or equal to the expected count", actualCount <= expectedCount);
    }


    @And("the user verifies that the length of the roles is {int}")
    public void theUserVerifiesThatTheLengthOfTheRolesIs(int arg0) {
        if (response == null) {
            throw new IllegalStateException("Response is not initialized");
        }
        JsonPath jsonPath = response.jsonPath();
        List<String> roles = jsonPath.getList("roles");
        assertEquals(arg0, roles.size());
    }

    @And("the response should have a area {string} with value {string}")
    public void theResponseShouldHaveAAreaWithValue(String area, String value) {
        response.then().body(area, equalTo(value));
    }



}
