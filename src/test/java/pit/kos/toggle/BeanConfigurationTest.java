package pit.kos.toggle;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.cache.CacheBuilder;

import pit.kos.toggle.constant.Constants;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.services.impl.LoginRepositoryImpl;

@TestConfiguration
public class BeanConfigurationTest {

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
    public LoginRepository loginRepository() {
        return new LoginRepositoryImpl();
    }
}
