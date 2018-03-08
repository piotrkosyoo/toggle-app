package pit.kos.toggle.feature;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

import pit.kos.toggle.feature.group.Configuration;
import pit.kos.toggle.feature.group.Security;

/**
 * @author Piotr Kosmala
 * 
 */

public enum MyFeatures implements Feature {

    @Label("List features")
    @EnabledByDefault
    @Configuration
    GET_LIST_FEATURE,
    
    @Label("Search user")
    @EnabledByDefault
    @Configuration
    SEARCH_USER_FEATURE,
    
    @Label("Search user by handle")
    @EnabledByDefault
    @Configuration
    GET_USER_FOLLOWERS,
    
    @Label("Follow user")
    @EnabledByDefault
    @Configuration
    USER_FOLLOW,
    
    @Label("Unfollow user")
    @EnabledByDefault
    @Configuration
    USER_UNFOLLOW,
    
    @Label("Get feed for a hashtag")
    @EnabledByDefault
    @Configuration
    TAG_FEED,
    
    @Label("Perform a like operation for a media (hashtag)")
    @EnabledByDefault
    @Configuration
    LIKE,
    
    @Label("Add a comment for a media (hashtag)")
    @EnabledByDefault
    @Configuration
    COMMENT,
    
    @Label("Login")
    @EnabledByDefault
    @Security
    LOGIN_ENABLED;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

}
