package com.xxx.challenge.config;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vinodjagwani on 11/27/18.
 */
@Configuration
public class MongoConfig {


    @Value("${mongo.host:localhost}")
    private String host;

    @Value("${mongo.port:27017}")
    private String port;

    @Value("${mongo.username:admin}")
    private String mongoUsername;

    @Value("${mongo.password:admin}")
    private String mongoPassword;

    @Value("${mongo.database:test}")
    private String mongoDatabase;

    @Value("${mongo.fs.bucket:test-bucket}")
    private String mongoFsBucket;


    @Bean
    public MongoClient mongoClient() {
        //final String uri = "mongodb://" + mongoUsername + ":" + mongoPassword + "@" + host + ":" + port + "/" + mongoDatabase;
        return new MongoClient(host);
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(mongoDatabase);
    }

    @Bean
    public GridFSBucket gridFSBucket() {
        return GridFSBuckets.create(mongoDatabase(), mongoFsBucket);
    }

}
