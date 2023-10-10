package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//Data Transfer Object (DTO)
//Plain Old Java Object (POJO)
public class Credentials {
    public String email;
    public String password;
    public String domain;

    public Credentials (String email, String password, String domain){
        this.email = email;
        this.password = password;
        this.domain = domain;
    }


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Credentials myCredentials = new Credentials("mm.milenkova@gmail.com", "q2w3e4r5", "marieta-ood");
        //Convert java object to json
        String credentialsJson = gson.toJson(myCredentials);
        System.out.println("Serialized object:");
        System.out.println(credentialsJson);
        //Convert json to java object
        Credentials deserializedCredentials = gson.fromJson(credentialsJson, Credentials.class);
        System.out.println("Deserialized fields:");
        System.out.println(deserializedCredentials.email);
        System.out.println(deserializedCredentials.password);
        System.out.println(deserializedCredentials.domain);
    }

}