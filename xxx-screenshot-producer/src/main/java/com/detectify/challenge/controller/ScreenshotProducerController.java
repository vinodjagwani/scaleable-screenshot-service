package com.detectify.challenge.controller;


import com.detectify.challenge.dto.ScreenshotUrlData;
import com.detectify.challenge.facade.ScreenshotProducerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Slf4j
@RestController
@RequestMapping("/v1/xxx")
public class ScreenshotProducerController {


    @Autowired
    private ScreenshotProducerFacade screenshotProducerFacade;


    @RequestMapping(value = "/screenshots", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> sendUrlsForScreenshot(@Valid @RequestBody final ScreenshotUrlData data) {
        log.info("Requested urls for screenshot {}", data);
        screenshotProducerFacade.sendUrlsForScreenshot(data);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
