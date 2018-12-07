package com.xxx.challenge.service.impl;


import com.xxx.challenge.dto.ScreenshotsData;
import com.xxx.challenge.mongo.service.ImageService;
import com.xxx.challenge.service.CaptureScreenshotService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.bson.types.ObjectId;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Slf4j
@Service
public class CaptureScreenshotServiceImpl implements CaptureScreenshotService {


    @Autowired
    private ImageService imageService;


    @Override
    public void captureScreen(final ScreenshotsData data) {
        final WebDriver webDriver = new FirefoxDriver();
        try {
            final String url = data.getUrl();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            webDriver.get(url);
            final Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies
                    .viewportPasting(1000)).takeScreenshot(webDriver);
            File file = new File("screenshot.png");
            ImageIO.write(screenshot.getImage(), "png", file);
            final ObjectId objectId = imageService.uploadFromStream(data.getUrl(), FileUtils.openInputStream(file));
            log.debug("ObjectId mapping for screenshot {}, {}", objectId, url);
        } catch (Exception ex) {
            log.error("{}", ex);
        } finally {
            webDriver.quit();
        }
    }
}
