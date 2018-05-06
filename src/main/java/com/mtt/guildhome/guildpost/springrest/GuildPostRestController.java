package com.mtt.guildhome.guildpost.springrest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mtt.guildhome.guildpost.domain.GuildPost;
import com.mtt.guildhome.guildpost.domain.GuildPostRepository;

@RestController
@RequestMapping("/guild/post")
public class GuildPostRestController {

	@Autowired
	private GuildPostRepository guildPostRepository;

	@GetMapping("/{id}")
	public GuildPost getPost(@PathVariable String id) {
		return this.guildPostRepository.findById(id);
	}

	@GetMapping
	public List<GuildPost> getPosts(@RequestParam String guildId, @RequestParam(required = false) String userId) {
		if (userId == null) {
			return this.guildPostRepository.findByGuild(guildId);
		}

		return this.guildPostRepository.findByGuildAndUser(guildId, userId);
	}

	@PostMapping
	public void createPost(@RequestBody GuildPost post) {
		this.guildPostRepository.save(post);
	}

	@PutMapping
	public void updatePost(@RequestBody GuildPost post) {
		this.guildPostRepository.save(post);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePost(@PathVariable String id) {
		this.guildPostRepository.delete(id);
	}

}
