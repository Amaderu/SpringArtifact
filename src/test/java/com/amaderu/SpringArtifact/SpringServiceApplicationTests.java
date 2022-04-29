package com.amaderu.SpringArtifact;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = SpringServiceApplication.class)
class SpringServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void givenCustomRepository_whenInvokeCustomFindMethod_thenEntityIsFound() {
		/*User user = new User();
		user.setEmail("foo@gmail.com");
		user.setName("userName");*/

		/*User persistedUser = userRepository.save(user);*/

		/*assertEquals(persistedUser, userRepository.customFindMethod(user.getId()));*/
	}

}
