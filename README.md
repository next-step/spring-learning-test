# 📖 Spring MVC Config 학습 테스트
- Spring MVC Config 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- addViewControllers: "/" 요청 시 hello.html 페이지 응답하기
- addInterceptors: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
- addArgumentResolvers: AuthenticationPrincipalArgumentResolver 등록하기

> `WebMvcConfiguration` 수정하여 `WebMvcConfigurationTest` 테스트 성공 시키기

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout mvc-config`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드의 주석을 참고하여 프로덕션 코드를 구현하기
- 테스트는 [mvc-config](https://github.com/next-step/spring-learning-test/tree/core/src/test/java/nextstep/helloworld/mvc-config) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/core/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [mvc-config-sample](https://github.com/next-step/spring-learning-test/tree/mvc-config-sample)를 참고해서 진행해도 좋음 `git checkout mvc-config-sample`

## 학습 테스트 활용 방법

---
### 1. 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 프로덕션 코드에 추가할 내용을 인지하기

```java
/**
 * 비인가 사용자가 회원 목록 조회를 했을 때 권한이 없다는 응답이 나와야 함
 * 현재는 모든 사용자가 접근이 가능하지만 LoginInterceptor를 통해 인증 과정을 거치도록 하기
 * <p>
 * 디버깅 해보기!
 * <p>
 * WebMvcConfiguration의 addInterceptors 메서드로 설정하기
 */
@Test
    void addInterceptors() {
            RestAssured
            .given().log().all()
            .when().get("/admin/members")
            .then().log().all()
            .statusCode(HttpStatus.UNAUTHORIZED.value())
            .extract();
            }
```  

---

### 2. 프로덕션 코드에 기능 구현하기

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
    .addPathPatterns("/admin/**");
}
```

---

### 3. 테스트 다시 수행

- 기능이 정상적으로 동작하는지 검증

### 4. 디버깅

- 필요한 경우 디버깅을 진행
<p align="center">
  <img src="https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/98975a2831a340f8b2fa5dd06d32bf35" width="70%">
</p>

<p align="center">
  <img src="https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/bbe245ae067f4723ae23f09fa551bc8c" width="70%">
</p>

- 추가적으로 학습을 위해 인자 값을 확인해도 좋음

<p align="center">
  <img src="https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/443709cfc2a145eeaad92f63e33440ae" width="70%">
</p>

---

## 참고 레퍼런스 모음
- [View Controller](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-controller)
- [Handler Interceptor](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-interceptors)
- [Handler Method Argument Resolver](https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver)