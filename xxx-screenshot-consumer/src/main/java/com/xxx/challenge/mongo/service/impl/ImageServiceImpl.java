package com.xxx.challenge.mongo.service.impl;

import com.xxx.challenge.mongo.service.ImageService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;

/**
 * Created by vinodjagwani on 11/27/18.
 */

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    private GridFSBucket gridFSBucket;


    @Override
    public ObjectId uploadFromStream(final String url, final InputStream inputStream) {
        final GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(358400)
                .metadata(new Document("type", "image"));
        return gridFSBucket.uploadFromStream(url, inputStream, options);
    }


    @Override
    public Optional<InputStreamResource> downloadFromStream(final String objectId) {
        final GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(objectId));
        final InputStreamResource imageStream = new InputStreamResource(downloadStream) {
            @Override
            public long contentLength() {
                return downloadStream.getGridFSFile().getLength();
            }
        };
        return Optional.of(imageStream);
    }
}
