package com.evry.analytics;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.common.JSONSerializer;
import com.evry.analytics.annotations.exceptions.JSONSerializableException;
import com.evry.analytics.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
            _jsonSerializer.convertToJSONString(userDTO);
        });

        Assertions.assertThrows(
            JSONSerializableException.class,
            () -> _jsonSerializer.convertToJSONString(user)
        );
    }

    @Test
    void testJSONSerialization() {
        User user = new User();
        user.setName("James Matthew");
        user.setId(1L);
        user.setArrayTest(new String[]{"Test1", "Test2"});

        UserDTO userDTO = new UserDTO(user);

        String expected = "{\n" +
            "    \"phoneNumber\": null,\n" +
            "    \"name\": \"James Matthew\",\n" +
            "    \"arrayTest\": [\"Test1\", \"Test2\"],\n" +
            "    \"id\": 1\n" +
            "}";

        Assertions.assertDoesNotThrow(() ->
            Assertions.assertEquals(expected,
                _jsonSerializer.convertToJSONString(userDTO)
            )
        );
    }

    private final JSONSerializer _jsonSerializer = new JSONSerializer();

}
