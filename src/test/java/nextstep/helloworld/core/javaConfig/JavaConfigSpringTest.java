package nextstep.helloworld.core.javaConfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HelloApplication에 설정된 @SpringBootApplication을 통해 애플리케이션의 모든 빈들을 이용한 테스트
 * <p>
 * AuthenticationPrincipalConfig을 이용하여
 * AuthenticationPrincipalArgumentResolver를 빈 등록하고
 * AuthService에 대한 의존성을 주입하기
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JavaConfigSpringTest {
    @Autowired
    private AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver;

    @Test
    void main() {
        assertThat(authenticationPrincipalArgumentResolver.findMemberName()).isEqualTo("사용자");
    }
}