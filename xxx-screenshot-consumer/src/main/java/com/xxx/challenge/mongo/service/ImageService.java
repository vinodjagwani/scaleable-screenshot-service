package com.xxx.challenge.mongo.service;

import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;

import java.io.InputStream;
import java.util.Optional;

/**
 * Created by vinodjagwani on 11/27/18.
 */
public interface ImageService {

    ObjectId uploadFromStream(final String url, final InputStream inputStream);

    Optional<InputStreamResource> downloadFromStream(final String objectId);

}
