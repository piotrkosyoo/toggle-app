package pit.kos.toggle.services;

import java.io.IOException;

import pit.kos.toggle.entity.LoginData;
import pit.kos.toggle.entity.User;

/**
 * @author Piotr Kosmala
 * 
 */

public interface LoginRepository {
    User login(LoginData loginData) throws IOException;
}
