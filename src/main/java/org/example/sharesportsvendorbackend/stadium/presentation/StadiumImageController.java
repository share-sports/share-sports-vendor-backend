package org.example.sharesportsvendorbackend.stadium.presentation;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponse;
import org.example.sharesportsvendorbackend.stadium.application.StadiumImageService;
import org.example.sharesportsvendorbackend.stadium.dto.in.AddStadiumImageRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetStadiumImageListResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "구장 이미지 관리 API", description = "구장 이미지 관련 API endpoints")
@RequestMapping("/api/host/stadium/image")
public class StadiumImageController {

	private final StadiumImageService stadiumImageService;

	@Operation(summary = "구장 이미지 추가", description = "구장 이미지 추가 API")
	@PostMapping
	public BaseResponse<Void> addStadiumImage(@RequestBody AddStadiumImageRequestDto addStadiumImageRequestDto) {

		stadiumImageService.addStadiumImage(addStadiumImageRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 이미지 수정", description = "구장 이미지 수정 API")
	@PutMapping
	public BaseResponse<Void> updateStadiumImage(Long stadiumImageId,
		@RequestBody AddStadiumImageRequestDto addStadiumImageRequestDto) {

		stadiumImageService.updateStadiumImage(stadiumImageId, addStadiumImageRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 이미지 삭제", description = "구장 이미지 삭제 API")
	@DeleteMapping
	public BaseResponse<Void> deleteStadiumImage(Long stadiumImageId) {

		stadiumImageService.deleteStadiumImage(stadiumImageId);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 이미지 리스트 조회", description = "구장 이미지 리스트 조회 API")
	@GetMapping
	public BaseResponse<List<GetStadiumImageListResponse>> getStadiumImageList(String stadiumUuid) {

		return new BaseResponse<>(
			stadiumImageService.getStadiumImageList(stadiumUuid)
		);
	}
}
