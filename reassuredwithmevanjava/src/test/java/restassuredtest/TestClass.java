package restassuredtest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static restassuredtest.ReusableMethods.*;

public class TestClass {

    public static void main(String args[]) throws IOException {
//        Stream<Path> pathlist = getAllFilesFromDir("./src/main/resources/request");
//        pathlist.forEach(s -> {
//            try {
//                testReadJson(s.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

//    public static void testReadJson(String filePath) throws IOException{
//        RestAssured.baseURI = "http://216.10.245.166";
//        Response res = given().
//                header("Content-Type", "application/json").
//                body(generateStringFromResources(filePath)).
//                when().
//                post("/Library/Addbook.php").
//                then().assertThat().statusCode(200).
//                extract().response();
//
//        JsonPath json = rawToJson(res);
//        String id = json.get("ID");
//        System.out.println(id);
//
//        //Delete Data
//        Response resDelete = given().
//                body("{\"ID\" : \"" + id + "\"}").
//                when().
//                post("/Library/DeleteBook.php").
//                then().assertThat().statusCode(200).
//                extract().response();
//    }

}
