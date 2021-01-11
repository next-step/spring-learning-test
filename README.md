# Spring MVC Config 학습 테스트

---

## 요약
### `WebMvcConfiguration` 수정하여 `WebMvcConfigurationTest` 테스트 성공 시키기
- addViewControllers: "/" 요청 시 hello.html 페이지 응답하기
- addInterceptors: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
- addArgumentResolvers: AuthenticationPrincipalArgumentResolver 등록하기

---
### `View Controller`
> https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-controller

### `Handler Interceptor`
> https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors

### `Handler Method Argument Resolver`
> https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver