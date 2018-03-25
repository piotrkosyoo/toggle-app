package pit.kos.toggle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:feature.properties")
@EnableCaching
@Import(BeanConfigurationTest.class)
public class AppConfigurationTest {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigurationTest.class, args);
    }
    
}