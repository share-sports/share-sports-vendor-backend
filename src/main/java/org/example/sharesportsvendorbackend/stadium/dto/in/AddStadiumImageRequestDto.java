package org.example.sharesportsvendorbackend.stadium.dto.in;

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

	private String imageSrc;
	private boolean thumbnail;
	private String stadiumUuid;

	public StadiumImage createEntity(AddStadiumImageRequestDto addStadiumImageRequestDto) {
		return StadiumImage.builder()
				.imageSrc(addStadiumImageRequestDto.getImageSrc())
				.thumbnail(addStadiumImageRequestDto.isThumbnail())
				.stadiumUuid(addStadiumImageRequestDto.getStadiumUuid())
				.build();
	}

	public StadiumImage updateEntity(Long stadiumImageId, AddStadiumImageRequestDto addStadiumImageRequestDto) {

		return StadiumImage.builder()
				.stadiumImageId(stadiumImageId)
				.imageSrc(addStadiumImageRequestDto.getImageSrc())
				.thumbnail(addStadiumImageRequestDto.isThumbnail())
				.stadiumUuid(addStadiumImageRequestDto.getStadiumUuid())
				.build();
	}
}
