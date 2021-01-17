package nextstep.helloworld.auth;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMvcConfigurationTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * 사용자가 "/"로 요청을 보냈을 때 hello.html이 응답되어야 함
     * <p>
     * WebMvcConfiguration의 addViewControllers 메서드로 설정하기
     */
    @Test
    void addViewControllers() {
        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .when().get("/")
                .then().log().all().extract();

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * 비인가 사용자가 회원 목록 조회를 했을 때 권한이 없다는 응답이 나와야 함
     * 현재는 모든 사용자가 접근이 가능하지만 LoginInterceptor를 통해 인증 과정을 거치도록 하기
     * <p>
     * 디버깅 해보기!
     * <p>
     * WebMvcConfiguration의 addInterceptors 메서드로 설정하기
     */
    @Test
    void addInterceptors() {
        RestAssured
                .given().log().all()
                .when().get("/admin/members")
                .then().log().all()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract();
    }

    /**
     * MvcConfigController에서 @AuthenticationPrincipal LoginMember loginMember 파라미터에 값을 셋팅할 수 있게 설정하기
     * AuthenticationPrincipalArgumentResolver를 활용하기
     * <p>
     * 디버깅 해보기!
     * <p>
     * WebMvcConfiguration의 addInterceptors 메서드로 설정하기
     */
    @Test
    void addArgumentResolvers() {
        RestAssured
                .given().log().all()
                .when().get("/favorites")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }
}