package com.xxx.challenge.mongo.repository;


import com.xxx.challenge.mongo.entity.ScreenshotEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by vinodjagwani on 11/27/18.
 */

public interface ScreenshotRepository extends MongoRepository<ScreenshotEntity, String> {

}
