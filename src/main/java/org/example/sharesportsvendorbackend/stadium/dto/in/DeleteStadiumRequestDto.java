package org.example.sharesportsvendorbackend.stadium.dto.in;

import org.example.sharesportsvendorbackend.stadium.domain.Stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeleteStadiumRequestDto {

	private String stadiumUuid;

	public Stadium deleteEntity(Stadium stadium) {
		return Stadium.builder()
				.stadiumId(stadium.getStadiumId())
				.hostUuid(stadium.getHostUuid())
				.stadiumName(stadium.getStadiumName())
				.address(stadium.getAddress())
				.phone(stadium.getPhone())
				.description(stadium.getDescription())
				.parking(stadium.isParking())
				.shoeRent(stadium.isShoeRent())
				.ballRent(stadium.isBallRent())
				.uniformRent(stadium.isUniformRent())
				.rentCost(stadium.getRentCost())
				.openingHours(stadium.getOpeningHours())
				.deleted(false)
				.build();
	}
}
