package org.example.sharesportsvendorbackend.stadium.dto.in;

import org.example.sharesportsvendorbackend.stadium.domain.Stadium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AssignStadiumRequestDto {

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


	public Stadium createEntity() {
		return Stadium.builder()
				.hostUuid(hostUuid)
				.name(name)
				.address(address)
				.phone(phone)
				.description(description)
				.parking(parking)
				.shoeRent(shoeRent)
				.ballRent(ballRent)
				.uniformRent(uniformRent)
				.rentCost(rentCost)
				.openingHours(openingHours)
				.closingHours(closingHours)
				.deleted(false)
				.build();
	}

}
