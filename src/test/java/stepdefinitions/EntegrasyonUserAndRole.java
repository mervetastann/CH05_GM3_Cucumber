package stepdefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.UserPojo;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class EntegrasyonUserAndRole {
    private static UserPojo userPojo = UserInfoSteps.getUserPojo(); // Ensure this is shared across steps

    private static Integer randomRoleId;

    Response response= Common.getResponse();


    @And("user saved the random id")
    public void userSavedTheRandomId() {

        if (response == null) {
            throw new RuntimeException("Response is null. Ensure a valid GET request was made.");
        }

        // JSON yanıtından roller listesini al
        List<Map<String, Object>> roles = response.jsonPath().getList("");

        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("Roles list is empty or null.");
        }

        // Rastgele bir rol seç
        Random random = new Random();
        int randomIndex = random.nextInt(roles.size());
        Map<String, Object> randomRole = roles.get(randomIndex);

        // Seçilen rolün ID'sini al
        randomRoleId = Integer.valueOf(randomRole.get("id").toString());
        System.out.println("randomRoleId = " + randomRoleId);
    }

    


    @And("update user role")
    public void updateUserRole() {
        String endpoint = "/v1/user/"+ UserActionsSteps.savedValue+"/application/"+userPojo.getApp_id()+"/membership/3f5f58ee-72bf-41bb-a5ba-31beceff791b/role/"+randomRoleId+"/add-role";
        response = given().when().put(endpoint);
        
    }
    @And("set default role")
    public void setDefaultRole() {
        String endpoint = "/v1/user/"+ UserActionsSteps.savedValue+"/application/"+userPojo.getApp_id()+"/membership/3f5f58ee-72bf-41bb-a5ba-31beceff791b/role/"+randomRoleId+"/set-default-role";
        response = given().when().put(endpoint);
    }
    @And("I send a GET user request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = RestAssured.given().when().get(endpoint+"/"+UserActionsSteps.savedValue);
        Common.setResponse(response);

        String defaultRoleIdFromResponse = response.jsonPath().getString("default_role_id");

        // Assert işlemi ile default_role_id'nin set ettiğimiz role ID ile eşleştiğini kontrol et
        Assert.assertEquals("Default role ID doğru şekilde set edilmedi!", String.valueOf(randomRoleId), defaultRoleIdFromResponse);

        System.out.println("Default role successfully set to: " + defaultRoleIdFromResponse);
    }

    }


