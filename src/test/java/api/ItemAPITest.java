package api;

import api.dto.Credentials;
import api.dto.Item;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ItemAPITest {

    @Test
    @Tag("api")
    @DisplayName("Can create new item")
    public void canCreateNewItem(){
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item("Coffee", 20.50f, "kg.", 1);;
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    public void canGetAllItems(){
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Response createResp = itemAPI.getItems();
        Assertions.assertEquals(200, createResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get single item")
    public void canGetSingleItem(){
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item("Coffee", 20.50f, "kg.", 1);;
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200, getResp.statusCode());
    }

    @Test
    @Tag("api")
    @Tag("update")
    @DisplayName("Can update single item")
    public void canUpdateSingleItem(){
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item("Coffee", 20.50f, "kg.", 1);;
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200, getResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can delete single item")
    public void canDeleteSingleItem(){
        Credentials credentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = new Item("Coffee", 20.50f, "kg.", 1);;
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response deleteResp = itemAPI.deleteItem(id);
        Assertions.assertEquals(204, deleteResp.statusCode());
    }
}
