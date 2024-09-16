package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class PermissionSteps {

    Response response= Common.getResponse();



    @Then("each permission in the response should contain {string} and {string} fields")
    public void eachPermissionShouldContainFields(String field1, String field2) {
        List<Map<String, Object>> permissions = response.jsonPath().getList("$");
        for (Map<String, Object> permission : permissions) {
            assertTrue("Field '" + field1 + "' is missing in permission: " + permission, permission.containsKey(field1));
            assertTrue("Field '" + field2 + "' is missing in permission: " + permission, permission.containsKey(field2));
        }
    }


    @Then("the {string} in the response should only contain values {string}")
    public void theFieldInTheResponseShouldOnlyContainValues(String fieldName, String expectedValues) {
        List<String> actualValues = response.jsonPath().getList(fieldName);
        String[] allowedValues = expectedValues.split(", ");
        for (String value : actualValues) {
            assertTrue("Unexpected value found: " + value, List.of(allowedValues).contains(value));
        }
    }



    @And("the app_id in the response should only contain values {int}, {int}, or {int}")
    public void theApp_idInTheResponseShouldOnlyContainValuesOr(int id1, int id2, int id3) {
        List<Integer> validAppIds = Arrays.asList(id1, id2, id3);

        // Yanıttaki her bir öğenin app_id değerlerini liste olarak al
        List<Integer> appIds = response.jsonPath().getList("app_id");

        // Her bir app_id'nin belirtilen değerlerden birine sahip olup olmadığını kontrol et
        for (Integer appId : appIds) {
            assertTrue("Invalid app_id: " + appId, validAppIds.contains(appId));
        }
    }


}
