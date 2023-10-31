package api;

import api.dto.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LoginAPI {
    private static final String BASE_URL = "https://api.inv.bg";
    private static final String ENDPOINT = "/login/token";
    private static final String EMAIL = "mm.milenkova@gmail.com";
    private static final String PASSWORD = "q2w3e4r5";
    private static final String DOMAIN = "marieta-ood";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String obtainToken(Credentials credentials) {
        String body = gson.toJson(credentials);
        return RestAssured.given()
                .log().all()
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .body(body) //Sets the request body
                .when().post(ENDPOINT)// Sets verb to POST and provide resource url
                .prettyPeek() //Prints response in a nice way
                .then().extract().body().jsonPath().getString("token"); //Extract the token from the response body
    }

    public static void main(String[] args) {
        String token = LoginAPI.obtainToken(new Credentials(EMAIL, PASSWORD, DOMAIN));
        System.out.println();
        System.out.println("The token is:" + token);
    }
}
