import files.payload;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(payload.complexJson());
        //no. of courses by API

        int numOfCourses = js.getInt("courses.size()");
        System.out.println("courses:" + numOfCourses);

        //purchase amount

        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("purchaseAmount:" + purchaseAmount);

        //print title of first course

        String courseOneTitle = js.get("courses[0].title");
        System.out.println("CourseOneTitle:" + courseOneTitle);

        System.out.println("******************************************************");

        //print all course title and their price

        for (int i = 0; i < numOfCourses; i++) {

            String courseTitle = js.get("courses[" + i + "].title");
            int coursePrice = js.get("courses[" + i + "].price");

            System.out.println("courseTitle is:" + courseTitle);
            System.out.println("coursePrice is:" + coursePrice);


        }
        System.out.println("******************************************************");
        //print no of copies sold by RPA course

        for (int i = 0; i < numOfCourses; i++) {

            if(js.get("courses["+i+"].title").equals("RPA")){
                int copies=js.getInt("courses["+i+"].copies");
                System.out.println("Number of Copies sold by RPa is: "+copies);
            }
        }

        System.out.println("******************************************************");
        //sum of all courses price is equal to purchase amount
        List<Integer> price=new ArrayList<>();
        int TotalpurchasedAmount=0;

        for (int i = 0; i < numOfCourses; i++) {
            int copies=js.getInt("courses["+i+"].copies");
            int amount=js.getInt("courses["+i+"].price");
            int purchaseAmountforOne=copies*amount;
            TotalpurchasedAmount=TotalpurchasedAmount+purchaseAmountforOne;
        }
        assertEquals(purchaseAmount,TotalpurchasedAmount);


        }
}



