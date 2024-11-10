package org.example.sharesportsvendorbackend.stadium.dto.out;

import org.example.sharesportsvendorbackend.stadium.domain.Stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDetailStadiumResponseDto {

	private String stadiumUuid;
	private String hostUuid;
	private String name;
	private String address;
	private String phone;
	private String description;
	private boolean parking;
	private boolean shoeRent;
	private boolean ballRent;
	private boolean uniformRent;
	private int rentCost;
	private String openingHours;
	private String closingHours;

	public static GetDetailStadiumResponseDto toDto(Stadium stadium) {
		return GetDetailStadiumResponseDto.builder()
				.stadiumUuid(stadium.getStadiumUuid())
				.hostUuid(stadium.getHostUuid())
				.name(stadium.getStadiumName())
				.address(stadium.getAddress())
				.phone(stadium.getPhone())
				.description(stadium.getDescription())
				.parking(stadium.isParking())
				.shoeRent(stadium.isShoeRent())
				.ballRent(stadium.isBallRent())
				.uniformRent(stadium.isUniformRent())
				.rentCost(stadium.getRentCost())
				.openingHours(stadium.getOpeningHours())
				.closingHours(stadium.getClosingHours())
				.build();
	}
}
