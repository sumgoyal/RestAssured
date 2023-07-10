package ReusableMethods;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {


    public static JsonPath parseRawToJSON(String response){

        JsonPath js=new JsonPath(response);
        return js;

    }
}
