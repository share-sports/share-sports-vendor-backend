package org.example.sharesportsvendorbackend.stadium.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetStadiumListByUserResponse {

	private String stadiumUuid;
	private String name;
	private String address;

}
