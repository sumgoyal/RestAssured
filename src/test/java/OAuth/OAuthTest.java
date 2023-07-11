package OAuth;

import POJO.Courses;
import POJO.GetCourse;
import POJO.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static io.restassured.RestAssured.*;
public class OAuthTest {

    public static void main(String[] args) throws InterruptedException {

//        RestAssured.baseURI="";

        String url="https://rahulshettyacademy.com/getCourse.php?state=sumitg&code=4%2F0AZEOvhUfqYenU9Hgv-R5jWhMaQBKedVBWlBGet_R3nO2jaYVT5CUsYUMZmy8C2b6Yg-yEg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=2&prompt=none";
        String partialCode=url.split("code=")[1];
        String code=partialCode.split("&scope")[0];
//        System.out.println(code);


        String accessTokenResponse=given().urlEncodingEnabled(false).queryParam("code",code).queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js=new JsonPath(accessTokenResponse);
        String accessToken=js.get("access_token");



        GetCourse gc=given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON).when().
                get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);


        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());










    }



}
