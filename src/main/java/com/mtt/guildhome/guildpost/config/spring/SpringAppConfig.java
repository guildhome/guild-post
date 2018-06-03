package com.mtt.guildhome.guildpost.config.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class SpringAppConfig {

	@Value("${guildpost.mongodb.host}")
	private String guildPostMongoDbHost;

	@Value("${guildpost.mongodb.database.name}")
	private String guildPostMongoDbName;

	public @Bean MongoClient mongoClient() {
		return new MongoClient(this.guildPostMongoDbHost);
	}

	public @Bean MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), this.guildPostMongoDbName);
	}
}
