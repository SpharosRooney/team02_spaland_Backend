package spaland.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /*
    400 BAD_REQUEST : 잘못된 요청
    401 UNAUTHORIZED : 인증되지 않은 사용자
    403 FORBIDDEN : 접근할 권한이 없음, 반대로 생각하면 권한이 필요하다라는 의미로, 이 정보 조차 제공하지 않기 위해 404를 많이 씀.
    404 NOT_FOUND : Resource 를 찾을 수 없음
    409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재
    */

    /* email    */
    DIFFERENT_CONFIRM_KEY(NOT_FOUND,"인증번호가 일치하지 않습니다."),
    NULL_MAIL_ADMIN(NOT_FOUND, "인증번호 관리자가 등록되지 않았습니다."),
    DUPLICATE_EMAIL(CONFLICT, "이미 가입된 메일입니다"),
    DUPLICATE_EMAIL_2(CONFLICT, "이미 가입된 메일입니다"),

    /* users    */
    INVALID_MEMBER_INFO(NOT_FOUND,"유저 정보가 일치하지 않습니다."),
    INVALID_MEMBER(NOT_FOUND,"등록되지 않은 유저입니다."),
    INVALID_MEMBER_USER(NOT_FOUND,"유저 정보를 찾을 수 없습니다."),

    /* shipping */
    INVALID_MEMBER_SHIPPING(NOT_FOUND,"배송지 정보를 찾을 수 없습니다."),

    /* products */
    INVALID_PRODUCT(NOT_FOUND,"상품 정보를 찾을 수 없습니다."),
    INVALID_CATEGORY(NOT_FOUND,"카테고리 정보를 찾을 수 없습니다."),
    INVALID_EVENT(NOT_FOUND,"기획전 정보를 찾을 수 없습니다."),
    INVALID_IMAGE_LIST(NOT_FOUND,"이미지 정보를 찾을 수 없습니다."),
    INVALID_IMAGE(NOT_FOUND,"이미지 정보를 찾을 수 없습니다."),
    INVALID_OPTION(NOT_FOUND,"옵션 정보를 찾을 수 없습니다."),
    INVALID_SEASON(NOT_FOUND,"시즌 정보를 찾을 수 없습니다."),


    /* history  */
    INVALID_MEMBER_HISTORY(NOT_FOUND,"주문내역이 없습니다."),

    /* auth     */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    INVALID_ACCESS(NOT_FOUND,"유효하지 않은 접근입니다."),


    /* cart     */
    INVALID_MEMBER_CART(NOT_FOUND,"상품 정보를 찾을 수 없습니다."),

    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),


    ;

    private final HttpStatus httpStatus;
    private final String detail;

}
