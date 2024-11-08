package org.example.sharesportsvendorbackend.stadium.application;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.stadium.domain.Stadium;
import org.example.sharesportsvendorbackend.stadium.dto.in.AssignStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.DeleteStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.GetDetailStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.UpdateStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetDetailStadiumResponseDto;
import org.example.sharesportsvendorbackend.stadium.infrastructure.StadiumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StadiumService {

	private final StadiumRepository stadiumRepository;

	public void assignStadium(AssignStadiumRequestDto assignStadiumRequestDto) {

		if (stadiumRepository.existsByName(assignStadiumRequestDto.getName())) {
			throw new BaseException(BaseResponseStatus.DUPLICATED_DATA);
		}
		stadiumRepository.save(assignStadiumRequestDto.createEntity());
	}

	public void updateStadium(String hostUuid, UpdateStadiumRequestDto updateStadiumRequestDto) {

		Long stadiumId = stadiumRepository.findByUuid(updateStadiumRequestDto.getStadiumUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA)).getStadiumId();

		stadiumRepository.save(updateStadiumRequestDto.updateEntity(hostUuid, stadiumId));
	}


	public void deleteStadium(DeleteStadiumRequestDto deleteStadiumRequestDto) {

		Stadium stadium = stadiumRepository.findByUuid(deleteStadiumRequestDto.getStadiumUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		stadiumRepository.delete(deleteStadiumRequestDto.deleteEntity(stadium));
	}

	@Transactional(readOnly = true)
	public GetDetailStadiumResponseDto getDetailStadium(GetDetailStadiumRequestDto getDetailStadiumRequestDto) {

		Stadium stadium = stadiumRepository.findByUuid(getDetailStadiumRequestDto.getStadiumUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		return GetDetailStadiumResponseDto.toDto(stadium);
	}
}
