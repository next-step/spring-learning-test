package nextstep.helloworld.config.environment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EnvironmentTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void environment() {
        Environment env = context.getEnvironment();
        String key = env.getProperty("security.jwt.token.expire-length");
        assertThat(key).isEqualTo("11");

        String[] activeProfiles = env.getActiveProfiles();
        assertThat(activeProfiles).contains("test");
    }

    @Test
    void propertySources() {
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        assertThat(propertySources).isNotEmpty();
    }
}
