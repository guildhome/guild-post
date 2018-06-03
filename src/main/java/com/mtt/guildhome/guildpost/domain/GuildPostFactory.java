package com.mtt.guildhome.guildpost.domain;

import java.util.UUID;

public class GuildPostFactory {

	public static GuildPost newPost(String guildId, String userId, String content) {
		String id = UUID.randomUUID().toString();
		return new GuildPost(id, guildId, userId, content, generateContentSneakpeak(content));
	}

	public static GuildPost updatedPost(String guildPostId, String guildId, String userId, String content) {
		return new GuildPost(guildPostId, guildId, userId, content, generateContentSneakpeak(content));
	}

	// public static GuildPost reconstitutePost(String id, String guildId, String
	// userId, String content,
	// String contentSneakpeak) {
	// return new GuildPost(id, guildId, userId, content, contentSneakpeak);
	// }

	private static String generateContentSneakpeak(String content) {
		int endIndex = content.length() > 30 ? 30 : content.length();
		return content.substring(0, endIndex);
	}
}
