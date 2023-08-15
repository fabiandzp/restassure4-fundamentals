package com.psrestassured.headersvalidation;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class _1PickAndPrint {

    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void peek(){
        RestAssured.get(BASE_URL)
                .peek();
    }

    @Test
    public void pretyPeek(){
        RestAssured.get(BASE_URL)
                .prettyPeek();
    }

    @Test
    public void print(){
        RestAssured.get(BASE_URL)
                .print();
    }

    @Test
    public void pretyPrint(){
        RestAssured.get(BASE_URL)
                .prettyPrint();
    }
}
