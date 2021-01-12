# 📖 Spring Core 학습 테스트
- Spring Core 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- scan: component scan을 통해 스프링 빈 등록 학습 테스트
- di: 스프링 빈들의 의존성 주입 학습 테스트

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout core`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드의 주석을 참고하여 프로덕션 코드를 구현하기
- 테스트는 [core](https://github.com/next-step/spring-learning-test/tree/core/src/test/java/nextstep/helloworld/core) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/core/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [core-sample](https://github.com/next-step/spring-learning-test/tree/core-sample)를 참고해서 진행해도 좋음 `git checkout core-sample`

## 학습 테스트 활용 방법

---
### 1. 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 프로덕션 코드에 추가할 내용을 인지하기

```java
/**
 * TODO: core > scan 패키지 내에 있는 클래스를 스프링 빈으로 등록하기
 */
@Test
void componentScan() {
    ApplicationContext context = getApplicationContext();
    LineDao dao = context.getBean("lineDao", LineDao.class);
    assertThat(dao).isNotNull();

    LineService service = context.getBean("lineService", LineService.class);
    assertThat(service).isNotNull();
}
```  


---

### 2. 프로덕션 코드에 기능 구현하기

```java
package nextstep.helloworld.core.scan;

import org.springframework.stereotype.Service;

@Service
public class LineService {
}
```

---

### 3. 테스트 다시 수행

- 기능이 정상적으로 동작하는지 검증
<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/83828cfd9aa649eab2b1f5933a8c39d8" width="70%">
</p>

---

## 참고 레퍼런스 모음

### scan
- [@Component](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-stereotype-annotations)

### di
- [Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-dependencies)
- [Constructor Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-constructor-injection)
- [Setter Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-setter-injection)
- [Field Injection](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-autowired-annotation)