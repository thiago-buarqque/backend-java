package com.evry.analytics;

import com.evry.analytics.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    public void testFlatMap() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a", "b"),
                                                Arrays.asList("c", "d"));

        List<String> flatList = list.stream().flatMap(
                Collection::stream).collect(Collectors.toList());

        List<String> expectedList = Arrays.asList("a", "b", "c", "d");

        for (int i = 0; i < expectedList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), flatList.get(i));
        }
    }

    @Test
    public void testFilter1() {
        List<String> list = Arrays.asList("a", "b", "b", "a", "b");

        List<String> filteredList = list.stream().filter(
                item -> !item.equals("a")).collect(Collectors.toList());

        List<String> expectedList = Arrays.asList("b", "b", "b");

        for (int i = 0; i < expectedList.size(); i++) {
            Assertions.assertEquals(expectedList.get(i), filteredList.get(i));
        }
    }

    @Test
    public void testFilter2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> itemOptional = list.stream().filter(
                item -> item < 3).findFirst();

        Assertions.assertTrue(itemOptional.isPresent());
        Integer item = itemOptional.get();
        Assertions.assertEquals(1, item);
    }

    @Test
    public void testReduce1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        Integer sum = list.stream().reduce(0, Integer::sum);

        Assertions.assertEquals(15, sum);
    }

    @Test
    public void testReduce2() {
        User user1 = new User();
        user1.setFirstName("James");
        user1.setLastName("Whatever");
        user1.setId(UUID.fromString("351deb70-0247-476e-a740-ec2cd3bbc658"));

        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Whatever");
        user2.setId(UUID.fromString("951deb70-0612-271e-a180-ec2cd3bbc659"));

        List<User> list = Arrays.asList(user1, user2);

        String result = list.stream()
                .reduce("",
                    (accumulator, user) -> accumulator + user.getId().toString(),
                    String::concat
                );

        Assertions.assertEquals(
                "351deb70-0247-476e-a740-ec2cd3bbc658951deb70-0612-271e-a180-ec2cd3bbc659",
                result);
    }

    @Test
    public void testMatches() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "a");

        Stream<String> stream1 = list.stream();
        Assertions.assertTrue(stream1.noneMatch(item -> item.equals("d")));

        Stream<String> stream2 = list.stream();
        Assertions.assertTrue(stream2.anyMatch(item -> item.equals("a")));

        Stream<String> stream3 = list.stream();
        Assertions.assertFalse(stream3.allMatch(item -> item.equals("b")));
    }
}
