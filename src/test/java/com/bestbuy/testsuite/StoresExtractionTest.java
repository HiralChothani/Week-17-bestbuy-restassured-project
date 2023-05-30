package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
//         response.log().all(); this line is to print data into console
    }

    //    1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of the 5th store : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> listOfStoreNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of all stores : " + listOfStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> listOfStoreIds = response.extract().path("data.services[0].storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Store Ids are : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        Integer sizeOfDataList = response.extract().path("data.length");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of datalist : " + sizeOfDataList);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<Object> valueOfStore = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the value of store named 'St Cloud' : " + valueOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> storeAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("store address of store named 'St Cloud' : " + storeAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<HashMap<String, ?>> servicesListMap = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("services of 8th Store : " + servicesListMap);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
//        List<HashMap<String, ?>> storeServicesListMap = response.extract().path("data.services.findAll{it.name == 'Windows Store'}.storeservices");
//        System.out.println("------------------StartingTest---------------------------");
//        System.out.println("Storeservices of Windows Store : " + storeServicesListMap);
//        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        test005();
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> listOfId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Ids of all stores : " + listOfId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name where state = ND : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<HashMap<String, ?>> totalServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services.length");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name where state = ND : " + totalServices.size());
        System.out.println("------------------End of Test---------------------------");
    }
//15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015(){
        List<String> createdAtForServices = response.extract().path("data.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Created at for services whose name = Windows Store : " + createdAtForServices);
        System.out.println("------------------End of Test---------------------------");
   }
//            16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016(){
        List<String> servicesName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("name of all services Where store name = Fargo : " + servicesName);
        System.out.println("------------------End of Test---------------------------");
    }
//            17. Find the zip of all the store
    @Test
    public void teat017(){
        List<Integer> zipOfStores = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Zip of all stores : " + zipOfStores);
        System.out.println("------------------End of Test---------------------------");
    }
//18. Find the zip of store name = Roseville
    @Test
        public void test018() {
        List<Integer> zipForRoseville = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("zip of store name = Roseville : " + zipForRoseville);
        System.out.println("------------------End of Test---------------------------");
    }
//19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019(){
//        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
//        System.out.println("------------------StartingTest---------------------------");
//        System.out.println("storeservices details of the service name = Magnolia Home Theater : " + storeServices);
//        System.out.println("------------------End of Test---------------------------");
    }
//20. Find the lat of all the stores
    @Test
    public void teat020(){
        List<Double> latOfAllStores = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("lat of all the stores : " + latOfAllStores);
        System.out.println("------------------End of Test---------------------------");
    }
}
