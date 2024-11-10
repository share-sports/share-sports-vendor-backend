package org.example.sharesportsvendorbackend.post.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetPostListResponseDto {

	private String postUuid;
	private String title;
	private String content;
	private String authorName;
	private String authorUuid;
	private boolean delete;
}
