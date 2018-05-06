package com.mtt.guildhome.guildpost.domain;

import java.util.List;

public interface GuildPostRepository {

	public GuildPost findById(String id);

	public List<GuildPost> findByGuild(String guildId);

	public List<GuildPost> findByGuildAndUser(String guildId, String userId);

	public void save(GuildPost post);

	public void delete(String id);
}
