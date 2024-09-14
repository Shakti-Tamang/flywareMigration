package com.nextstep.flyway.pac.migrate.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ApiResposne<T>{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String status_message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("statusCode")
    private int status_code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("List")
    List<T> list;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("object")
    T ab;

}
