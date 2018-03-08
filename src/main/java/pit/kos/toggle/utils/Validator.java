package pit.kos.toggle.utils;

import org.apache.commons.validator.routines.EmailValidator;

import pit.kos.toggle.entity.Followers;
import pit.kos.toggle.entity.Media;
import pit.kos.toggle.entity.SearchData;

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
    
    public static boolean notNull(Object value) {
        return value != null;
    }

    public static boolean validateSearchData(SearchData searchData) {
    	return Validator.notNull(searchData) && Validator.notNull(searchData.getUser()) && Validator.notNull(searchData.getLoginData()) && 
				Validator.validateLoginForm( searchData.getLoginData().getEmail(),  searchData.getLoginData().getPassword());
    }
    
    public static boolean validateFollowers(Followers followers) {
    	return Validator.notNull(followers) && Validator.notNull(followers.getLoginData()) &
				Validator.validateLoginForm( followers.getLoginData().getEmail(),  followers.getLoginData().getPassword());
    }
    
    public static boolean validateMediaLike(Media media) {
    	return Validator.notNull(media) && Validator.notNull(media.getLoginData());
    }
    
    public static boolean validateMediaComment(Media media) {
    	return Validator.notNull(media) && Validator.notNull(media.getLoginData()) && Validator.notNull(media.getComment());
    }
    
}
