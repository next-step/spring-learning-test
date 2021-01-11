# Spring Auth 학습 테스트

---

## 요약
### `AuthController` 수정하여 `AuthControllerTest` 테스트 성공 시키기
- sessionLogin: session 기반의 로그인 학습 테스트 
- tokenLogin: token 기반의 로그인 학습 테스트

---
## Session Login

### `HttpSession`
> https://www.baeldung.com/spring-security-session#2-injecting-the-raw-session-into-a-controller

### `@RequestParam`이나 `HttpServletRequest` 활용하기
> https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments