package org.example.stream_basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SortingAndFinding {
    public static void main(String[] args) {
        //simply sort integers
        List<Integer> unsortedList = Arrays.asList(2, 5, 1, 3, 4);
        List<Integer> sortedList = unsortedList.stream().sorted().toList();
        System.out.println(sortedList);

        //sort strings by length
        List<String> unsortedWords = List.of("ab", "a", "abcdefg", "abcd", "abc");
        List<String> sortedWords = unsortedWords.stream().
                sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println(sortedWords);

        //finding min/max element
        Optional<Integer> minEle=unsortedList.stream().min(Integer::compare);
        System.out.println(minEle.get());
        Optional<Integer> maxEle=unsortedList.stream().max(Integer::compare);
        System.out.println(maxEle.get());

        //find the firstELement in stream
        Integer firstEle=unsortedList.stream().findFirst().orElse(-1);
        System.out.println(firstEle);

        //find any element in a stream
        System.out.println(unsortedList.parallelStream().findAny().orElse(-1));
    }
}
