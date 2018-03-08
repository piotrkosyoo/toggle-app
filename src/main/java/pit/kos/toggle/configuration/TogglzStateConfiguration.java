package pit.kos.toggle.configuration;

import java.io.File;

import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import pit.kos.toggle.feature.MyFeatures;

/**
 * @author Piotr Kosmala
 * 
 */

@Component
public class TogglzStateConfiguration implements TogglzConfig {

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return MyFeatures.class;
    }

    @Override
    public StateRepository getStateRepository() {
        return new FileBasedStateRepository(new File("/tmp/features.properties"));
    }

    @Override
    public UserProvider getUserProvider() {
        return () -> new SimpleFeatureUser("test", true);
    }
}
