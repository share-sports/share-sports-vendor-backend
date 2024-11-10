package org.example.sharesportsvendorbackend.stadium.dto.in;

import java.util.List;
import java.util.stream.Collectors;

import org.example.sharesportsvendorbackend.stadium.domain.StadiumImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AddStadiumImageRequestDto {

	private List<StadiumImageDto> images;
	private String stadiumUuid;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class StadiumImageDto {
		private String imageSrc;
		private boolean thumbnail;
	}

	public List<StadiumImage> createEntities() {
		return images.stream()
			.map(imageDto -> StadiumImage.builder()
				.imageSrc(imageDto.getImageSrc())
				.thumbnail(imageDto.isThumbnail())
				.stadiumUuid(this.stadiumUuid)
				.build())
			.collect(Collectors.toList());
	}
}
