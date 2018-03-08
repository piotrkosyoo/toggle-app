package pit.kos.toggle.entity;

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
public class Media {
	private long pk;
	private String comment;
	private LoginData loginData;
}
