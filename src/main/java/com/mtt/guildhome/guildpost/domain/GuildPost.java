package com.mtt.guildhome.guildpost.domain;

import javax.validation.constraints.NotBlank;

public class GuildPost {

	private String id;

	@NotBlank(message = "guildId should be non-blank")
	private String guildId;

	@NotBlank(message = "userId should be non-blank")
	private String userId;

	@NotBlank(message = "content should be non-blank")
	private String content;

	private String contentSneakpeak;

	private GuildPost() {
	}

	GuildPost(String id, String guildId, String userId) {
		this();
		this.id = id;
		this.guildId = guildId;
		this.userId = userId;
	}

	GuildPost(String id, String guildId, String userId, String content, String contentSneakpeak) {
		this(id, guildId, userId);
		this.setContent(content);
		this.setSneakpeakContent(contentSneakpeak);
	}

	public String getId() {
		return id;
	}

	public String getGuildId() {
		return guildId;
	}

	public String getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public String getContentSneakpeak() {
		return contentSneakpeak;
	}

	protected void setContent(String content) {
		this.content = content;
	}

	protected void setSneakpeakContent(String contentSneakpeak) {
		this.contentSneakpeak = contentSneakpeak;
	}
}
