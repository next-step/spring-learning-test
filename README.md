# 📖 Spring Auth 학습 테스트
- Spring Auth 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- sessionLogin: session 기반의 로그인 학습 테스트
- tokenLogin: token 기반의 로그인 학습 테스트

> `AuthController` 수정하여 `AuthControllerTest` 테스트 성공 시키기

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout auth`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드의 주석을 참고하여 프로덕션 코드를 구현하기
- 테스트는 [auth](https://github.com/next-step/spring-learning-test/tree/auth/src/test/java/nextstep/helloworld/auth) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/core/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [auth-sample](https://github.com/next-step/spring-learning-test/tree/auth-sample)를 참고해서 진행해도 좋음 `git checkout auth-sample`

## 학습 테스트 활용 방법

---
### 1. 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 프로덕션 코드에 추가할 내용을 인지하기

```java
@Test
void sessionLogin() {
    MemberResponse member = RestAssured
        .given().log().all()
        .auth().form(EMAIL, PASSWORD, new FormAuthConfig("/login/session", USERNAME_FIELD, PASSWORD_FIELD))
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when().get("/members/me")
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract().as(MemberResponse.class);
    
    assertThat(member.getEmail()).isEqualTo(EMAIL);
}
```  


---

### 2. 로그 확인하기
- request 참고하여 `AuthController`에서 요청을 처리 할 메서드를 확인

request
```http
POST /login/session HTTP/1.1
accept: */*
content-type: application/x-www-form-urlencoded; charset=ISO-8859-1
content-length: 37
host: localhost:62888
connection: Keep-Alive
user-agent: Apache-HttpClient/4.5.11 (Java/1.8.0_252)
accept-encoding: gzip,deflate
```

response
```http
HTTP/1.1 401 Unauthorized
Transfer-Encoding: chunked
Keep-Alive: timeout=60
Connection: keep-alive
Date: Sun, 17 Jan 2021 14:00:41 GMT
Content-Type: application/json
```

---

### 3. 프로덕션 코드에 기능 구현하기
- 주석을 확인학여 동작하는 코드를 구현

```java
/**
 * ex) request sample
 * <p>
 * POST /login/session HTTP/1.1
 * content-type: application/x-www-form-urlencoded; charset=ISO-8859-1
 * host: localhost:55477
 * <p>
 * email=email@email.com&password=1234
 */
@PostMapping("/login/session")
public ResponseEntity sessionLogin() {
    // TODO: email과 password 값 추출하기
    String email = "";
    String password = "";

    if (authService.checkInvalidLogin(email, password)) {
    throw new AuthorizationException();
    }

    // TODO: Session에 인증 정보 저장 (key: SESSION_KEY, value: email값)

    return ResponseEntity.ok().build();
}
```

---

### 4. 테스트 다시 수행

- 기능이 정상적으로 동작하는지 검증

---

## 참고 레퍼런스 모음

- [HttpSession](https://www.baeldung.com/spring-security-session#2-injecting-the-raw-session-into-a-controller)
- [`@RequestParam` or `HttpServletRequest`](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments)