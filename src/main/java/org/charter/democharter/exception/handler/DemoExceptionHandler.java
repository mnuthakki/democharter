package org.charter.democharter.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charter.democharter.business.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice("org.charter.democharter")
public class DemoExceptionHandler {

    private static final Logger log = LogManager.getLogger(DemoExceptionHandler.class);
    private static final String GENERIC_ERROR = "Unknown Error Occurred";

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseModel<Object>> exceptionHandler(Exception exception, WebRequest request) {
        String requestURI = ((ServletWebRequest) request).getRequest().getRequestURI().replace(request.getContextPath(), "");
        log.error("Exception Occurred at " + requestURI);
        ResponseModel<Object> responseModel = org.charter.democharter.business.model.ResponseModel.of(exception.getMessage());
        List<String> errorMessages = new ArrayList<>(Arrays.asList(GENERIC_ERROR, exception.getMessage()));
        responseModel.setErrorMessage(errorMessages);
        responseModel.setStatusCode(HttpStatus.BAD_REQUEST.value());
        responseModel.setStatusText("Failure");
        return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
    }
}
