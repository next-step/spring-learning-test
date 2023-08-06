package nextstep.helloworld.config.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * ConfigurationPropertiesTest 에서 사용 중
 * ConfigurationPropertiesTest 에서 ConfigurationPropertiesScan 을 선언해주었기 때문에 컴파일 에러 무시 가능
 *
 * setter를 추가하지 말고 동작하도록 해보세요.
 */
@ConfigurationProperties(prefix = "security.jwt.token")
@ConstructorBinding
public class SecurityJwtTokenProperties {
    private String secretKey;
    private String expireLength;

    public SecurityJwtTokenProperties(String secretKey, String expireLength) {
        this.secretKey = secretKey;
        this.expireLength = expireLength;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getExpireLength() {
        return expireLength;
    }
}

