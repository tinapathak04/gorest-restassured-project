package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class UserAssertionTest {
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

//      1. Verify the if the total record is 20
    @Test
    public void test01(){
        response.body("total",equalTo(20));
    }

//      2. Verify the if the name of id = 4040686 is equal to ”Chaturbhuj Reddy”
    @Test
    public void test02(){
        response.body("[0].id",equalTo("[0].name"));
    }

//      3. Check the single ‘Name’ in the Array list (Chaturbhuj Reddy)
    @Test
    public void test03(){
        response.body("name",hasItem("Chaturbhuj Reddy"));
    }

//      4. Check the multiple ‘Names’ in the ArrayList (Saraswati Dhawan,Bhima Chaturvedi, Tushar Ahluwalia)
    @Test
    public void test04(){
        response.body("name",hasItems("Saraswati Dhawan","Bhima Chaturvedi","Tushar Ahluwalia"));
    }

//      5. Verify the email of userid =4040686  is equal “chaturvedi_bhima@barrows.example”
    @Test
    public void test05(){
        response.body("[5].id",hasKey("chaturvedi_bhima@barrows.example"));
    }

//      6. Verify the status is “Active” of user name is “”
    @Test
    public void test06(){
        response.body("[5].status",hasKey("[5].name"));

    }

//      7. Verify the Gender = male of user name is “Bhima Chaturvedi”
    @Test
    public void test07(){
        response.body("[5].gender",hasKey("[5].name"));

    }


}
