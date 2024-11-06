package org.example.sharesportsvendorbackend.stadium.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDetailStadiumRequestDto {

	private String stadiumUuid;
}
