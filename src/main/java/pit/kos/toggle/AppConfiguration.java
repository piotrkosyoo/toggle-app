package pit.kos.toggle;


import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.cache.CacheBuilder;

import pit.kos.toggle.constant.Constants;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.services.LoginServices;
import pit.kos.toggle.services.impl.LoginRepositoryImpl;

/**
 * @author Piotr Kosmala
 * 
 */
@Configuration
@SpringBootApplication
@PropertySource("classpath:feature.properties")
@EnableCaching
public class AppConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

    //clean cache
    @CacheEvict(allEntries = true, cacheNames = { Constants.LOGIN_CACHE })
    @Scheduled(cron="0 0 3 ? * *")
    public void cacheEvict() {
    }

    // remove login after 30 minutes
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ConcurrentMapCache loginCache = new ConcurrentMapCache(Constants.LOGIN_CACHE,
                CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build().asMap(),false);
        cacheManager.setCaches(Arrays.asList(loginCache));
        return cacheManager;
    }

    @Bean
    @Scope(value="singleton")
    public LoginRepository loginRepository(@Autowired LoginServices loginServices) {
        return new LoginRepositoryImpl(loginServices);
    }
    
    @Bean
    @Scope(value="singleton")
    public LoginServices loginServices() {
        return new LoginServices();
    }
}