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
public class Followers {
	private long pk;
	private String full_name;
	private String username;
	private LoginData loginData;
}
