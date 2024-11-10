package org.example.sharesportsvendorbackend.global.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

	// 200: 요청 성공
	SUCCESS(HttpStatus.OK, true, "요청에 성공하였습니다."),

	// 400: Bad Request
	BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
	INVALID_FILE_FORMAT(HttpStatus.BAD_REQUEST, false, "잘못된 파일 형식입니다."),
	FILE_IS_EMPTY(HttpStatus.BAD_REQUEST, false, "파일이 비어있습니다."),
	FAILED_TO_LOGIN(HttpStatus.BAD_REQUEST, false, "로그인에 실패하였습니다."),
	WRONG_JWT_TOKEN(HttpStatus.BAD_REQUEST, false, "잘못된 JWT 토큰입니다."),

	/*
	 * 404 NOT_FOUND: 리소스를 찾을 수 없음
	 */
	DUPLICATE_CATEGORY_NAME(HttpStatus.CONFLICT, false, "중복된 카테고리 이름입니다."),
	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, false, "카테고리 데이터를 찾지 못했습니다."),
	INVALID_CSV_FORMAT(HttpStatus.BAD_REQUEST, false, "잘못된 CSV 파일 형식입니다."),
	NOT_FOUND_DATA(HttpStatus.NOT_FOUND, false, "데이터를 찾을 수 없습니다."),
	AUTH_CODE_INVALID(HttpStatus.NOT_FOUND, false, "인증 코드가 유효하지 않습니다."),
	/*
	 * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
	 */
	METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메서드입니다."),

	/*
	 * 409 CONFLICT: 리소스의 현재 상태와 충돌
	 */
	DUPLICATED_DATA(HttpStatus.CONFLICT, false, "중복된 데이터입니다."),
	DUPLICATED_HOST(HttpStatus.CONFLICT, false, "중복된 계정이 존재합니다."),

	/*
	 * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "내부 서버 오류입니다."),
	FILE_PROCESSING_ERROR(HttpStatus.PROCESSING, false, "파일 처리 중 오류가 발생했습니다."),
	;

	private final HttpStatusCode httpStatusCode;
	private final boolean isSuccess;
	private final String message;
}