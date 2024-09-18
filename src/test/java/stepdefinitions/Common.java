package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.UserPojo;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class Common {

    private static Response response;
    private static UserPojo userPojo = new UserPojo(); // Ensure `userPojo` is shared across steps

    // Static method to set response
    public static void setResponse(Response response) {
        Common.response = response;
    }

    public static Response getResponse() {
        return response;
    }

    public static UserPojo getUserPojo() {
        return userPojo;
    }

    public static void setUserPojo(UserPojo userPojo) {
        Common.userPojo = userPojo;
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals("Status code does not match!", expectedStatusCode, actualStatusCode);
    }


    @Then("the Content-Type should be {string}")
    public void theContentTypeShouldBe(String expectedContentType) {
        assertThat("Expected Content-Type: " + expectedContentType + " but found: " + response.getContentType(),
                response.getContentType(), equalTo(expectedContentType));
    }
    @And("the response have a field {string} with value {int}")
    public void theResponseHaveAFieldWithValue(String arg0, int arg1) {
        response.then().body(arg0, equalTo(arg1));
    }

    @Then("the response data length should be {int}")
    public void theResponseDataLengthShouldBe(int expectedLength) {
        // Verideki id alanlarını liste olarak al
        List<Object> responseData = response.jsonPath().getList("");

        // Assert işlemi ile uzunluk doğrulaması yap
        Assert.assertEquals(expectedLength,responseData.size());

        // Doğru JSON yolu ile body kontrolü yap
        response.then().body("", hasSize(expectedLength));  // Burada JSON yolunu "id" olarak düzeltiyoruz
//        System.out.println("response.then().body(\"\", hasSize(expectedLength)) = " + response.then().body("", hasSize(expectedLength)));
    }

    @And("print response body")
    public void printResponseBody() {
        response.prettyPrint();
    }

    @And("the response must have a field {string} with value {string}")
    public void theResponseMustHaveAFieldWithValue(String fname, String countryname) {
        response.then().body(fname, equalTo(countryname));
    }


}