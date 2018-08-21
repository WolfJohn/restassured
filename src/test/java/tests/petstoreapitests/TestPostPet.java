package tests.petstoreapitests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojos.petstore.Category;
import pojos.petstore.Pet;
import pojos.petstore.Tag;
import utils.PropertyReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.*;

@RunWith(SerenityRunner.class)
public class TestPostPet {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI= PropertyReader.readProperty("URL");
    }

    @Test
    public void testPostMethod()  {
        ObjectMapper om = new ObjectMapper();

        Pet pet = new Pet(0L,
                new Category(11, "CategoryName"),
                "Jack",
                new ArrayList<>(Arrays.asList("google.com", "amazon.com")),
                new ArrayList<>(Arrays.asList(new Tag(0, "TagName1"), new Tag(1, "TagName2"))),
                "available");

        try {
            String json = om.writeValueAsString(pet);

            String postPetString = given().
                    body(json).header("Content-Type", "application/json").
                    when().
                    post(PropertyReader.readProperty("postPetURL")).
                    body().asString();

            Pet postPet = om.readValue(postPetString, Pet.class);


        System.out.println(postPet);
        PropertyReader.setOrRewriteProperty("petID", "" + postPet.getId());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
