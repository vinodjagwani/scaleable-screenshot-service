package com.detectify.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorFieldData implements Serializable {

    private String code;

    private String param;

    private String message;
}
