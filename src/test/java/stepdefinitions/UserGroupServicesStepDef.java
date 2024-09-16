package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.UserPojo;

import static org.hamcrest.Matchers.equalTo;

public class UserGroupServicesStepDef {

    Response response = Common.getResponse();
    UserPojo userPojo = new UserPojo();  // Pojo sınıfını burada initialize ediyoruz

    @And("get and save information from field {string}")
    public void getAndSaveInformationFromField(String fieldName) {
        // İlgili alanın değerini JSON response'dan alıyoruz
        String fieldValue = response.jsonPath().getString(fieldName);

        // Kaydetmek istediğimiz alan "id" ise, Pojo'ya kaydediyoruz
        if (fieldName.equals("id")) {
            userPojo.setSavedGroupId(fieldValue);
        }
        // Eğer başka bir alan varsa ona göre işlem yapabilirsiniz
        // Örneğin:
         else if (fieldName.equals("group_type_id")) {
             userPojo.setSavedGroup_type_id(fieldValue);
         }
    }
    @When("I send a POST request for group to {string} with the following body")
    public void i_send_a_post_request_for_group_to_with_the_following_body(String endpointim, String bodym) {

        if (userPojo == null) {
            throw new RuntimeException("User information not available. Ensure GET request is made first.");
        }

        this.response = RestAssured.given()
                .contentType("application/json")
                .body(bodym)
                .post(endpointim);

        // Print response for debugging
        System.out.println("POST Response Body: " + response.getBody().asString());

        // Yanıttan yeni grup ID'sini al ve Pojo'ya kaydet
        String groupId = response.jsonPath().getString("id");
        if (groupId != null) {
            userPojo.setSavedGroupId(groupId); // Yeni grup ID'sini Pojo'ya kaydet
        } else {
            throw new RuntimeException("New group ID not found in response.");
        }
    }


    @And("the response group should have a field {string} with value {string}")
    public void theResponseGroupShouldHaveAFieldWithValue(String arg0, String arg1) {
        response.then().body(arg0, equalTo(arg1));
    }
}
