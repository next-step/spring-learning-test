# 📖 Spring Configuration 학습 테스트
- Spring Configuration 기능을 학습하기 위한 테스트 프로젝트

## 테스트 목록
- xmlConfig: xml 기반 설정 학습 테스트
- javaConfig: java 기반 설정 학습 테스트
- environment: 외부 설정 학습 테스트

## 진행 가이드
- 클론 받기 `git clone https://github.com/next-step/spring-learning-test.git`
- 체크아웃 브랜치 `git checkout config`
- 뼈대 코드로 제공된 프로젝트에서 테스트를 성공 시키기 위해 테스트 코드의 주석을 참고하여 프로덕션 코드를 구현하기
- 테스트는 [config](https://github.com/next-step/spring-learning-test/tree/core/src/test/java/nextstep/helloworld/config) 디렉토리 하위에 위치
- [학습 테스트 활용 방법](https://github.com/next-step/spring-learning-test/blob/config/README.md#%ED%95%99%EC%8A%B5-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%99%9C%EC%9A%A9-%EB%B0%A9%EB%B2%95)를 참고하여 학습 및 기능 구현
- 완성 브랜치 [config-sample](https://github.com/next-step/spring-learning-test/tree/config-sample)를 참고해서 진행해도 좋음 `git checkout config-sample`

## 학습 테스트 활용 방법

### java-config

---
#### 1. JavaConfigTest 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 프로덕션 코드에 추가할 내용을 인지하기

```java
/**
 * AnnotationConfigApplicationContext을 통해 AuthenticationPrincipalConfig에 설정된 빈들을 이용한 테스트
 * <p>
 * AuthenticationPrincipalConfig을 이용하여
 * AuthService 빈 등록하기
 */
class JavaConfigTest {
    @Test
    void main() {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloApplication.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        AuthService authService = context.getBean(AuthService.class);
        assertThat(authService).isNotNull();
    }
}
```

---

#### 2. AuthService 빈 등록하기
- `AuthenticationPrincipalConfig`에서 `AuthService`을 빈으로 등록

```java
// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
public class AuthenticationPrincipalConfig {

    // TODO: AuthService 빈을 등록하기
    public AuthService authService() {
        return null;
    }
    ...
}
```

---

#### 3. JavaConfigSpringTest 테스트 확인하기

- 테스트 메서드의 주석을 확인하여 프로덕션 코드에 추가할 내용을 인지하기

```java
/**
 * HelloApplication에 설정된 @SpringBootApplication을 통해 애플리케이션의 모든 빈들을 이용한 테스트
 * <p>
 * AuthenticationPrincipalConfig을 이용하여
 * AuthenticationPrincipalArgumentResolver를 빈 등록하고
 * AuthService에 대한 의존성을 주입하기
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class JavaConfigSpringTest {
    @Autowired
    private AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver;

    @Test
    void main() {
        assertThat(authenticationPrincipalArgumentResolver.findMemberName()).isEqualTo("사용자");
    }
}
```

---

#### 4. AuthenticationPrincipalArgumentResolver 빈 등록하기 
- `AuthenticationPrincipalConfig`에서 `AuthenticationPrincipalArgumentResolver`을 빈으로 등록
- `AuthenticationPrincipalArgumentResolver`에 `AuthService`에 대한 의존성 주입

```java
// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
public class AuthenticationPrincipalConfig {
    ...
    
    // TODO: AuthenticationPrincipalArgumentResolver를 빈 등록하고 authService에 대한 의존성을 주입하기
    @Bean
    public AuthenticationPrincipalArgumentResolver authenticationPrincipalArgumentResolver() {
        return null;
    }
}
```

### environment

#### 1. EnvironmentTest - key 테스트 확인하기
- PropertySourceConfig 설정을 통해 ApplicationContext를 생성하고 JwtTokenKeyProvider를 조회하여 secret key 주입을 검증하는 테스트
- PropertySourceConfig 클래스에서 테스트를 성공시키기 위한 설정을 해야함

```java
    @Test
    void key() {
        ApplicationContext context = new AnnotationConfigApplicationContext(PropertySourceConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        JwtTokenKeyProvider jwtTokenKeyProvider = context.getBean(JwtTokenKeyProvider.class);
        assertThat(jwtTokenKeyProvider.getSecretKey()).isEqualTo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIiLCJuYW1lIjoiSm9obiBEb2UiLCJpYXQiOjE1MTYyMzkwMjJ9.ih1aovtQShabQ7l0cINw4k1fagApg3qLWiB8Kt59Lno");
    }
```

#### 2. PropertySourceConfig 설정하기
- TODO를 따라서 설정을 완료하기
- JwtTokenKeyProvider에 대한 추가 수정은 필요 없음

```java
// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기
public class PropertySourceConfig {

    private final Environment env;

    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    // TODO: application.properties의 security-jwt-token-secret-key 값을 활용하여 JwtTokenKeyProvider를 빈으로 등록하기
    public JwtTokenKeyProvider jwtTokenKeyProvider() {
        return new JwtTokenKeyProvider("");
    }
}
```

#### 3. EnvironmentTest - key 테스트 확인하기
- ValueConfig 설정을 통해 ApplicationContext를 생성하고 JwtTokenExpireProvider 조회하여 validityInMilliseconds 주입을 검증하는 테스트
- ValueConfig, JwtTokenExpireProvider 클래스에서 테스트를 성공시키기 위한 설정을 해야함

```java
    @Test
    void expire() {
            ApplicationContext context = new AnnotationConfigApplicationContext(ValueConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        JwtTokenExpireProvider jwtTokenExpireProvider = context.getBean(JwtTokenExpireProvider.class);
        assertThat(jwtTokenExpireProvider.getValidityInMilliseconds()).isEqualTo(3600000);
        }
```

#### 4. ValueConfig 설정하기
- TODO를 따라서 설정을 완료하기
- JwtTokenExpireProvider 대한 추가 수정이 필요함

```java
// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
// TODO: application.properties 파일을 활용하기 위한 설정 추가하기
// TODO: nextstep.helloworld.core.environment 내에 있는 스프링 빈을 스캔하기
public class ValueConfig {
}
```

#### 5. JwtTokenExpireProvider 설정하기
- validityInMilliseconds에 값을 주입하기 위한 수정하기

```java
@Component
public class JwtTokenExpireProvider {
    // TODO: application.properties의 security-jwt-token-expire-length 값을 활용하여 validityInMilliseconds값 초기화 하기
    private long validityInMilliseconds;

    public JwtTokenExpireProvider(long validityInMilliseconds) {
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public long getValidityInMilliseconds() {
        return validityInMilliseconds;
    }
}
```

## 참고 레퍼런스 모음

- [Java-based Container Configuration](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-basic-concepts)
- [Injecting Inter-bean Dependencies](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-injecting-dependencies)
- [Using @PropertySource](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-using-propertysource)
- [Using @Value](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-value-annotations)