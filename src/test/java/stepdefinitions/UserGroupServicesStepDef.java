package stepdefinitions;

import base_urls.GMBaseUrl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserGroupServicesStepDef {

    private static UserPojo userPojo = new UserPojo();

    // Response'u statik olarak tutmak için
    private static Response response;


    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        UserGroupServicesStepDef.response = response;
    }

    @And("get and save information from field {string}")
    public void getAndSaveInformationFromField(String fieldName) {
        // İlgili alanın değerini JSON response'dan alıyoruz
        String fieldValue = response.jsonPath().getString(fieldName);

        // Kaydetmek istediğimiz alan "id" ise, Pojo'ya kaydediyoruz
        if (fieldName.equals("id")) {
            userPojo.setGroupId(fieldValue);
        }
        // Eğer başka bir alan varsa ona göre işlem yapabilirsiniz
        // Örneğin:
        else if (fieldName.equals("group_type_id")) {
            userPojo.setGroup_type_id(fieldValue);
        }
    }


    @When("I send a POST request for group to {string} with the following body")
    public void i_send_a_post_request_for_group_to_with_the_following_body(String endpointim, String bodym) {
        // POST isteği yapılıyor
        this.response =given()
                .contentType("application/json")
                .body(bodym)
                .post(endpointim);

        // Response'u kaydediyoruz
        UserGroupServicesStepDef.setResponse(response);

        // Yanıtı ekrana yazdırma
        System.out.println("POST Response Body: " + response.getBody().asString());

        // Yanıttan `groupId` ve `group_type_id` alınıyor ve `userPojo`'ya kaydediliyor
        String groupId = response.jsonPath().getString("id");
        String groupTypeId = response.jsonPath().getString("group_type_id");

        if (groupId != null && groupTypeId != null) {
            // `userPojo`'yu güncelleme
            userPojo.setGroupId(groupId);
            userPojo.setGroup_type_id(groupTypeId);
        }  else  {
            System.out.println("Failed to create group. Status code: " + response.getStatusCode());
         //negatif senaryolarımızda bunu kullanıyoruz
        }
    }

    @When("I send a DELETE request for group to {string}")
    public void iSendADELETERequestForGroupTo(String endpoint) {

        // `userPojo`'nun null olup olmadığını ve `groupId`'nin set edilip edilmediğini kontrol ediyoruz
        if (userPojo == null || userPojo.getGroupId() == null) {
            throw new RuntimeException("Group ID is null. Ensure a POST request is made first.");
        }

        // `groupId`'yi endpoint içine yerleştiriyoruz
        String updatedEndpoint = endpoint.replace("{{id}}", userPojo.getGroupId());

        // DELETE isteğini yapıyoruz
        this.response = RestAssured.delete(updatedEndpoint);

        // Yanıtı kaydediyoruz
        UserGroupServicesStepDef.setResponse(response);

        // Yanıtı ekrana yazdırma
        System.out.println("DELETE Response Status Code: " + response.getStatusCode());

        // Yanıt kodunun doğru olup olmadığını kontrol ediyoruz
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to delete the group. Status code: " + response.getStatusCode());
        }
    }


    @And("the response group should have a field {string} with value {string}")
    public void theResponseGroupShouldHaveAFieldWithValue(String arg0, String arg1) {
        response.then().body(arg0, equalTo(arg1));
    }

    @Then("the response for group status code should be {int}")
    public void theResponseForGroupStatusCodeShouldBe(int arg0) {
        response.then().statusCode(arg0);
    }

    @Then("I send a DELETE request to {string} using saved information in parameter")
    public void iSendADELETERequestToUsingSavedInformationInParameter(String endpoint) {
        this.response = RestAssured.delete(endpoint + "/" + userPojo.getGroupId());
    }

    @When("I for group service send a PUT request to {string} with the following body")
    public void iForGroupServiceSendAPUTRequestToWithTheFollowingBody(String endpoint, String body) {

        // `body` içindeki {{id}}'yi `userPojo.getGroupId()` ile değiştiriyoruz
        String updatedBody = body.replace("{{id}}", userPojo.getGroupId());

        // Endpoint içindeki {{id}}'yi güncellemek gerekmediği için bu kısımda değişiklik yapmıyoruz
        String updatedEndpoint = endpoint;

        // PUT isteğini gönderiyoruz
        this.response = RestAssured.given()
                .contentType("application/json")
                .body(updatedBody)
                .put(updatedEndpoint);

        // Yanıtı kaydediyoruz
        Common.setResponse(response);

    }


    @Then("the response should contain error message {string}")
    public void theResponseShouldContainErrorMessage(String expectedMessage) {
        response.then().body("message", equalTo(expectedMessage));
    }


}
