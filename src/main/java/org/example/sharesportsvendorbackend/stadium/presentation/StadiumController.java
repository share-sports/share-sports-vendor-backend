package org.example.sharesportsvendorbackend.stadium.presentation;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponse;
import org.example.sharesportsvendorbackend.stadium.application.StadiumService;
import org.example.sharesportsvendorbackend.stadium.dto.in.AssignStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.DeleteStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.UpdateStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetDetailStadiumResponseDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetStadiumListByUserResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "구장 관리 API", description = "구장 관련 API endpoints")
@RequestMapping("/api/host/stadium")
public class StadiumController {

	private final StadiumService stadiumService;

	@Operation(summary = "구장 상세 조회", description = "구장 상세 조회 API")
	@GetMapping("/detail")
	public BaseResponse<GetDetailStadiumResponseDto> getDetailStadium(@RequestParam String stadiumUuid) {

		return new BaseResponse<>(
			stadiumService.getDetailStadium(stadiumUuid)
			);
	}

	@Operation(summary = "구장 생성", description = "구장 생성 API")
	@PostMapping
	public BaseResponse<Void> assignStadium(@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody AssignStadiumRequestDto assignStadiumRequestDto) {

		stadiumService.assignStadium(userDetails.getUsername(), assignStadiumRequestDto);

		return new BaseResponse<>();
	}

	@Operation(summary = "구장 수정", description = "구장 수정 API")
	@PutMapping
	public BaseResponse<Void> updateStadium(@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody UpdateStadiumRequestDto updateStadiumRequestDto) {

		System.out.println(updateStadiumRequestDto);

		stadiumService.updateStadium(userDetails.getUsername(), updateStadiumRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장 삭제", description = "구장 삭제 API")
	@DeleteMapping
	public BaseResponse<Void> deleteStadium(@RequestBody DeleteStadiumRequestDto deleteStadiumRequestDto) {

		stadiumService.deleteStadium(deleteStadiumRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "구장주의 구장 목록 조회", description = "구장주의 구장 목록 조회 API")
	@GetMapping("/list")
	public BaseResponse<List<GetStadiumListByUserResponse>> getStadiumListByHost(@AuthenticationPrincipal UserDetails userDetails) {

		return new BaseResponse<>(
			stadiumService.getStadiumListByHost(userDetails.getUsername())
			);
	}
}
