package org.example.sharesportsvendorbackend.post.dto.out;

import java.time.LocalDateTime;

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
	private String authorName;
	private String authorUuid;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}