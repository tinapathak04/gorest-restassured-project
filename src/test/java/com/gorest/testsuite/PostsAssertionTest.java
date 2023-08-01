package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {

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

//    1. Verify the if the total record is 25
    @Test
    public void test01(){
        response.body("total",equalTo(25));
    }


//    2. Verify the if the title of id = 56978 is equal to ”Rerum omnis sursum damno terror.”
    @Test
    public void test02(){
        response.body("title",equalTo("[1].id)"));

    }

//    3. Check the single user_id in the Array list (4040706)
     @Test
     public void test03(){
         response.body("user_id",hasItem(4040706));

     }

//    4. Check the multiple ids in the ArrayList (56978, 56965,56954)
     @Test
     public void test04(){
         response.body("id",hasItems(56978,56965,56954));
     }

//    5. Verify the body of userid = 4040694 is equal “Carus eaque voluptatem. Calcar
//    spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//    Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//    Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//    cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//    adflicto. Assentator umquam pel."”
     @Test
     public void test05(){
         response.body("[5].user_id",equalTo("[5].body"));
    }

}
