package org.example.sharesportsvendorbackend.stadium.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StadiumImageRequestDto {
	private String stadiumUuid;
	private String imageSrc;
	private boolean thumbnail;
}