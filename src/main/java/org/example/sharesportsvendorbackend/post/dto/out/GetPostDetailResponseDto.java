package org.example.sharesportsvendorbackend.post.dto.out;

import java.time.LocalDateTime;

import org.example.sharesportsvendorbackend.post.domain.PostType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetPostDetailResponseDto {


	private String postUuid;
	private String title;
	private String content;
	private String authorName;
	private String authorUuid;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private PostType postType;
}
