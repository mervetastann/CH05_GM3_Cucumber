package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authentication.gSessionId;

public class GMBaseUrl {
    public static RequestSpecification spec;

    // Method to set the base URL for other APIs
    public static void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-gm3.quaspareparts.com/a3m/auth/api")
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "GSESSIONID=" + gSessionId())
                .build();
    }

    // Method to set the base URL for User Info
    public static void setUserInfoSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://qa-gm3.quaspareparts.com/auth")
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "GSESSIONID=" + gSessionId())
                .build();
    }


}
