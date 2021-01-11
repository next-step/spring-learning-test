package nextstep.helloworld.mvcconfig.config;

import nextstep.helloworld.mvcconfig.ui.AuthenticationPrincipalArgumentResolver;
import nextstep.helloworld.mvcconfig.ui.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-controller
     * <p>
     * "/" 요청 시 hello.html 페이지 응답하기
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
    }

    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors
     * <p>
     * "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**");
    }

    /**
     * https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver
     * <p>
     * AuthenticationPrincipalArgumentResolver 등록하기
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
    }
}
