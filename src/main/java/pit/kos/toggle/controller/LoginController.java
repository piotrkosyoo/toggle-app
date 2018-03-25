package pit.kos.toggle.controller;

import org.apache.log4j.Logger;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pit.kos.toggle.aspect.FeatureAssociation;
import pit.kos.toggle.constant.Endpoints;
import pit.kos.toggle.entity.LoginData;
import pit.kos.toggle.entity.User;
import pit.kos.toggle.feature.MyFeatures;
import pit.kos.toggle.services.LoginRepository;
import pit.kos.toggle.utils.Validator;

/**
 * @author Piotr Kosmala
 * 
 */

@RestController
@RequestMapping(Endpoints.USER)
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginRepository loginRepository;

    @FeatureAssociation(value = MyFeatures.LOGIN_ENABLED)
    @RequestMapping(path = Endpoints.USER_LOGIN, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstagramLoggedUser> login(@RequestBody LoginData loginData) {

        if (Validator.validateLoginForm(loginData.getEmail(), loginData.getPassword())) {
            try {
                User logedUser = loginRepository.login(loginData.getEmail(), loginData.getPassword());
                return new ResponseEntity<>(logedUser.getInstagramLoginResult().getLogged_in_user(), HttpStatus.OK);
            } catch (Exception e) {
                logger.info(e);
            }
        } else {
            logger.info("Validate error for:" + loginData.getEmail());
        }

        return new ResponseEntity<>(new InstagramLoggedUser(), HttpStatus.BAD_REQUEST);
    }
}
