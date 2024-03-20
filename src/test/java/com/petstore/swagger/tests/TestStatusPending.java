package com.petstore.swagger.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.petstore.swagger.api.ApiStatus;
import com.petstore.swagger.datas.IStatus;
import com.petstore.swagger.pojos.Pet;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestStatusPending {

    Response response;
    JSONObject jsonObject;
    int ID;
    String NAME;
    ObjectMapper mapper;


    public TestStatusPending(){
        this.mapper = new ObjectMapper();
        this.jsonObject = new JSONObject();
        Faker FAKER = new Faker();
        this.ID = (int) FAKER.number().randomNumber();
        this.NAME = FAKER.animal().name();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("POST request is starting")
    @Description("Validating to POST request for the PENDING status")
    @Test(priority = 1)
    public void validatePostForPendingStatus() {

        Pet requestPet = petRequest();
        this.response = ApiStatus.post(requestPet);
        Pet responsePet = this.response.as(Pet.class);
        assertPost(responsePet,requestPet);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("GET request is starting")
    @Description("Validating to GET request for the PENDING status")
    @Test(priority = 2)
    public void validateGetForPendingStatus(){

        Response response = ApiStatus.get(this.ID);
        assertThat(response.getStatusCode(),equalTo(200));
        Pet responsePet = response.as(Pet.class);
        assertGetPendingStatus(responsePet);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("GET request is starting for ALL PENDING statuses")
    @Description("Validating to GET request for the ALL PENDING status")
    @Test(priority = 3)
    public void validateGetAllPendingStatuses() {

        this.response = ApiStatus.getPending();

        String status = this.response.path("status[3]").toString();
        String thirdId = this.response.path("id[3]").toString();
        assertAllPendingStatus(status);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("DELETE request is starting ")
    @Description("Validating to DELETE request for the PENDING status")
    @Test(priority = 4)
    public void validateDeleteForPendingStatus(){

        this.response = ApiStatus.delete(this.ID);
        assertDeleteStatus();
    }




    public Pet petRequest (){
        Pet requestPet = new Pet();
        requestPet.setId(this.ID);
        requestPet.setName(this.NAME);
        requestPet.setStatus(IStatus.statusPending);
        return requestPet;
    }

    public void assertPost(Pet responsePet,Pet requestPet) {
        assertThat(responsePet.getId(), equalTo(requestPet.getId()));
        assertThat(responsePet.getName(), equalTo(requestPet.getName()));
        assertThat(responsePet.getStatus(), equalTo(requestPet.getStatus()));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));
    }

    public void assertGetPendingStatus (Pet responsePet) {
        assertThat(responsePet.getId() , equalTo(this.ID));
        assertThat(responsePet.getName() , equalTo(this.NAME));
        assertThat(responsePet.getStatus(), equalTo(IStatus.statusPending));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));
    }

    public void assertAllPendingStatus(String status){
        assertThat(status,equalTo(IStatus.statusPending));
        //assertThat(thirdId,equalTo("764"));
        assertThat(this.response.getStatusCode(),equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));
    }

    public void assertDeleteStatus() {
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine() , equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));
        assertThat(this.response.path("code") , equalTo(200));
        assertThat(this.response.path("type") , equalTo("unknown"));
    }
}
