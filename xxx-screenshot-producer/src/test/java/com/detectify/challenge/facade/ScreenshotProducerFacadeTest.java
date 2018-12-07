package com.detectify.challenge.facade;


import com.detectify.challenge.dto.ScreenshotData;
import com.detectify.challenge.dto.ScreenshotUrlData;
import com.detectify.challenge.facade.impl.ScreenshotProducerFacadeImpl;
import com.detectify.challenge.service.ScreenshotProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ScreenshotProducerFacadeTest {


    @Mock
    private ScreenshotProducerService screenshotProducerService;


    @InjectMocks
    private ScreenshotProducerFacadeImpl screenshotProducerFacade;


    @Test
    public void testSendUrlForScreenshot() {
        final ScreenshotUrlData data = getScreenshotUrlData();
        doNothing().when(screenshotProducerService).sendUrlForScreenshot(any(ScreenshotData.class));
        screenshotProducerFacade.sendUrlsForScreenshot(data);
        verify(screenshotProducerService, times(1)).sendUrlForScreenshot(any(ScreenshotData.class));
    }


    private ScreenshotUrlData getScreenshotUrlData() {
        ScreenshotUrlData data = new ScreenshotUrlData();
        Set<String> urls = new HashSet<>();
        urls.add("www.google.com");
        data.setUrls(urls);
        return data;
    }
}
