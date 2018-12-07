package com.detectify.challenge.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Data
public class ScreenshotUrlData implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Set<String> urls;

}
