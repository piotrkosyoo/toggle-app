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
public class SearchData {
	private String user;
	private String tag;
	private LoginData loginData;
}
