package com.petstore.swagger.api;


import io.restassured.response.Response;

import static com.petstore.swagger.api.SpecBuilder.getRequestSpec;
import static com.petstore.swagger.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.*;

public class RestResource {

    public static Response post(String path ,Object requestGrocery){

        return given(getRequestSpec()).
                body(requestGrocery).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path){

        return given(getRequestSpec()).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response getPending(String path) {

        return given(getRequestSpec()).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response getAvailable(String path) {

        return given(getRequestSpec()).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response getSold(String path) {

        return given(getRequestSpec()).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }



    public static Response update(String path , Object requestGrocery){
        return given(getRequestSpec()).
                body(requestGrocery).
                when().
                put(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String path){
        return given(getRequestSpec()).
                when().
                delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
