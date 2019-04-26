package restassuredtest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static restassuredtest.ReusableMethods.*;
import static restassuredtest.ReusableMethods.generateStringFromResources;

public class basicReadtest {

    @DataProvider(name="booksData")
    public Iterator<Object[]> setupData() throws IOException {
        Stream<Path> pathlist = getAllFilesFromDir("./src/main/resources/request");
        Collection<Object[]> dp = new ArrayList<Object[]>();
        pathlist.forEach(s -> dp.add(new Object[]{s.toString()}));
        return dp.iterator();
    }

    @Test(dataProvider = "booksData")
    public void testReadJson(String path) throws IOException {
        //System.out.println(path);
        RestAssured.baseURI = "http://216.10.245.166";
        Response res = given().
                header("Content-Type", "application/json").
                body(generateStringFromResources(path)).
                when().
                post("/Library/Addbook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath json = rawToJson(res);
        String id = json.get("ID");
        System.out.println(id);

        String s = generateStringFromResources(path.replaceAll("request", "response"));
        JsonPath jsonPath = JsonPath.from(s);
        System.out.println(jsonPath.getString("Msg"));

        Assert.assertEquals(json.get("Msg"), jsonPath.getString("Msg"));

        //Delete Data
        Response resDelete = given().
                body("{\"ID\" : \"" + id + "\"}").
                when().
                post("/Library/DeleteBook.php").
                then().assertThat().statusCode(200).
                extract().response();

        JsonPath jsonDelete = rawToJson(resDelete);
        System.out.println(jsonDelete.getString("msg"));

        Assert.assertEquals(jsonDelete.get("msg"), "book is successfully deleted");
    }
}