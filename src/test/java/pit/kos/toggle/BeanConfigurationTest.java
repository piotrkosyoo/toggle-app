package pit.kos.toggle;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.cache.CacheBuilder;

import pit.kos.toggle.constant.Constants;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.services.LoginServices;
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
    @Scope(value="singleton")
    public LoginRepository loginRepository(@Autowired LoginServices loginServices) {
        return new LoginRepositoryImpl(loginServices);
    }
    
    @Bean
    @Scope(value="singleton")
    public LoginServices loginServices() throws ClientProtocolException, IOException {
    	LoginServices mock = Mockito.mock(LoginServices.class);
    	Mockito.when(mock.login(Matchers.any(Instagram4j.class))).thenReturn(new InstagramLoginResult());
        return mock;
    }
}
