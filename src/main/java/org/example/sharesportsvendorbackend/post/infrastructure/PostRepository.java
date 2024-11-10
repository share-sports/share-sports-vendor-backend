package org.example.sharesportsvendorbackend.post.infrastructure;

import java.util.List;
import java.util.Optional;

import org.example.sharesportsvendorbackend.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByPostUuid(String postUuid);

	List<Post> findByStadiumUuid(String stadiumUuid);
}
