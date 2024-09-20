package stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class MembershipStepDef {


    Response response = Common.getResponse();

    @And("the response count is displayed")
    public void theResponseCountIsDisplayed() {
        int actualSize = response.jsonPath().getList("user_id").size();
        System.out.println("The size of user_id list is: " + actualSize);

    }


    @And("the username field should be {string}")
    public void theUsernameFieldShouldBe(String expecteduserName) {

        JsonPath jsonPath = response.jsonPath();

        // Eğer 'username' alanı bir liste içeriyorsa
        List<String> usernames = jsonPath.getList("username"); // 'username' alanının bir liste olduğunu varsayalım

        // Beklenen adın bu listede olup olmadığını kontrol ediyoruz
        boolean containsExpecteduserName = usernames.contains(expecteduserName);

        // 'name' listesinde beklenen adın olup olmadığını doğruluyoruz
        assertTrue("The username field contain the expected username", containsExpecteduserName);
    }

    @And("the response should contain {string} in all objects")
    public void the_response_should_contain_in_all_objects(String key) {
        // Yanıtı JSON formatında alıyoruz
        List<Map<String, Object>> jsonData = response.jsonPath().getList("$");

        // Yanıtın bir dizi olduğunu doğrula
        assertNotNull("Response is not an array", jsonData);

        // Her bir nesnede belirtilen anahtarın (user_id) olup olmadığını kontrol ediyoruz
        jsonData.forEach(item -> {
            assertTrue("Object does not contain '" + key + "' key", item.containsKey(key));
        });
    }

    @And("the data length should be greater than 0")
    public void the_data_length_should_be_greater_than() {
        int expectedCount =0;
        List<Map<String, Object>> jsonResponse = response.jsonPath().getList("$");

        int actualCount = jsonResponse.size(); // Listenin boyutunu alıyoruz

        assertTrue("The body attribute should be greater than 0", actualCount > expectedCount);
    }

    @And("the response should have a area {string} with value true")
    public void theResponseShouldHaveAAreaWithValueTrue(String arg0) {

        JsonPath jsonPath = response.jsonPath();
        boolean areaValue = jsonPath.getBoolean(arg0);
        assertTrue(areaValue);
    }
}
