package org.example.stream_basics;

import java.util.List;

public class Aggregation {
    public static void main(String[] args) {
        //average of numbers
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        double average = numbers.stream()
                .mapToInt(Integer::intValue) // converts Stream<Integer> → IntStream
                .average()                   // returns OptionalDouble
                .orElse(0.0);                // default if stream empty

        System.out.println("Average: " + average);
    }
}
