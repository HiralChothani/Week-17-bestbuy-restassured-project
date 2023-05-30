package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBaseStores;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBaseStores {
    static int storeId;
    private String name = "Zara" + TestUtils.getRandomValue();
    private String type = "SmallBox" + TestUtils.getRandomValue();
    private String address = TestUtils.getRandomValue();
    private String address2 = "";
    private String city = TestUtils.getRandomValue();
    private String state = TestUtils.getRandomValue();
    private String zip = TestUtils.getRandomValue();
    private Double lat = TestUtils.randomDouble();
    private Double lng = TestUtils.randomDouble();
    private String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    @Test
    public void test001() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post();
        storeId = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id number is :" + storeId);
        response.prettyPrint();

    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test003() {
        Response response = given()
                .when()
                .get("/" + storeId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test004() {
        StorePojo storePojo = new StorePojo();
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/" + storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/" + storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

}
