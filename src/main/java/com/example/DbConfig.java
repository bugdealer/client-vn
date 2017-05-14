package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import com.example.properties.MongoProperties;
import com.example.properties.OdlProperties;
import com.mongodb.MongoClientURI;


@Component
public class DbConfig {
	

	@Autowired
	private MongoProperties mongoProperties;

	
	private MongoClientURI uri;


    public @Bean(name="mongoTemplate") MongoTemplate mongoTemplate() throws Exception {
    	uri = new MongoClientURI("mongodb://"+mongoProperties.getAddress()+
    			":"+mongoProperties.getPort()+
    			"/"+mongoProperties.getDatabase());
        return new MongoTemplate(new SimpleMongoDbFactory(uri));
    }
   
    
}
