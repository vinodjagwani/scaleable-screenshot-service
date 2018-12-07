package com.detectify.challenge.facade.impl;

import com.detectify.challenge.dto.ScreenshotData;
import com.detectify.challenge.dto.ScreenshotUrlData;
import com.detectify.challenge.facade.ScreenshotProducerFacade;
import com.detectify.challenge.service.ScreenshotProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Slf4j
@Service
public class ScreenshotProducerFacadeImpl implements ScreenshotProducerFacade {


    @Autowired
    private ScreenshotProducerService screenshotProducerService;


    @Override
    public void sendUrlsForScreenshot(final ScreenshotUrlData data) {
        data.getUrls().forEach(url -> {
            if (StringUtils.isNotEmpty(url)) {
                final String domain = getHostFromUrl(url);
                screenshotProducerService.sendUrlForScreenshot(ScreenshotData.builder().id(domain).url(url).build());
            }
        });
    }


    private String getHostFromUrl(final String value) {
        try {
            URL url = new URL(value);
            return url.getHost();
        } catch (Exception ex) {
            log.error("Invalid url {}", ex);
            return value;
        }
    }


}
