package pit.kos.toggle.services.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.cache.annotation.Cacheable;

import pit.kos.toggle.constant.Constants;
import pit.kos.toggle.entity.LoginData;
import pit.kos.toggle.entity.User;
import pit.kos.toggle.services.LoginRepository;

/**
 * @author Piotr Kosmala
 * 
 */

public class LoginRepositoryImpl implements LoginRepository {

    private static final Logger logger = Logger.getLogger(LoginRepositoryImpl.class);

    @Cacheable(cacheNames = Constants.LOGIN_CACHE)
    @Override
    public User login(LoginData loginData) throws IOException {
        logger.info("try login user: " + loginData.getEmail());
        User user = new User();
        Instagram4j instagram = Instagram4j.builder().username(loginData.getEmail()).password(loginData.getPassword()).build();
        user.setInstagram4j(instagram);
        instagram.setup();
        user.setInstagramLoginResult(instagram.login());
        return user;
    }
}
