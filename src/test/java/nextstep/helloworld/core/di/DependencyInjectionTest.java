package nextstep.helloworld.core.di;

import nextstep.helloworld.HelloApplication;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencyInjectionTest {
    /**
     * TODO: 생성자 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void constructorInjection() {
        ApplicationContext context = getApplicationContext();
        StationConstructorService service = context.getBean("stationConstructorService", StationConstructorService.class);
        assertThat(service.sayHi()).isEqualTo("Hi");
    }

    /**
     * TODO: 필드 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void autowiredInjection() {
        ApplicationContext context = getApplicationContext();
        StationFieldService service = context.getBean("stationFieldService", StationFieldService.class);
        assertThat(service.sayHi()).isEqualTo("Hi");
    }

    /**
     * TODO: setter 주입을 통해 StationConstructorService에 StationRepository 의존성을 주입하기
     */
    @Test
    void setterInjection() {
        ApplicationContext context = getApplicationContext();
        StationSetterService service = context.getBean("stationSetterService", StationSetterService.class);
        assertThat(service.sayHi()).isEqualTo("Hi");
    }

    /**
     * HelloApplication > @SpringBootApplication 설정을 통해 이미 ComponentScan 설정되어있음
     */
    private ApplicationContext getApplicationContext() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        return context;
    }
}
