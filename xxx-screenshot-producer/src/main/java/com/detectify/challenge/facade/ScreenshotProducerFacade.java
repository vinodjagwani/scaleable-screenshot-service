package com.detectify.challenge.facade;


import com.detectify.challenge.dto.ScreenshotUrlData;

/**
 * Created by vinodjagwani on 11/27/18.
 */

public interface ScreenshotProducerFacade {

    void sendUrlsForScreenshot(final ScreenshotUrlData data);
}
