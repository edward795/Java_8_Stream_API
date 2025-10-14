package org.example.stream_basics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class MiscellaneousQuestions {
    public static void main(String[] args) {
        //find the frequency of charscters in a string
        String test1 = "My name is Ayyappa";
        Map<Character, Long> freq = test1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(freq);

        //exclude spaces & non-letters
        Map<Character, Long> freq2 = test1.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(freq2);

        //sort the resultant map by alphabets
        LinkedHashMap<Character, Long> sortedMap = freq2.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        System.out.println(sortedMap);

        //freq of words in sentence
        String sentence = "this is a test this is only a test";
        Map<String,Long> freq3= Arrays.stream(sentence.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(freq3);
        LinkedHashMap<String,Long> sortedFreq3=freq3.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new));
        System.out.println(sortedFreq3);
    }
}
