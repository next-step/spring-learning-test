package nextstep.helloworld.core.javaConfig;

import nextstep.helloworld.HelloApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

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
    void javaConfig() {
        assertThat(authenticationPrincipalArgumentResolver.findMemberName()).isEqualTo("사용자");
    }

    @Test
    void useSpringBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        AuthService authService = context.getBean(AuthService.class);
        AuthenticationPrincipalArgumentResolver resolver = context.getBean(AuthenticationPrincipalArgumentResolver.class);
        assertThat(resolver.getAuthService()).isEqualTo(authService);
    }
}