package nextstep.helloworld.mvc.handler;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;

/**
 * ReturnValueController의 주석을 풀고 진행해주세요.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReturnValueTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * ReturnValueController > string 메서드
     */
    @DisplayName("Return Value - ResponseBody String")
    @Test
    void string() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/return-value/message")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("message"));
    }

    /**
     * ReturnValueController > responseBodyForUser 메서드
     */
    @DisplayName("Return Value - ResponseBody User")
    @Test
    void responseBodyForUser() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/return-value/users")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", is("name"))
                .body("email", is("email"));
    }

    /**
     * ReturnValueController > responseEntity 메서드
     */
    @DisplayName("Return Value - ResponseEntity")
    @Test
    void responseEntity() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/return-value/users/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", is("name"))
                .body("email", is("email"));
    }

    /**
     * ReturnValueController > responseEntityFor400 메서드
     */
    @DisplayName("Return Value - ResponseEntity")
    @Test
    void responseEntityFor400() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/return-value/members")
                .then().log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
