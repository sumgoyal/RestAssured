import ReusableMethods.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Basics {
    public static void main(String[] args){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.AddPlace()).when().post("/maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200).body("scope",equalTo("APP")).extract().response().asString();

        JsonPath js=new JsonPath(response);
        String placeID=js.getString("place_id");
        System.out.println(placeID);

        //update place
        String newAddress="70 Summaer walk, USA";

        given().log().all().queryParam("key","qaclick123").body("{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n").
                when().put("/maps/api/place/update/json").
                then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        //get place api
        String getPlaceResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).
                when().get("/maps/api/place/get/json").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1=ReusableMethods.parseRawToJSON(getPlaceResponse);
        String address=js1.getString("address");
        assertEquals("70 Summer walk, USA",newAddress);





    }







}
