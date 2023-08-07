package nextstep.helloworld.config.environment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
@ConfigurationPropertiesScan
public class ConfigurationPropertiesTest {

    @Autowired
    private SecurityJwtTokenProperties securityJwtProperties;

    @Test
    void configurationProperties() {
        assertThat(securityJwtProperties.getSecretKey()).isNotBlank();
        assertThat(securityJwtProperties.getExpireLength()).isNotBlank();
    }
}
