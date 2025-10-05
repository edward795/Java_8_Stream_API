package org.example.stream_basics;

import org.w3c.dom.ls.LSException;

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

        //check if any number is greater than 10
        List<Integer> elements= List.of(10,12,14,16,18);
        System.out.println(elements.stream().anyMatch(n->n>10));

        //check if all numbers is greater than 10
        System.out.println(elements.stream().allMatch(n->n>10));

        //check if none is negative
        System.out.println(elements.stream().noneMatch(n->n<0));

    }
}
