package org.example.sharesportsvendorbackend.post.dto.in;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePostRequestDto {

	private String postUuid;
	private String title;
	private String content;


	public Post updateEntity(Long postId, Host host) {
		return Post.builder()
			.postId(postId)
			.postUuid(postUuid)
			.title(title)
			.content(content)
			.deleted(false)
			.authorName(host.getName())
			.authorUuid(host.getHostUuid())
			.build();
	}
}
