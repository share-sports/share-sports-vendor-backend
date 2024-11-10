package org.example.sharesportsvendorbackend.stadium.application;

import java.util.List;

import org.example.sharesportsvendorbackend.stadium.dto.in.AddStadiumImageRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetStadiumImageListResponse;
import org.example.sharesportsvendorbackend.stadium.infrastructure.StadiumImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class StadiumImageService {

	private final StadiumImageRepository stadiumImageRepository;

	public void addStadiumImage(AddStadiumImageRequestDto addStadiumImageRequestDto) {

		stadiumImageRepository.save(addStadiumImageRequestDto.createEntity(addStadiumImageRequestDto));
	}

	public void updateStadiumImage(Long stadiumImageId, AddStadiumImageRequestDto addStadiumImageRequestDto) {

		stadiumImageRepository.save(addStadiumImageRequestDto.updateEntity(stadiumImageId, addStadiumImageRequestDto));
	}

	public void deleteStadiumImage(Long stadiumImageId) {

		stadiumImageRepository.deleteById(stadiumImageId);
	}

	public List<GetStadiumImageListResponse> getStadiumImageList(String stadiumUuid) {

		return stadiumImageRepository.findByStadiumUuid(stadiumUuid)
			.stream()
			.map(stadiumImage -> GetStadiumImageListResponse.builder()
				.stadiumImageId(stadiumImage.getStadiumImageId())
				.imageSrc(stadiumImage.getImageSrc())
				.thumbnail(stadiumImage.isThumbnail())
				.build())
			.toList();
	}
}
