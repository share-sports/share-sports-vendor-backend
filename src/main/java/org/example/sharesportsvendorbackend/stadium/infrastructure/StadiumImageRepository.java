package org.example.sharesportsvendorbackend.stadium.infrastructure;

import java.util.List;

import org.example.sharesportsvendorbackend.stadium.domain.StadiumImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadiumImageRepository extends JpaRepository<StadiumImage, Long> {

	List<StadiumImage> findByStadiumUuid(String stadiumUuid);

	void deleteByStadiumUuid(String stadiumUuid);
}
