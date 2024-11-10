package org.example.sharesportsvendorbackend.post.dto.in;

import java.util.UUID;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.post.domain.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddPostRequestDto {

	private String title;
	private String content;
	private String stadiumUuid;

	public Post createEntity(Host host) {
		return Post.builder()
				.title(title)
				.postUuid(UUID.randomUUID().toString().substring(0, 8))
				.content(content)
				.deleted(false)
				.authorName(host.getName())
				.authorUuid(host.getHostUuid())
				.stadiumUuid(stadiumUuid)
				.build();
	}


}
