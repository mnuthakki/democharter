package org.charter.democharter.business.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseModel<T> {
    private T body;
    private Integer statusCode;
    private String statusText;
    private List<String> errorMessage;

    public static <T> ResponseModel<T> of(T body) {
        ResponseModel<T> response = new ResponseModel<>();
        response.setBody(body);
        return response;
    }

    public static <T> ResponseModel<T> of(T body, Integer statusCode, String statusText) {
        ResponseModel<T> response = ResponseModel.of(body);
        response.setStatusCode(statusCode);
        response.setStatusText(statusText);
        return response;
    }

}
