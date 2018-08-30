package tests.petstoreapitests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
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

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class TestGetPetByID {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI= PropertyReader.readProperty("URL");
    }

    @Test
    public void testPostMethod() {
        ObjectMapper om = new ObjectMapper();

        Pet pet = new Pet(Long.parseLong(PropertyReader.readProperty("petID")),
                new Category(11, "CategoryName"),
                "Jack",
                new ArrayList<>(Arrays.asList("google.com", "amazon.com")),
                new ArrayList<>(Arrays.asList(new Tag(0, "TagName1"), new Tag(1, "TagName2"))),
                "available");

        Pet getPet =
                given().
                    header("Accept", "application/json").
                when().
                    get(PropertyReader.readProperty("postPetURL") + "/" + PropertyReader.readProperty("petID")).
                    body().as(Pet.class);

        Assert.assertEquals(pet, getPet);
    }
}
