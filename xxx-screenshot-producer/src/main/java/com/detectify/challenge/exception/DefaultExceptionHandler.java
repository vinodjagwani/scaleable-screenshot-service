package com.detectify.challenge.exception;

import com.detectify.challenge.dto.ErrorFieldData;
import com.detectify.challenge.dto.ErrorResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Slf4j
@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DefaultExceptionHandler {


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseData> handleException(final MethodArgumentNotValidException ex) {
        log.error("{}", ex);
        List<ErrorFieldData> fieldDataList = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ErrorFieldData fieldData = new ErrorFieldData();
            fieldData.setCode(fieldError.getCode());
            fieldData.setParam(fieldError.getField());
            fieldData.setMessage(fieldError.getDefaultMessage());
            fieldDataList.add(fieldData);
        }
        ErrorResponseData responseData = new ErrorResponseData();
        responseData.setErrors(fieldDataList);
        responseData.setCode(ErrorCodeEnum.INVALID_FORMAT.name());
        responseData.setMessage(ErrorCodeEnum.INVALID_FORMAT.name());
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseData> handleJSONException(final HttpMessageNotReadableException ex) {
        log.error("{}", ex);
        ErrorResponseData responseData = new ErrorResponseData();
        responseData.setCode(ErrorCodeEnum.INVALID_REQUEST.name());
        responseData.setErrors(new ArrayList<>());
        responseData.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

}
