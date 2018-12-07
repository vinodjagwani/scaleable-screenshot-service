package com.xxx.challenge.controller;


import com.xxx.challenge.exception.CheckedSupplier;
import com.xxx.challenge.mongo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@RestController
@RequestMapping("/v1/xxx")
public class ScreenshotController {


    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/screenshots/{object_id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getScreenshotById(@PathVariable("object_id") final String objectId) {
        return imageService.downloadFromStream(objectId)
                .map(stream -> ResponseEntity.ok()
                        .contentLength(((CheckedSupplier<Long>) stream::contentLength).get())
                        .contentType(MediaType.IMAGE_PNG)
                        .body(stream)
                )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
