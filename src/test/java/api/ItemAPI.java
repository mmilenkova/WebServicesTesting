package api;

import api.dto.Credentials;
import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ItemAPI extends Request {
    private static final String ENDPOINT = "/items";

    public ItemAPI (String token){
        super(token);
    }

    /**
     * Create items
     * @param item item info
     * @return Response
     */
    public Response createItem(Item item){
        String body = GSON.toJson(item);
        return post(ENDPOINT, body);
    }

    /**
     * Deletes item by id
     * @param id id
     * @return Response
     */
    public Response deleteItem(int id){
        return delete(ENDPOINT +"/"+ id);
    }

    public Response getItem(int id){
        return get(ENDPOINT + "/" + id);
    }
    /**
     * Update existing item
     * @param item dta of the item
     * @return Response
     */
    public Response updateItem (Item item){
        return patch(ENDPOINT, GSON.toJson(item));
    }

    public Response getItems(){
        return get(ENDPOINT);
    }

    public static void main(String[] args) {
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item("CoffeeTest", 20.50f, "kg.", 1);
//        item.name = "Coffee";
//        item.price = 20.50f;
//        item.price_for_quantity = 1;
//        item.quantity_unit = "kg.";
        Response createResp = itemAPI.createItem(item);
    }
}
