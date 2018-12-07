package com.xxx.challenge.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Data
@Document(collection = "screenshots")
public class ScreenshotEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String url;

    private String screenshot;
}
