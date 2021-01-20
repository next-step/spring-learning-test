package nextstep.helloworld.core.javaConfig;

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
class JavaConfigTest {
    @Test
    void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        AuthService authService = context.getBean(AuthService.class);
        assertThat(authService).isNotNull();
    }
}