package org.example.sharesportsvendorbackend.stadium.infrastructure;

import java.util.List;
import java.util.Optional;

import org.example.sharesportsvendorbackend.stadium.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {

	@Query("SELECT s FROM Stadium s WHERE s.stadiumUuid = :stadiumUuid")
	Optional<Stadium> findByUuid(String stadiumUuid);

	boolean existsByStadiumName(String StadiumName);

	List<Stadium> findByHostUuid(String hostUuid);

}
