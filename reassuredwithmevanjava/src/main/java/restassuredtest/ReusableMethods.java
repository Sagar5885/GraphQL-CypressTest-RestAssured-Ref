package restassuredtest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReusableMethods {

    public static JsonPath rawToJson(Response r)
    {
        String respon=r.asString();
        //System.out.println(respon);
        JsonPath x=new JsonPath(respon);
        return x;
    }

    public static String generateStringFromResources(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static Stream<Path> getAllFilesFromDir(String Dir) throws IOException{
        return Files.list(Paths.get(Dir)).filter(Files::isRegularFile);
    }

}
