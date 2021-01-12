# 🚀 초간단 방문 횟수 조회 서비스

- 환영 인사와 몇 번째 방문인지를 알려주는 초간단 웹 애플리케이션

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/73eb46d282e5466b92224bf45e7b895f" width="70%">
</p>

## 기능 목록
- `http://localhost:8080/hello` 요청 시 `HelloWorld` 문구를 출력
- `http://localhost:8080/hello?name={user}` 요청 시 `Hello {user} n번째 방문입니다` 문구 출력

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout simple`
- [1. HelloApplication 실행하기](https://github.com/next-step/spring-learning-test/tree/simple#1-helloapplication-%EC%8B%A4%ED%96%89%ED%95%98%EA%B8%B0) 부터 가이드를 참고하여 진행
- 완성 브랜치 [simple-sample](https://github.com/next-step/spring-learning-test/tree/simple-sample)를 참고해서 진행해도 좋음 `git checkout simple-sample`

---
## 1. HelloApplication 실행하기
- 클론 받은 상태에서 서버를 바로 실행

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2c9ebdaef12b4d69b39d8ff549873160" width="70%">
</p>

- 브라우저에서 `http://localhost:8080` 요청 후 페이지 확인
- 아래와 같은 페이지가 뜨면 서버가 정상적으로 실행된 것

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A58%3A40.273image.png" width="70%">
</p>

---
## ❗️ 요청 / 응답 흐름도
- 클라이언트로 부터 받은 요청과 응답은 Spring MVC 모듈을 통해 처리
<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/e0ba787b5b204a029566ba11b5ea9042" width="70%">
</p>

---
## 2. /hello 요청 후 응답

- `http://localhost:8080/hello` 요청 시 `HelloWorld` 문구를 출력하는 부분 구현
- `HelloController.java` 파일을 생성하여 코드를 작성
- `@RestController`을 통해 요청을 처리할 클래스임을 지정
- `@GetMapping("/hello")`을 통해 `/hello` 요청을 처리할 메서드를 지정

```java
package nextstep.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        return "Hello " + name;
    }
}
```

- 브라우저에서 `localhost:8080/hello?name=브라운`으로 요청 후 페이지 확인 (재시작해야 코드 변경이 반영됨)

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T15%3A03%3A58.223image.png" width="70%">
</p>

---
## ❗️ DB 접근 흐름도
- DB 접근에 대한 부분은 Spring JDBC 모듈을 통해 처리
<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/cd11ddf0ad55472ca75bbfc689004fb8" width="70%">
</p>

---
## 3. 테이블 정의

- 요청 이력을 저장할 테이블의 스키마 정의

> resources/schema.sql
```
create table hello(
    id bigint auto_increment,
    name varchar(255) not null,
    primary key(id)
);
```

---
## 4. Dao 작성
- <span class=highlight>쿼리를 메서드에 매핑</span>하여 메서드를 통해 DB에 접근

```java
package nextstep.helloworld;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
    private JdbcTemplate jdbcTemplate;

    public HelloDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(String name) {
        String SQL = "insert into HELLO (name) values (?)";
        jdbcTemplate.update(SQL, new Object[]{name});
    }

    public int countByName(String name) {
        String sql = "select count(1) from HELLO where name=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, name);
    }
}
```

---
## 5. Controller에서 Dao 사용하기
- `HelloDao` 객체를 개발자가 직접 생성하지 않고 스프링 컨테이너로 관리하기 위해 `HelloController`에 `HelloDao` 의존을 주입
- 의존성 주입은 생성자 주입 방법을 추천

### 생성자 주입
```java
...

@RestController
public class HelloController {
    private HelloDao helloDao;

    public HelloController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @GetMapping("/hello")
    ...
}
```

---
## 6. 컨트롤러 기능 수정
- name 값이 없으면 그냥 HelloWorld를 화면에 표시
- name 값이 있으면 몇번째 방문인지 알려줌

```java
package nextstep.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private HelloDao helloDao;

    public HelloController(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "") String name) {
        if (name.isEmpty()) {
            return "HelloWorld!";
        }

        helloDao.insert(name);
        int count = helloDao.countByName(name);
        return "Hello " + name + " " + count + "번째 방문입니다.";
    }
}
```

---
## 완성!

<p align="center">
  <img src="https://techcourse-storage.s3.ap-northeast-2.amazonaws.com/2020-03-25T14%3A44%3A22.544image.png" width="70%">
</p>
