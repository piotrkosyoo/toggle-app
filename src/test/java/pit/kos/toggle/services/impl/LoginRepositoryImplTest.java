/**
 * 
 */
package pit.kos.toggle.services.impl;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pit.kos.toggle.AppConfigurationTest;
import pit.kos.toggle.entity.LoginData;
import pit.kos.toggle.entity.User;
import pit.kos.toggle.services.LoginRepository;

/**
 * @author Piotr Kosmala
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfigurationTest.class)
public class LoginRepositoryImplTest {
	
	@Autowired
	private LoginRepository loginRepository;
	
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testCacheLogin() throws IOException {
		
		LoginData loginDataFirst = new LoginData();
		loginDataFirst.setEmail("test@com.dk");
		loginDataFirst.setPassword("test");
		User userOne = loginRepository.login(loginDataFirst.getEmail(), loginDataFirst.getPassword());
		
		LoginData loginDataSecond = new LoginData();
		loginDataSecond.setEmail("test@com.dk");
		loginDataSecond.setPassword("test");
		User userTwo =loginRepository.login(loginDataSecond.getEmail(), loginDataSecond.getPassword());
		
		Assert.assertEquals(userOne, userTwo);
	}

	
}
