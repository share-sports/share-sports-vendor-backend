package org.example.sharesportsvendorbackend.stadium.dto.in;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UpdateStadiumImageRequestDto {
	private String stadiumUuid;
	private List<StadiumImageDto> images;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class StadiumImageDto {
		private Long stadiumImageId; // 기존 이미지인 경우 ID 존재
		private String imageSrc;
		private boolean thumbnail;
	}
}