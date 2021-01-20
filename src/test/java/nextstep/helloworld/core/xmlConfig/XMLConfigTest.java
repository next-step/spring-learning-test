package nextstep.helloworld.core.xmlConfig;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * application-config.xml로 등록한 빈을 검증하는 학습 테스트
 * 추가로 작성할 코드는 없습니다.
 */
class XMLConfigTest {
    @Test
    void main() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        System.out.println(Arrays.toString(beanDefinitionNames));

        UserService service = context.getBean("userService", UserService.class);

        System.out.println(service.sayHello());

        System.out.println(service.sayHi());
    }

}