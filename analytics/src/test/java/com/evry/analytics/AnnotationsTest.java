package com.evry.analytics;

import com.evry.analytics.DTO.UserDTO;
import com.evry.analytics.model.annotation.exception.JSONSerializableException;
import com.evry.analytics.common.JSONSerializer;
import com.evry.analytics.model.entity.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class AnnotationsTest {

    @Test
    void testJSONSerializationExceptionThrowing() {
        User user = new User();
        user.setFirstName("James");
        user.setLastName("Whatever");
        user.setId(UUID.randomUUID());

        UserDTO userDTO = new UserDTO(user);

        Assertions.assertDoesNotThrow(
                () -> {
                    _jsonSerializer.convertToJSONString(userDTO);
                });

        Assertions.assertThrows(
                JSONSerializableException.class, () -> _jsonSerializer.convertToJSONString(user));
    }

    @Test
    void testJSONSerialization() {
        User user = new User();
        user.setFirstName("James");
        user.setLastName("Whatever");
        user.setId(UUID.fromString("351deb70-0247-476e-a740-ec2cd3bbc658"));

        UserDTO userDTO = new UserDTO(user);

        String expected =
                "{\n"
                        + "    \"birthday\": null,\n"
                        + "    \"firstName\": \"James\",\n"
                        + "    \"lastName\": \"Whatever\",\n"
                        + "    \"address\": null,\n"
                        + "    \"gender\": null,\n"
                        + "    \"phone\": null,\n"
                        + "    \"middleName\": null,\n"
                        + "    \"id\": \"351deb70-0247-476e-a740-ec2cd3bbc658\",\n"
                        + "    \"email\": null,\n"
                        + "    \"createDate\": null\n"
                        + "}";

        Assertions.assertDoesNotThrow(
                () ->
                        Assertions.assertEquals(
                                expected, _jsonSerializer.convertToJSONString(userDTO)));
    }

    private final JSONSerializer _jsonSerializer = new JSONSerializer();
}
