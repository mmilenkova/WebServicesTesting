package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ItemAPI {
    private String token;
    private static final String ENDPOINT = "/items";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String BASE_URL = "https://api.inv.bg";

    public ItemAPI (String token){
        this.token = token;
    }

    /**
     * Create items
     * @param item item info
     * @return Response
     */
    public Response createItem(Item item){
        String body = gson.toJson(item);
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .body(body) //Sets the request body
                .when().post(ENDPOINT)// Sets verb to POST and provide resource url
                .prettyPeek(); //Prints response in a nice way
    }

    /**
     * Deletes item by id
     * @param id id
     * @return Response
     */
    public Response deleteItem(int id){
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .when().delete(ENDPOINT + "/" + id)// Sets verb to POST and provide resource url
                .prettyPeek(); //Prints response in a nice way
    }

    public Response getItem(int id){
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .when().get(ENDPOINT + "/" + id)// Sets verb to POST and provide resource url
                .prettyPeek(); //Prints response in a nice way
    }

    public Response updateItem (Item item){
        String body = gson.toJson(item);
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .body(body) //Sets the request body
                .when().post(ENDPOINT)// Sets verb to POST and provide resource url
                .prettyPeek(); //Prints response in a nice way
    }

    public Response getItems(){
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath("v3") //Sets the base path for the request
                .header("User-Agent", "Good try")
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON) //Sets Content-Type header
                .when().get(ENDPOINT)// Sets verb to POST and provide resource url
                .prettyPeek(); //Prints response in a nice way
    }

    public static void main(String[] args) {
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item();
        item.name = "Coffee";
        item.price = 20.50f;
        item.price_for_quantity = 1;
        item.quantity_unit = "kg.";
        Response createResp = itemAPI.createItem(item);
    }
}
