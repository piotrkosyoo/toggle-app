package pit.kos.toggle.feature.group;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.togglz.core.annotation.FeatureGroup;
import org.togglz.core.annotation.Label;

/**
 * @author Piotr Kosmala
 * 
 */

@FeatureGroup
@Label("Configuration")
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
}
