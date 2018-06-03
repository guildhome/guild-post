package com.mtt.guildhome.guildpost.infrastructure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mtt.guildhome.guildpost.domain.GuildPost;
import com.mtt.guildhome.guildpost.domain.GuildPostRepository;

@Repository("guildPostRepository")
public class SpringMongoGuildPostRepository implements GuildPostRepository {

	@Autowired
	private MongoOperations mongoOps;

	@Value("${guildpost.mongodb.collection.name}")
	private String guildPostMongoDbCollectionName;

	private SpringMongoGuildPostRepository() {
		super();
	}

	public SpringMongoGuildPostRepository(MongoOperations mongoOps, String guildPostMongoDbCollectionName) {
		this();
		this.mongoOps = mongoOps;
		this.guildPostMongoDbCollectionName = guildPostMongoDbCollectionName;
	}

	@Override
	public GuildPost findById(String id) {
		GuildPost post = this.mongoOps.findById(id, GuildPost.class, this.guildPostMongoDbCollectionName);
		return post;
	}

	@Override
	public List<GuildPost> findByGuild(String guildId) {
		return findByGuildAndUser(guildId, null);
	}

	@Override
	public List<GuildPost> findByGuildAndUser(String guildId, String userId) {
		Criteria criteria = Criteria.where("guildId").is(guildId);
		if (userId != null) {
			criteria.and("userId").is(userId);
		}
		List<GuildPost> posts = this.mongoOps.find(new Query(criteria), GuildPost.class,
				this.guildPostMongoDbCollectionName);
		return posts;
	}

	@Override
	public void save(GuildPost post) {
		this.mongoOps.save(post, this.guildPostMongoDbCollectionName);
	}

	@Override
	public void delete(String id) {
		this.mongoOps.findAllAndRemove(new Query(Criteria.where("_id").is(id)), this.guildPostMongoDbCollectionName);
	}

}
