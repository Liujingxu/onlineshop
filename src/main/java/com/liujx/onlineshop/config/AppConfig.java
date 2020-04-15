package com.liujx.onlineshop.config;


import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author lenovo
 */
@Configuration
public class AppConfig {

    @Value("${mongod.host}")
    private String host;

    @Value("${mongod.port}")
    private Integer port;

    @Value("${mongod.db}")
    private String dataBase;

    @Value("${mongod.user}")
    private String user;

    @Value("${mongod.password}")
    private String password;


    @Bean
    public MongoClient mongoClient(){
        MongoCredential credential = MongoCredential.createCredential(user, "admin", password.toCharArray());
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(host, port))))
                .credential(credential)
                .build());
    }

    @Bean
    public MongoDatabase mongoDatabase(){
        return mongoClient().getDatabase(dataBase);
    }



}
