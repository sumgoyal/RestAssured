import ReusableMethods.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class DynamicJson {

    String id=null;


    @Test(dataProvider = "BooksData")
    public void addBook(String name,String aisle){
        RestAssured.baseURI="http://216.10.245.166";

        String response=given().log().all().header("Content-Type","application/json").body(payload.addBook(name,aisle)).
                when().post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=ReusableMethods.parseRawToJSON(response);

        id=js.get("ID");
        System.out.println("ID is: "+id);

    }

    @DataProvider(name="BooksData")
    public Object[][] getData(){

        return new Object[][]{{"sumit","121"},{"anjali","092"},{"ranu","234"}};

    }



//    @Test(priority=2)
//    public void deleteBook(){
//        String outputDelete=given().log().all().queryParam("ID",id)
//                .when().delete("/Library/DeleteBook.php")
//                .then().log().all().assertThat().statusCode(200).extract().response().asString();
//
//        JsonPath js1=ReusableMethods.parseRawToJSON(outputDelete);
//        System.out.println(js1.get("msg"));
//
//    }

}
