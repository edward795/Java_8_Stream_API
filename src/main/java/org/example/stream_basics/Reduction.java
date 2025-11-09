package org.example.stream_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reduction {
    public static void main(String[] args) {
        List<Integer> numbersList = List.of(2, 4, 6, 8, 10);

        //sum
        Integer sum = numbersList.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        //product
        Integer product = numbersList.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        //concatenate strings
        List<String> words = List.of("Apple", "Orange", "Mango", "Grapes");
        System.out.println(words.stream().reduce("", (a, b) -> a + b));

        //find longest word in a sentence
        String txt = "This is a Cat.I feed this cat,every day!";
        String longestTxt = Stream.of(txt).reduce((a, b) -> a.length() > b.length() ? a : b).get();
        System.out.println(longestTxt);

        //Count characters in a string
        long count = txt.chars().count();
        System.out.println(count);

        //Factorial using range closed
        int fact = IntStream.rangeClosed(1, 5).reduce(1, (a, b) -> a * b);
        System.out.println("factorial : " + fact);

        //maximum using reduce
        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        System.out.println(nums.stream().reduce(Integer::max).get());
        System.out.println(nums.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b));

        //minimum using reduce
        System.out.println(nums.stream().reduce(Integer::min).get());

        System.out.println(nums.stream().reduce(Integer.MAX_VALUE, (a, b) -> a < b ? a : b));

        //count elements
        System.out.println("count : " + nums.stream().count());
        System.out.println(nums.stream().reduce(0, (c, e) -> c + 1, Integer::sum));

        //sum of salaries
        List<Employee> employeeList = List.of(new Employee(1, "Raju", 20000),
                new Employee(2, "Arun", 30000),
                new Employee(3, "Kiran", 40000));

        System.out.println(employeeList.stream().reduce(0,
                (s, e) -> s + e.salary,
                Integer::sum));

        System.out.println(employeeList.stream().mapToInt(Employee::salary).sum());

        //concatenate all string using ,
        List<String> wordsList = List.of("apple", "orange", "guava", "lemon");
        System.out.println(String.join(",", wordsList));
        System.out.println(wordsList.stream().collect(Collectors.joining(",")));
        System.out.println(wordsList.stream().reduce("",
                (partial, word) -> partial.isEmpty() ? word : partial + "," + word));

        //count of words in a paragraph
        String paragraph = "Java Stream API makes coding fun and expressive";
        int wordCount = Arrays.stream(paragraph.split("\\s+"))
                .reduce(0,
                        (c, word) -> c + 1,
                        Integer::sum);
        System.out.println(wordCount);

    }

    public record Employee(int empId, String empName, int salary) {
    }
}
