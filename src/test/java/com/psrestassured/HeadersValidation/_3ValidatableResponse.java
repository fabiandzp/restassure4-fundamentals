package com.psrestassured.HeadersValidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.codehaus.groovy.classgen.asm.util.LoggableClassVisitor;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;

public class _3ValidatableResponse {

    public static final String BASE_URL = "https://api.github.com";


    @Test
    public void basicValidatableExample(){
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.JSON)
                .and().assertThat()
                    .header("x-ratelimit-limit", "60");
    }

    @Test
    public void basicHamcrestMatchers(){
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .statusCode(Matchers.lessThan(300))
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .header("etag", Matchers.notNullValue())
                .header("etag", Matchers.not(emptyString()));
    }


    @Test
    public void complexHamcrestMatchers(){
        RestAssured.get(BASE_URL)
                .then()
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.equalTo(60))
                .header("date", date -> LocalDate.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME),
                        OrderingComparison.comparesEqualTo(LocalDate.now()))

                .header("x-ratelimit-limit",
                        response -> greaterThan(response.header("x-ratelimit-remaining")));



    }




}
