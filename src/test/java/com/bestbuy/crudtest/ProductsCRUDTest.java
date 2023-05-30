package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseProduct;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBaseProduct {
    static int productId;
    private String name = "Lara" + TestUtils.getRandomValue();
    private String type = "Lakme" + TestUtils.getRandomValue();
    private Double price = 6.98;
    private String upc = TestUtils.getRandomValue();
    private int shipping = TestUtils.getRandomNumber();
    private String description = TestUtils.getRandomValue();
    private String manufacturer = TestUtils.getRandomValue();
    private String  model  = "MN" + TestUtils.getRandomValue();
    private String  url = "http://" + TestUtils.randomString();
    private String image = "http://" +TestUtils.randomString();

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post();
        productId = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id number is :" + productId);
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
                .get("/" + productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test004() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

}
