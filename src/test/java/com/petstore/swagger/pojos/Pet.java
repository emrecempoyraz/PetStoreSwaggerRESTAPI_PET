package com.petstore.swagger.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<Object> photoUrls = null;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("status")
    private String status;
}
