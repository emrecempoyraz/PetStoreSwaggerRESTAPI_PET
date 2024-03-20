package com.petstore.swagger.api;

import com.petstore.swagger.datas.PetPath;
import com.petstore.swagger.pojos.Pet;
import io.restassured.response.Response;

public class ApiStatus {

    public static Response getAvailable() {
        return RestResource.getAvailable(PetPath.PET_AVAILABLE);
    }

    public static Response getPending() {
        return RestResource.getPending(PetPath.PET_PENDING);
    }

    public static Response getSold() {
        return RestResource.getSold(PetPath.PET_SOLD);
    }

    public static Response post(Pet requestPet) {
        return RestResource.post(PetPath.PET, requestPet);
    }

    public static Response get(int id) {
        return RestResource.get(PetPath.PET + PetPath.GET_ID + id);
    }

    public static Response delete(int id) {
        return RestResource.delete(PetPath.PET + PetPath.GET_ID + id);
    }
}