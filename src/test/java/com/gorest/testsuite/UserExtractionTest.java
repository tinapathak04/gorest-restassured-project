package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        HashMap<String, Object> qParam = new HashMap<>();
        qParam.put("page","1");
        qParam.put("per_page","20");
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/";
        response = given()
                .queryParams(qParam)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

//    1. Extract the All Ids
    @Test
    public void test01(){
        List<Integer> id = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of id are : " + id );
        System.out.println("------------------End of Test---------------------------");
    }

//    2. Extract the all Names
    @Test
    public void test02(){
    List<String> names = response.extract().path("name");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of Names are : " + names );
    System.out.println("------------------End of Test---------------------------");
}

//    3. Extract the name of 5th object
    @Test
    public void test03(){
        String singleName = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Name is : " + singleName );
        System.out.println("------------------End of Test---------------------------");
    }

//    4. Extract the names of all object whose status = inactive
    @Test
    public void test04(){
        List<String> status = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of status are : " + status );
        System.out.println("------------------End of Test---------------------------");
    }

//    5. Extract the gender of all the object whose status = active
    @Test
     public void test05() {

        List<String> status = response.extract().path("findAll{it.status == 'active'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

//    6. Print the names of the object whose gender = female
    @Test
    public void test06(){
        List<String> Female = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of female are : " + Female);
        System.out.println("------------------End of Test---------------------------");
    }

//    7. Get all the emails of the object where status = inactive
    @Test
    public void test07() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of email are : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

//    8. Get the ids of the object where gender = male
    @Test
    public void test08(){
        List<String> Male = response.extract().path("findAll{it.gender == 'male'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of female are : " + Male);
        System.out.println("------------------End of Test---------------------------");
    }

//    9. Get all the status
    @Test
    public void test09(){
        List<String> allStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Names are : " + allStatus );
        System.out.println("------------------End of Test---------------------------");
    }

//    10. Get email of the object where name = Bhima Chaturvedi
    @Test
    public void test10(){
        String email = response.extract().path("[5].email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email is : " + email );
        System.out.println("------------------End of Test---------------------------");
    }



//    11. Get gender of id = 4040686
    @Test
    public void test11(){
        String gender = response.extract().path("[5].gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The email is : " + gender );
        System.out.println("------------------End of Test---------------------------");
    }

}
