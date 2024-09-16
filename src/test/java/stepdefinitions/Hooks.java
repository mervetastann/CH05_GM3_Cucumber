package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class Hooks {
    @Before("@UserInfo")
    public void beforeScenario() {
        // Burada UserInfo API çağrısı yapıp verileri saklayın
        UserInfoSteps userInfoSteps = new UserInfoSteps();
        userInfoSteps.iSetTheBaseSpecificationForUserInfoAPI();
        userInfoSteps.iSendAGETRequestTo("/userinfo");
        userInfoSteps.iSaveFieldFromTheResponse("sub_app");
        userInfoSteps.iSaveFieldFromTheResponse("sub_default_org_id");
        userInfoSteps.iSaveFieldFromTheResponse("sub_default_subscription_id");
    }
}
