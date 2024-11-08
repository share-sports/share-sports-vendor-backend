package org.example.sharesportsvendorbackend.stadium.presentation;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponse;
import org.example.sharesportsvendorbackend.stadium.application.StadiumService;
import org.example.sharesportsvendorbackend.stadium.dto.in.AssignStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.DeleteStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.GetDetailStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.UpdateStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetDetailStadiumResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "구장 관리 API", description = "구장 관련 API endpoints")
@RequestMapping("/api/host/stadium")
public class StadiumController {

	private final StadiumService stadiumService;

	@Operation(summary = "구장 상세 조회", description = "구장 상세 조회 API")
	@GetMapping("/detail")
	public BaseResponse<GetDetailStadiumResponseDto> getDetailStadium(GetDetailStadiumRequestDto getDetailStadiumRequestDto) {

		return new BaseResponse<>(
			stadiumService.getDetailStadium(getDetailStadiumRequestDto)
			);
	}

	@Operation(summary = "구장 생성", description = "구장 생성 API")
	@PostMapping
	public BaseResponse<Void> assignStadium(AssignStadiumRequestDto assignStadiumRequestDto) {

		stadiumService.assignStadium(assignStadiumRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 수정", description = "구장 수정 API")
	@PutMapping
	public BaseResponse<Void> updateStadium(@AuthenticationPrincipal UserDetails userDetails,
		UpdateStadiumRequestDto updateStadiumRequestDto) {

		stadiumService.updateStadium(userDetails.getUsername(), updateStadiumRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 삭제", description = "구장 삭제 API")
	@DeleteMapping
	public BaseResponse<Void> deleteStadium(DeleteStadiumRequestDto deleteStadiumRequestDto) {

		stadiumService.deleteStadium(deleteStadiumRequestDto);
		return new BaseResponse<>();
	}
}
