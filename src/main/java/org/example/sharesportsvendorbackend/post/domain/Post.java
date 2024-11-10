package org.example.sharesportsvendorbackend.post.domain;

import org.example.sharesportsvendorbackend.global.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Column(nullable = false, unique = true)
	private String postUuid;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 1000)
	private String content;

	@Column(nullable = false)
	private boolean deleted;

	@Column(nullable = false)
	private String authorName;

	@Column(nullable = false)
	private String authorUuid;

	@Column(nullable = false)
	private String stadiumUuid;

	@Column(nullable = false)
	private PostType postType;
}
