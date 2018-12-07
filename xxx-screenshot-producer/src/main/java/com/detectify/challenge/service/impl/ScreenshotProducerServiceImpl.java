package com.detectify.challenge.service.impl;

import com.detectify.challenge.constant.Constants;
import com.detectify.challenge.dto.ScreenshotData;
import com.detectify.challenge.service.ScreenshotProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Service
public class ScreenshotProducerServiceImpl implements ScreenshotProducerService {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void sendUrlForScreenshot(final ScreenshotData data) {
        int q = new Random().nextInt((2 - 1) + 1) + 1;
        rabbitTemplate.convertAndSend(Constants.QUEUE_SCREENSHOTS_ + q, data);
    }
}
