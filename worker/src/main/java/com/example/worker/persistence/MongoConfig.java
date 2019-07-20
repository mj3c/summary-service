package com.example.worker.persistence;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("${ss.mongo.host}")
    private String mongoHost;

    @Value("${ss.mongo.port}")
    private int mongoPort;

    @Value("${ss.mongo.db}")
    private String mongoDb;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Override
    protected String getDatabaseName() {
        return mongoDb;
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.worker.persistence");
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception {
        // Remove _class field from saved documents
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new MongoTemplate(mongoDbFactory(), converter);
    }
}
