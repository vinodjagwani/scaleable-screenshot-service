package com.detectify.challenge.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Data
@Builder
public class ScreenshotData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String url;

}
