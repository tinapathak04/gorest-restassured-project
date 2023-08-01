package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

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
                .get("/posts")
                .then().statusCode(200);
    }

//    1. Extract the title
    @Test
    public void test01(){
    List<Integer> title = response.extract().path("title");
    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The value of title are : " + title );
    System.out.println("------------------End of Test---------------------------");
    }
//    2. Extract the total number of record
    @Test
    public void test02(){

    }
//    3. Extract the body of 15th record
    @Test
    public void test03(){
        String recordOf15 = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of 15th record is : " + recordOf15 );
        System.out.println("------------------End of Test---------------------------");

    }

//    4. Extract the user_id of all the records
    @Test
    public void test04(){
        List<String> userId = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of user id records are : " + userId );
        System.out.println("------------------End of Test---------------------------");

    }

//    5. Extract the title of all the records
    @Test
    public void test05(){
        List<Integer> allRecords = response.extract().path("");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all records of data are : " + allRecords );
        System.out.println("------------------End of Test---------------------------");
    }



//    6. Extract the title of all records whose user_id = 56978
    @Test
    public void test06(){
        String allRecordsOfId = response.extract().path("[1].title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of record is : " + allRecordsOfId );
        System.out.println("------------------End of Test---------------------------");

    }

//    7. Extract the body of all records whose id = 56969
    @Test
    public void test07(){
        String allRecordsOfBody = response.extract().path("[3].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of record is : " + allRecordsOfBody );
        System.out.println("------------------End of Test---------------------------");
    }


}
