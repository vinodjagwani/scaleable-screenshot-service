package com.detectify.challenge.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Data
public class ErrorResponseData {

    private String code;

    private String message;

    private List<ErrorFieldData> errors = new ArrayList<>();

}
