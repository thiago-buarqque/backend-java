package com.evry.analytics;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.common.JSONSerializer;
import com.evry.analytics.common.exceptions.JSONSerializableException;
import com.evry.analytics.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnalyticsApplicationTests {

	final JSONSerializer jsonSerializer = new JSONSerializer();

	@Test
	void testJSONSerializationExceptionThrowing() {
		User user = new User();
		user.setName("James Matthew");
		user.setUserId(1L);

		UserDTO userDTO = new UserDTO(user);

		Assertions.assertDoesNotThrow(() -> {
			jsonSerializer.convertToJSONString(userDTO);
		});

		Assertions.assertThrows(JSONSerializableException.class, () -> {
			jsonSerializer.convertToJSONString(user);
		});
	}

	@Test
	void testJSONSerialization() {
		User user = new User();
		user.setName("James Matthew");
		user.setUserId(1L);

		UserDTO userDTO = new UserDTO(user);
		;

		String expected = "\"name\":\"James Matthew\",\"userId\":1";
		try {
			Assertions.assertEquals(expected,
				jsonSerializer.convertToJSONString(userDTO)
			);
		} catch (JSONSerializableException | IllegalAccessException exception) {
		}
	}
}
