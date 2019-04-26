package restassuredtest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static restassuredtest.ReusableMethods.rawToJson;
import static restassuredtest.payload.getPostData;
import static restassuredtest.payload.getPostDataUpdate;
import static restassuredtest.resources.*;

public class basictest {

    private static Logger log = LogManager.getLogger(basictest.class.getName());
    Properties props = new Properties();

    @BeforeTest
    public void getTestData() throws IOException {
        FileInputStream fs = new FileInputStream("./src/main/resources/env.properties");
        props.load(fs);
        props.get("HOST");
        RestAssured.baseURI = props.getProperty("HOST");

    }

    String id;

    @Test
    public void testCreate(){
        log.info("log info!"+props.getProperty("HOST"));

        log.info("POST Test Starts!");

        Response res = given().
                body(getPostData()).
                when().
                post(postData()).
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath json = rawToJson(res);
        id = json.get("id");

        System.out.println("Test Create Post id: "+id);
        log.info("POST Test Ends!");
    }

    @Test(dependsOnMethods = "testCreate")
    public void testGet(){

        log.info("GET Test Starts!");

        Response res = given().
                when().
                get(getData()+id).
                then().assertThat().statusCode(200).
                extract().response();

        System.out.println("Test Get!"+res.asString());

        JsonPath json = rawToJson(res);

        Assert.assertEquals("testSaga1", json.get("employee_name"));
        Assert.assertEquals("123", json.get("employee_salary"));
        Assert.assertEquals("23", json.get("employee_age"));

        log.info("GET Test Ends!");
    }

    @Test(dependsOnMethods = "testGet")
    public void testUpdate(){
        log.info("PUT Test Starts!");
        Response res = given().
                body(getPostDataUpdate()).
                when().
                put(putData()+id).
                then().assertThat().statusCode(200).
                extract().response();
        System.out.println("Test Update Put id: "+id);
        log.info("PUT Test Ends!");
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testGetAgain(){
        log.info("GET-Again Test Starts!");

        Response res = given().
                when().
                get(getData()+id).
                then().assertThat().statusCode(200).
                extract().response();

        System.out.println("Test Get!"+res.asString());

        JsonPath json = rawToJson(res);

        Assert.assertEquals("testSaga1", json.get("employee_name"));
        Assert.assertEquals("124", json.get("employee_salary"));
        Assert.assertEquals("24", json.get("employee_age"));

        log.info("GET-Again Test Ends!");
    }

    @Test(dependsOnMethods = "testGetAgain")
    public void testDelete(){
        log.info("DELETE Test Starts!");

        given().when().delete(deleteData()+id).then().
                assertThat().statusCode(200);
        System.out.println("Test Delete id: "+id);

        log.info("DELETE Test Ends!");
    }
}
