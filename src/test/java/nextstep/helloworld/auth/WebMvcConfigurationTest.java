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
     * 관리자 -> 회원 목록 조회 시나리오
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
     * 사용자 -> 자신의 즐겨찾기 조회
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