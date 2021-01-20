package nextstep.helloworld.core.environment;

import nextstep.helloworld.HelloApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AnnotationConfigApplicationContext을 통해 AuthenticationPrincipalConfig에 설정된 빈들을 이용한 테스트
 * <p>
 * AuthenticationPrincipalConfig을 이용하여
 * AuthService 빈 등록하기
 */
class EnvironmentTest {
    @Test
    void environment() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        JwtTokenKeyProvider jwtTokenKeyProvider = context.getBean(JwtTokenKeyProvider.class);
        assertThat(jwtTokenKeyProvider.getSecretKey()).isEqualTo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.ih1aovtQShabQ7l0cINw4k1fagApg3qLWiB8Kt59Lno");
    }

    @Test
    void environment2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValueConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        JwtTokenExpireProvider jwtTokenExpireProvider = context.getBean(JwtTokenExpireProvider.class);
        assertThat(jwtTokenExpireProvider.getValidityInMilliseconds()).isEqualTo(3600000);
    }
}