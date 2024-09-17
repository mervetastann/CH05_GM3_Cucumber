package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UserGroupTypeService {

    Response response= Common.getResponse();


    @And("the response must contain {string}, {string}, or {string}")
    public void theResponseMustContainOr(String field1, String field2, String field3) {
        List<String> validFields = Arrays.asList(field1, field2, field3);

        // JSON yanıtındaki alanları al
        String responseBody = response.getBody().asString();  // Yanıtı string olarak al
        List<String> responseFields = new ArrayList<>();

        // JSON yanıtındaki alanları kontrol et
        if (responseBody.contains("id")) {
            responseFields.add("id");
        }
        if (responseBody.contains("name")) {
            responseFields.add("name");
        }
        if (responseBody.contains("description")) {
            responseFields.add("description");
        }

        // Yanıttaki her bir alanın belirtilen alanlardan biri olup olmadığını kontrol et
        for (String field : responseFields) {
            assertTrue("Invalid field in response: " + field, validFields.contains(field));
        }

    }
}






