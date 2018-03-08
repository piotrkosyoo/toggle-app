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

    @Label("Login")
    @EnabledByDefault
    @Security
    LOGIN_ENABLED;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }

}
