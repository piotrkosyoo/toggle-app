package pit.kos.toggle.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pit.kos.toggle.feature.MyFeatures;

/**
 * @author Piotr Kosmala
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface FeatureAssociation {
    MyFeatures value();
}