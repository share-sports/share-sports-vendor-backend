package org.example.sharesportsvendorbackend.stadium.application;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.example.sharesportsvendorbackend.stadium.domain.Stadium;
import org.example.sharesportsvendorbackend.stadium.dto.in.AssignStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.DeleteStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.in.UpdateStadiumRequestDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetDetailStadiumResponseDto;
import org.example.sharesportsvendorbackend.stadium.dto.out.GetStadiumListByUserResponse;
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
	private final HostRepository hostRepository;

	public void assignStadium(String hostUuid, AssignStadiumRequestDto assignStadiumRequestDto) {

		if (stadiumRepository.existsByStadiumName(assignStadiumRequestDto.getStadiumName())) {
			throw new BaseException(BaseResponseStatus.DUPLICATED_DATA);
		}
		stadiumRepository.save(assignStadiumRequestDto.createEntity(hostUuid));
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
	public GetDetailStadiumResponseDto getDetailStadium(String stadiumUuid) {

		Stadium stadium = stadiumRepository.findByUuid(stadiumUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		return GetDetailStadiumResponseDto.toDto(stadium);
	}

	@Transactional(readOnly = true)
	public List<GetStadiumListByUserResponse> getStadiumListByHost(String hostUuid) {

		return stadiumRepository.findByHostUuid(hostUuid)
			.stream()
			.map(stadium -> GetStadiumListByUserResponse.builder()
				.stadiumUuid(stadium.getStadiumUuid())
				.name(stadium.getStadiumName())
				.address(stadium.getAddress())
				.build())
			.toList();
	}
}
