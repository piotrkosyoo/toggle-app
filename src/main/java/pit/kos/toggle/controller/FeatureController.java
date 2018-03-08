package pit.kos.toggle.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pit.kos.toggle.aspect.FeatureAssociation;
import pit.kos.toggle.constant.Endpoints;
import pit.kos.toggle.feature.MyFeatures;

/**
 * @author Piotr Kosmala
 * 
 */

@RestController
@RequestMapping(Endpoints.FEATURE)
public class FeatureController {

    @FeatureAssociation(value = MyFeatures.GET_LIST_FEATURE)
    @GetMapping(path = Endpoints.GET_FEATURES)
    public ResponseEntity<Map<String, Boolean>> getFeature() {
        Map<String, Boolean> entities = new HashMap<>();
        for (MyFeatures e: MyFeatures.values()) {
            entities.put(e.name(), e.isActive());
        }
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}