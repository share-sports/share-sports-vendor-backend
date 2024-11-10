package org.example.sharesportsvendorbackend.stadium.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StadiumImageDto {
	private String imageSrc;
	private boolean thumbnail;
}
