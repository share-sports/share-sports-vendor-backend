package org.example.sharesportsvendorbackend.stadium.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StadiumImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long stadiumImageId;

	@Column(nullable = false)
	private String imageSrc;

	@Column(nullable = false)
	private boolean thumbnail;

	@Column(nullable = false)
	private String stadiumUuid;
}
