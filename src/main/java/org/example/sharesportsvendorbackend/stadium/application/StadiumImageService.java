package org.example.sharesportsvendorbackend.stadium.application;

import java.util.List;

import org.example.sharesportsvendorbackend.stadium.domain.StadiumImage;
import org.example.sharesportsvendorbackend.stadium.dto.StadiumImageRequestDto;
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

	// @Transactional
	// public void addStadiumImageList(List<StadiumImageRequestDto> requestDtoList) {
	// 	// 썸네일 검증
	// 	validateThumbnail(requestDtoList);
	//
	// 	List<StadiumImage> stadiumImageList = requestDtoList.stream()
	// 		.map(dto -> StadiumImage.builder()
	// 			.stadiumUuid(dto.getStadiumUuid())
	// 			.imageSrc(dto.getImageSrc())
	// 			.thumbnail(dto.isThumbnail())
	// 			.build())
	// 		.toList();
	//
	// 	stadiumImageRepository.saveAll(stadiumImageList);
	// }

	@Transactional
	public void updateStadiumImageList(List<StadiumImageRequestDto> requestDtoList) {
		// 썸네일 검증
		validateThumbnail(requestDtoList);

		// stadiumUuid 검증 (모든 이미지가 같은 stadium에 속하는지)
		validateSameStadium(requestDtoList);

		String stadiumUuid = requestDtoList.get(0).getStadiumUuid();

		// 기존 이미지들 삭제
		stadiumImageRepository.deleteByStadiumUuid(stadiumUuid);

		// 새로운 이미지들 저장
		List<StadiumImage> stadiumImageList = requestDtoList.stream()
			.map(dto -> StadiumImage.builder()
				.stadiumUuid(dto.getStadiumUuid())
				.imageSrc(dto.getImageSrc())
				.thumbnail(dto.isThumbnail())
				.build())
			.toList();

		stadiumImageRepository.saveAll(stadiumImageList);
	}

	private void validateThumbnail(List<StadiumImageRequestDto> requestDtoList) {
		long thumbnailCount = requestDtoList.stream()
			.filter(StadiumImageRequestDto::isThumbnail)
			.count();

		if (thumbnailCount != 1) {
			throw new RuntimeException("썸네일은 반드시 하나만 설정해야 합니다.");
		}
	}

	private void validateSameStadium(List<StadiumImageRequestDto> requestDtoList) {
		long distinctStadiumCount = requestDtoList.stream()
			.map(StadiumImageRequestDto::getStadiumUuid)
			.distinct()
			.count();

		if (distinctStadiumCount > 1) {
			throw new RuntimeException("서로 다른 구장의 이미지는 함께 업데이트할 수 없습니다.");
		}
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
