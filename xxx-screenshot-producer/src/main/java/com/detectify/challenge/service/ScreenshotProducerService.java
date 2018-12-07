package com.detectify.challenge.service;


import com.detectify.challenge.dto.ScreenshotData;

/**
 * Created by vinodjagwani on 11/27/18.
 */
public interface ScreenshotProducerService {

    void sendUrlForScreenshot(final ScreenshotData data);

}
