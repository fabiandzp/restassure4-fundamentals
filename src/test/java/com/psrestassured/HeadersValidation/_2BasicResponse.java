package com.psrestassured.HeadersValidation;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class _2BasicResponse {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void convenienceMethod() {
        Response response = RestAssured.get(BASE_URL).prettyPeek();
        assertEquals(response.getStatusCode(), 200);
//        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void genericHeader() {
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("X-RateLimit-Limit"), "60");
        assertEquals(Integer.parseInt(response.getHeader("X-RateLimit-Limit")), 60);
    }

    @Test
    public void getHeaders() {
        Response response = RestAssured.get(BASE_URL).prettyPeek();
        Headers headers = response.getHeaders();

        String val = headers.getValue("date");
        int size = headers.size();
        System.out.println("size = " + size);
        List<Header> list = headers.asList();
        System.out.println("list = " + list);
        boolean isPresent = headers.hasHeaderWithName("date");
        assertTrue(isPresent);
    }

    @Test
    public void timeExample(){
        Response response = RestAssured.get(BASE_URL);
        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.MINUTES));
    }
}

