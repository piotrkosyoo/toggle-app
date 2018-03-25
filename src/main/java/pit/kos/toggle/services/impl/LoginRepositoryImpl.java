package pit.kos.toggle.services.impl;

import java.io.IOException;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import lombok.extern.slf4j.Slf4j;
import pit.kos.toggle.constant.Constants;
import pit.kos.toggle.entity.User;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.services.LoginServices;

/**
 * @author Piotr Kosmala
 * 
 */
@Slf4j
public class LoginRepositoryImpl implements LoginRepository {
	
	private LoginServices loginServices;
	
	@Autowired
	public LoginRepositoryImpl(LoginServices loginServices) {
		this.loginServices = loginServices;
	}

    @Cacheable(cacheNames = Constants.LOGIN_CACHE)
    public User login(String email, String password) throws IOException {
    	log.info("email={} password={XXX}", email);
        User user = new User();
        Instagram4j instagram4j = Instagram4j.builder().username(email).password(password).build();
        user.setInstagram4j(instagram4j);
        instagram4j.setup();
        InstagramLoginResult result = loginServices.login(instagram4j);
        user.setInstagramLoginResult(result);
        return user;
    }
}
