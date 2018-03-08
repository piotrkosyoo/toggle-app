package pit.kos.toggle.utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * @author Piotr Kosmala
 * 
 */

public class Validator {

    public static boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean validatePassword(String password) {
        return password != null && password.length() > 6;
    }

    public static boolean validateLoginForm(String email, String password) {
        return validateEmail(email) && validatePassword(password);
    }
}
