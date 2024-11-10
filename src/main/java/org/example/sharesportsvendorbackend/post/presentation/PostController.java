package org.example.sharesportsvendorbackend.post.presentation;

import java.util.List;

import org.example.sharesportsvendorbackend.global.common.response.BaseResponse;
import org.example.sharesportsvendorbackend.post.application.PostService;
import org.example.sharesportsvendorbackend.post.dto.in.AddPostRequestDto;
import org.example.sharesportsvendorbackend.post.dto.in.UpdatePostRequestDto;
import org.example.sharesportsvendorbackend.post.dto.out.GetPostListResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/host/post")
@Tag(name = "게시글 관리 API", description = "게시글 관리 API endpoints")
public class PostController {

	private final PostService postService;

	@Operation(summary = "게시글 생성", description = "게시글 생성 API")
	@PostMapping
	public BaseResponse<Void> addPost(@AuthenticationPrincipal UserDetails userDetails,
		AddPostRequestDto addPostRequestDto) {

		postService.addPost(userDetails.getUsername(), addPostRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "게시글 수정", description = "게시글 수정 API")
	@PutMapping
	public BaseResponse<Void> updatePost(@AuthenticationPrincipal UserDetails userDetails,
		UpdatePostRequestDto updatePostRequestDto) {

		postService.updatePost(userDetails.getUsername(), updatePostRequestDto);
		return new BaseResponse<>();
	}

	@Operation(summary = "게시글 삭제", description = "게시글 삭제 API")
	@DeleteMapping("/{postUuid}")
	public BaseResponse<Void> deletePost(@PathVariable String postUuid) {

		postService.deletePost(postUuid);
		return new BaseResponse<>();
	}

	@GetMapping("/list/{stadiumUuid}")
	public BaseResponse<List<GetPostListResponseDto>> getPostList(@PathVariable String stadiumUuid) {

		return new BaseResponse<>(postService.getPostList(stadiumUuid));
	}
}
