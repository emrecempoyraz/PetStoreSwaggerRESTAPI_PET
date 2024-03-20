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
import static org.hamcrest.Matchers.*;


public class TestStatusSold {

    Response response;
    JSONObject jsonObject;
    int ID;
    String NAME;
    ObjectMapper mapper;

    public TestStatusSold() {
        this.mapper = new ObjectMapper();
        this.jsonObject = new JSONObject();
        Faker FAKER = new Faker();
        this.ID = (int) FAKER.number().randomNumber();
        this.NAME = FAKER.animal().name();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("POST request is starting")
    @Description("Validating to POST request for the SOLD status")
    @Test(priority = 1)
    public void validatePostForSoldStatus() {

        Pet requestPet = petRequest();
        this.response = ApiStatus.post(requestPet);
        Pet responsePet = this.response.as(Pet.class);
        assertPostStatus(responsePet,requestPet);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("GET request is starting")
    @Description("Validating to GET request for the SOLD status")
    @Test(priority = 2)
    public void validateGetForSoldStatus(){

        Response response = ApiStatus.get(this.ID);
        assertThat(response.getStatusCode(),equalTo(200));
        Pet responsePet = response.as(Pet.class);
        assertGetStatus(responsePet);

    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("GET request is starting for ALL SOLD statuses")
    @Description("Validating to GET request for the ALL SOLD status")
    @Test(priority = 3)
    public void validateGetAllSoldStatuses() {

        this.response = ApiStatus.getSold();

        String status = this.response.path("status[3]").toString();
        String thirdId = this.response.path("id[3]").toString();
        assertAllStatus(status);


    }

    @Severity(SeverityLevel.CRITICAL)
    @Step("DELETE request is starting ")
    @Description("Validating to DELETE request for the SOLD status")
    @Test(priority = 4)
    public void validateDeleteForSoldStatus(){

        this.response = ApiStatus.delete(this.ID);
        assertDeleteStatus();
    }




    public Pet petRequest() {
       Pet requestPet = new Pet();
       requestPet.setId(this.ID);
       requestPet.setName(this.NAME);
       requestPet.setStatus(IStatus.statusSold);
       return requestPet;
   }


    public void assertPostStatus(Pet responsePet,Pet requestPet) {
        assertThat(responsePet.getId(), equalTo(requestPet.getId()));
        assertThat(responsePet.getName(), equalTo(requestPet.getName()));
        assertThat(responsePet.getStatus(), equalTo(requestPet.getStatus()));
        assertThat(this.response.getStatusCode() , equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));
    }

    public void assertGetStatus(Pet responsePet) {
        assertThat(responsePet.getId() , equalTo(this.ID));
        assertThat(responsePet.getName() , equalTo(this.NAME));
        assertThat(responsePet.getStatus(), equalTo(IStatus.statusSold));
        assertThat(this.response.getStatusCode() , equalTo(200));
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

    public void assertAllStatus(String status) {
        assertThat(status,equalTo(IStatus.statusSold));
        //assertThat(thirdId,equalTo("70"));
        assertThat(this.response.getStatusCode(),equalTo(200));
        assertThat(this.response.getStatusLine(),equalTo("HTTP/1.1 200 OK"));
        assertThat(this.response.getContentType() , equalTo("application/json"));
        assertThat(this.response.getHeader("Connection") , equalTo("keep-alive"));
        assertThat(this.response.getHeader("Transfer-Encoding") , equalTo("chunked"));

    }

}
