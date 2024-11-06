package org.example.sharesportsvendorbackend.host.infrastructure;

import java.util.Optional;

import org.example.sharesportsvendorbackend.host.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<Host> findByHostUuid(String hostUuid);
    @Query("SELECT m.email FROM Host m WHERE m.hostUuid = :hostUuid")
    String findEmailByHostUuid(@Param("hostUuid") String hostUuid);
}
