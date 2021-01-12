package nextstep.helloworld.mvc.mapping;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UriPatternTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * UriPatternController > pathVariable 메서드
     */
    @DisplayName("Uri Pattern - @PathVariable")
    @Test
    void pathVariable() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/users/1")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    /**
     * UriPatternController > pattern 메서드
     * > 각 요청을 하나의 메서드로 처리하기
     */
    @DisplayName("Uri Pattern - pattern")
    @Test
    void pattern() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/a")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/b")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern"));
    }

    /**
     * UriPatternController > patternStars 메서드
     * > 각 요청을 하나의 메서드로 처리하기
     */
    @DisplayName("Uri Pattern - pattern multi")
    @Test
    void patternStars() {
        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/multi")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/all")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/uri-pattern/patterns/all/names")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body(is("pattern-multi"));
    }
}
