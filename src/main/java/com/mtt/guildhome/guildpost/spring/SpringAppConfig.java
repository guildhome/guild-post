package com.mtt.guildhome.guildpost.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class SpringAppConfig {

	public @Bean MongoClient mongoClient() {
		return new MongoClient("192.168.72.107");
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "guildhome");
	}
}
