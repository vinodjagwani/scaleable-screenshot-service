package com.xxx.challenge.listener;


import com.xxx.challenge.constant.Constants;
import com.xxx.challenge.dto.ScreenshotsData;
import com.xxx.challenge.service.CaptureScreenshotService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Component
public class ScreenshotsMessageListener {


    @Autowired
    private CaptureScreenshotService captureScreenshotService;


    @RabbitListener(queues = Constants.QUEUE_SCREENSHOTS_1)
    public void processScreenshots(final ScreenshotsData data) {
        captureScreenshotService.captureScreen(data);
    }


}
