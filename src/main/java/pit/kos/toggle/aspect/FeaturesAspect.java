package pit.kos.toggle.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Piotr Kosmala
 * 
 */

@Aspect
@Component
public class FeaturesAspect {

    private static final Logger LOG = Logger.getLogger(FeaturesAspect.class);

    @Around(value = "@within(featureAssociation) || @annotation(featureAssociation)")
    public Object checkAspect(ProceedingJoinPoint joinPoint, FeatureAssociation featureAssociation) throws Throwable {
        if (featureAssociation.value().isActive()) {
            return joinPoint.proceed();
        } else {
            LOG.error("Feature " + featureAssociation.value().name() + " is not enabled!");
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
    }
}