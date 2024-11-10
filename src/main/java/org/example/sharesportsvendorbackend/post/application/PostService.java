package org.example.sharesportsvendorbackend.post.application;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.example.sharesportsvendorbackend.post.domain.Post;
import org.example.sharesportsvendorbackend.post.domain.PostType;
import org.example.sharesportsvendorbackend.post.dto.in.AddPostRequestDto;
import org.example.sharesportsvendorbackend.post.dto.in.UpdatePostRequestDto;
import org.example.sharesportsvendorbackend.post.dto.out.GetPostDetailResponseDto;
import org.example.sharesportsvendorbackend.post.dto.out.GetPostListResponseDto;
import org.example.sharesportsvendorbackend.post.infrastructure.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final HostRepository hostRepository;
	private final PostRepository postRepository;

	public void addPost(String authorUuid, AddPostRequestDto addPostRequestDto) {

		Host host = hostRepository.findByHostUuid(authorUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_HOST));

		postRepository.save(addPostRequestDto.createEntity(host));
	}

	public void updatePost(String authorUuid, UpdatePostRequestDto updatePostRequestDto) {

		Host host = hostRepository.findByHostUuid(authorUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_HOST));

		Post post = postRepository.findByPostUuid(updatePostRequestDto.getPostUuid())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		postRepository.save(updatePostRequestDto.updateEntity(post, host));
	}

	public void deletePost(String postUuid) {

		Post post = postRepository.findByPostUuid(postUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		postRepository.save(Post.builder()
				.postId(post.getPostId())
				.postUuid(post.getPostUuid())
				.title(post.getTitle())
				.content(post.getContent())
				.stadiumUuid(post.getStadiumUuid())
				.deleted(true)
				.authorName(post.getAuthorName())
				.authorUuid(post.getAuthorUuid())
				.postType(post.getPostType())
				.build());
	}

	@Transactional(readOnly = true)
	public List<GetPostListResponseDto> getPostList(String stadiumUuid, PostType postType) {
		return postRepository.findByStadiumUuid(stadiumUuid).stream()
			.filter(post -> !post.isDeleted())
			.filter(post -> post.getPostType().equals(postType))
				.map(post -> GetPostListResponseDto.builder()
							.postUuid(post.getPostUuid())
							.title(post.getTitle())
							.authorName(post.getAuthorName())
							.authorUuid(post.getAuthorUuid())
							.createdDate(post.getCreatedDate())
							.updatedDate(post.getLastModifiedDate())
							.postType(postType)
							.build()
				)
				.toList();
	}

	@Transactional(readOnly = true)
	public GetPostDetailResponseDto getPostDetail(String postUuid) {

		Post post = postRepository.findByPostUuid(postUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		return GetPostDetailResponseDto.builder()
				.postUuid(post.getPostUuid())
				.title(post.getTitle())
				.content(post.getContent())
				.authorName(post.getAuthorName())
				.authorUuid(post.getAuthorUuid())
				.createdDate(post.getCreatedDate())
				.updatedDate(post.getLastModifiedDate())
				.postType(post.getPostType())
				.build();

	}

}
