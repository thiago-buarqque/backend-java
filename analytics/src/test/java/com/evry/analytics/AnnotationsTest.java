package com.evry.analytics;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.common.JSONSerializer;
import com.evry.analytics.annotations.exceptions.JSONSerializableException;
import com.evry.analytics.entity.User;
import com.evry.analytics.restController.UserRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnnotationsTest {

	@Test
	void testJSONSerializationExceptionThrowing() {
		User user = new User();
		user.setName("James Matthew");
		user.setId(1L);

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
		user.setId(1L);

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

	final JSONSerializer jsonSerializer = new JSONSerializer();

}
