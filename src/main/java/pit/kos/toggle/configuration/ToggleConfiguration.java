package pit.kos.toggle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

import pit.kos.toggle.feature.MyFeatures;

/**
 * @author Piotr Kosmala
 * 
 */

@Component
public class ToggleConfiguration {

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }
}
