package org.example.sharesportsvendorbackend.post.dto.in;

import org.example.sharesportsvendorbackend.post.domain.PostType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetPostListRequestDto {

	private String stadiumUuid;
	private PostType postType;
}
