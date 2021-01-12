# 📖 Spring JDBC 학습 테스트

- Spring JDBC 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- jdbctemplate: JdbcTemplated와 NamedParameterJdbcTemplate을 활용하여 쿼리 실행 학습 테스트
- simpleinsert: SimpleJdbcInsert을 활용하여 insert 기능 학습 테스트

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout jdbc`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드에서 호출하는 프로덕션 코드를 구현하기
- 프로덕션 코드의 주석에 기재된 메서드 시그니쳐와 클래스이름 힌트를 참고하기 
- 테스트는 [jdbc](https://github.com/next-step/spring-learning-test/tree/jdbc/src/test/java/nextstep/helloworld/jdbc) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/jdbc/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [jdbc-sample](https://github.com/next-step/spring-learning-test/tree/jdbc-sample)를 참고해서 진행해도 좋음 `git checkout mvc-sample`

## 학습 테스트 활용 방법
---
### 1. 테스트에서 호출하는 프로덕션 코드 확인
```java
@Test
void count() {
    int count = queryingDAO.count();

    assertThat(count).isEqualTo(4);
}
```

---
### 2. 메서드 주석 확인하고 기능 구현
- `QueryingDAO`는 `JdbcTemplate`객체를 통해 DB에 접근
- `JdbcTemplate`의 활용법을 찾아본 후 코드 적용

```java
/**
 * public <T> T queryForObject(String sql, Class<T> requiredType)
 */
public int count() {
    String sql = "select count(*) from customers";
    return jdbcTemplate.queryForObject(sql, Integer.class);
}
```

---
### 3. 테스트 다시 수행
- 기능이 정상적으로 동작하는지 검증
<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/e5a097dc9556407a850578a5a62b9b86" width="70%">
</p>

---
## 참고 레퍼런스 모음

- [JdbcTemplate - Querying](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-query)
- [JdbcTemplate - Updating](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-JdbcTemplate-examples-update)
- [NamedParameterJdbcTemplate](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-NamedParameterJdbcTemplate)
- [SimpleJdbcInsert](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#jdbc-simple-jdbc-insert-1)
