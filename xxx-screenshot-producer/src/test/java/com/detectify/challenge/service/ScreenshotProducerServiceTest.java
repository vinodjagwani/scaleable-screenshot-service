package com.detectify.challenge.service;


import com.detectify.challenge.dto.ScreenshotData;
import com.detectify.challenge.service.impl.ScreenshotProducerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScreenshotProducerServiceTest {


    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ScreenshotProducerServiceImpl screenshotProducerService;


    @Test
    public void testSendUrlForScreenshot() {
        doNothing().when(rabbitTemplate).convertAndSend(any(String.class), any(ScreenshotData.class));
        screenshotProducerService.sendUrlForScreenshot(ScreenshotData.builder()
                .id("www.google.com").url("www.google.com").build());
        verify(rabbitTemplate, times(1)).convertAndSend(any(String.class), any(ScreenshotData.class));
    }

}
