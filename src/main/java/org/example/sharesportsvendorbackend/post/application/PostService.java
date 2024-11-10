package org.example.sharesportsvendorbackend.post.application;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponseStatus;
import org.example.sharesportsvendorbackend.global.error.BaseException;
import org.example.sharesportsvendorbackend.host.domain.Host;
import org.example.sharesportsvendorbackend.host.infrastructure.HostRepository;
import org.example.sharesportsvendorbackend.post.domain.Post;
import org.example.sharesportsvendorbackend.post.dto.in.AddPostRequestDto;
import org.example.sharesportsvendorbackend.post.dto.in.UpdatePostRequestDto;
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
	private PostRepository postRepository;

	public void addPost(String authorUuid, AddPostRequestDto addPostRequestDto) {

		Host host = hostRepository.findByHostUuid(authorUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_HOST));

		postRepository.save(addPostRequestDto.createEntity(host));
	}

	public void updatePost(String authorUuid, UpdatePostRequestDto updatePostRequestDto) {

		Host host = hostRepository.findByHostUuid(authorUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_HOST));

		Long postId = postRepository.findByPostUuid(updatePostRequestDto.getPostUuid())
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA)).getPostId();

		postRepository.save(updatePostRequestDto.updateEntity(postId, host));
	}

	public void deletePost(String postUuid) {

		Post post = postRepository.findByPostUuid(postUuid)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_DATA));

		postRepository.save(Post.builder()
				.postId(post.getPostId())
				.postUuid(post.getPostUuid())
				.title(post.getTitle())
				.content(post.getContent())
				.deleted(true)
				.authorName(post.getAuthorName())
				.authorUuid(post.getAuthorUuid())
				.build());
	}

	@Transactional(readOnly = true)
	public List<GetPostListResponseDto> getPostList(String stadiumUuid) {
		return postRepository.findByStadiumUuid(stadiumUuid).stream()
			.filter(post -> !post.isDeleted())
				.map(post -> GetPostListResponseDto.builder()
							.postUuid(post.getPostUuid())
							.title(post.getTitle())
							.content(post.getContent())
							.authorName(post.getAuthorName())
							.authorUuid(post.getAuthorUuid())
							.build()
				)
				.toList();
	}

}
