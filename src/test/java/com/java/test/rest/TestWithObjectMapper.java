package com.java.test.rest;

import POJOs.Data;
import POJOs.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class TestWithObjectMapper {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://reqres.in";
    }

    @Test
    public void testWithObjectMapperAndSimpleAssertion() throws IOException {
        ObjectMapper om = new ObjectMapper();

        String res = get("/api/users/2").asString();

        Data data = om.readValue(res, Data.class);

        assert data.data.id.equals("2") && data.data.last_name.equals("Weaver") &&
                data.data.first_name.equals("Janet") && data.data.avatar.equals("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");

        System.out.println(data);
    }



}
