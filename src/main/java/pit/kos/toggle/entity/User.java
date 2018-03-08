package pit.kos.toggle.entity;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Piotr Kosmala
 * 
 */

@Getter
@Setter
@NoArgsConstructor
public class User {
    private InstagramLoginResult instagramLoginResult;
    private Instagram4j instagram4j;
}
