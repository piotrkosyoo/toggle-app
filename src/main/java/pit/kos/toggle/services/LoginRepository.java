package pit.kos.toggle.services;

import java.io.IOException;

import pit.kos.toggle.entity.User;

/**
 * @author Piotr Kosmala
 * 
 */

@FunctionalInterface
public interface LoginRepository {
    User login(String email, String password) throws IOException;
}
