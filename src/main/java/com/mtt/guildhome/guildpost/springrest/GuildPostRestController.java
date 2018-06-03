package com.mtt.guildhome.guildpost.springrest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtt.guildhome.guildpost.domain.GuildPost;
import com.mtt.guildhome.guildpost.domain.GuildPostFactory;
import com.mtt.guildhome.guildpost.domain.GuildPostRepository;

@RestController
@RequestMapping("/v1/guilds/{guildId}/posts")
public class GuildPostRestController {

	@Autowired
	private GuildPostRepository guildPostRepository;

	@GetMapping("/{id}")
	public ResponseEntity<?> getPost(@PathVariable String guildId, @PathVariable String id) {
		GuildPost post = this.guildPostRepository.findById(id);

		if (post == null) {
			return new ResponseEntity<>("id-" + id, HttpStatus.NOT_FOUND);
		}
		if (!post.getGuildId().equalsIgnoreCase(guildId)) {
			return ResponseEntity.badRequest().body("guildId mismatch");
		}
		return ResponseEntity.ok(post);
	}

	@GetMapping
	public List<GuildPost> getPostsForGuild(@PathVariable String guildId,
			@RequestParam(required = false) String userId) {
		// TODO: pagination
		if (userId == null) {
			return this.guildPostRepository.findByGuild(guildId);
		}
		return this.guildPostRepository.findByGuildAndUser(guildId, userId);
	}

	@PostMapping
	public ResponseEntity<?> createPost(@PathVariable String guildId, @Valid @RequestBody GuildPost post) {
		if (!guildId.equalsIgnoreCase(post.getGuildId())) {
			return ResponseEntity.badRequest().body("guildId mismatch");
		}

		post = GuildPostFactory.newPost(guildId, post.getUserId(), post.getContent());
		this.guildPostRepository.save(post);
		return ResponseEntity.ok(post);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePost(@PathVariable String guildId, @PathVariable String id,
			@Valid @RequestBody GuildPost post) {
		if (!guildId.equalsIgnoreCase(post.getGuildId()) || !id.equalsIgnoreCase(post.getId())) {
			return ResponseEntity.badRequest().body("guildId/id mismatch");
		}

		GuildPost existingPost = this.guildPostRepository.findById(id);

		if (existingPost == null) {
			return new ResponseEntity<>("id-" + id, HttpStatus.NOT_FOUND);
		}
		if (!existingPost.getGuildId().equalsIgnoreCase(guildId)) {
			return ResponseEntity.badRequest().body("guildId mismatch");
		}

		post = GuildPostFactory.updatedPost(existingPost.getId(), existingPost.getGuildId(), existingPost.getUserId(),
				post.getContent());
		this.guildPostRepository.save(post);
		return ResponseEntity.ok(post);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePost(@PathVariable String guildId, @PathVariable String id) {
		GuildPost post = this.guildPostRepository.findById(id);

		if (post == null) {
			return new ResponseEntity<>("id-" + id, HttpStatus.NOT_FOUND);
		}
		if (!post.getGuildId().equalsIgnoreCase(guildId)) {
			return ResponseEntity.badRequest().body("guildId mismatch");
		}

		this.guildPostRepository.delete(id);
		return ResponseEntity.noContent().build();
	}

}
