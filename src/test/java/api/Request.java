package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {
    private String token;
    private String BASE_URL = System.getProperty("baseURL", "https://api.inv.bg/");
    private String BASE_PATH = System.getProperty("basePath", "v3");
    private String BASE_AGENT = "My User Agent";
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public Request(String token){
        this.token = token;
    }

    private RequestSpecification baseRequest(){
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent",BASE_AGENT)
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON); //Sets Content-Type header
    }

    public Response get(String endpoint){
        return baseRequest().get(endpoint).prettyPeek();
    }

    public Response post(String endpoint, String body){
        return baseRequest().body(body).post(endpoint).prettyPeek();
    }

    public Response delete(String endpoint){
        return baseRequest().delete(endpoint).prettyPeek();
    }

    public Response patch(String endpoint, String body){
        return baseRequest().body(body).patch(endpoint).prettyPeek();
    }
}
