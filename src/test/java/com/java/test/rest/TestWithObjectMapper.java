package com.java.test.rest;

import POJOs.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestWithObjectMapper {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testWithObjectMapper(){
        ObjectMapper om = new ObjectMapper();

        String res = get("/api/user/2").asString();

        User user = om.convertValue(res, User.class);

        assert user.id == 2 && user.last_name.equals("Weaver") &&
                user.first_name.equals("Janet") && user.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
    }
}
