package nextstep.helloworld.mvc.mapping;

import io.restassured.RestAssured;
import nextstep.helloworld.mvc.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MediaTypeTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * MediaTypeController > createUser 메서드
     * > consumes 값으로 APPLICATION_JSON_VALUE 받기
     */
    @DisplayName("Media Type - Content-Type")
    @Test
    void createUser() {
        User user = new User("이름", "email@email.com");

        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(user)
                .when().post("/media-type/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", "/users/1");
    }

    /**
     * MediaTypeController > showUser 메서드
     * MediaTypeController > userPage 메서드
     *
     * > produces 값으로 요청 구분하기
     * > APPLICATION_JSON_VALUE와 TEXT_HTML_VALUE 각각 처리하기
     */
    @DisplayName("Media Type - Accept")
    @Test
    void showUser() {
        RestAssured.given().log().all()
                .accept(MediaType.TEXT_HTML_VALUE)
                .when().get("/media-type/users")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.TEXT_HTML_VALUE)
                .body(containsString("user page"));

        RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/media-type/users")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("size()", is(2));
    }
}
