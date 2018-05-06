package com.mtt.guildhome.guildpost.infrastructure;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mtt.guildhome.guildpost.domain.GuildPost;
import com.mtt.guildhome.guildpost.domain.GuildPostFactory;
import com.mtt.guildhome.guildpost.domain.GuildPostRepository;

@Repository("guildPostRepository")
public class SpringMongoGuildPostRepository implements GuildPostRepository {

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public GuildPost findById(String id) {
		// TODO Auto-generated method stub
		return GuildPostFactory.reconstitutePost(id, "", "", "", "");
	}

	@Override
	public List<GuildPost> findByGuild(String guildId) {
		// TODO Auto-generated method stub
		return findByGuildAndUser(guildId, null);
	}

	@Override
	public List<GuildPost> findByGuildAndUser(String guildId, String userId) {
		// TODO Auto-generated method stub

		String id = UUID.randomUUID().toString();

		if (userId == null) {
			userId = "";
		}

		GuildPost gp = GuildPostFactory.reconstitutePost(id, guildId, userId, "findByGuildAndUser", "fByGuildAndUser");

		return Arrays.asList(gp);
	}

	@Override
	public void save(GuildPost post) {
		if (post.getId() == null) {
			post = GuildPostFactory.newPost(post.getGuildId(), post.getUserId(), post.getContent());
		}
		this.mongoOps.save(post, "post");
	}

	@Override
	public void delete(String id) {
		this.mongoOps.findAllAndRemove(new Query(Criteria.where("_id").is(id)), "post");
	}

}
