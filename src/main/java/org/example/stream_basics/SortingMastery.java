package org.example.stream_basics;

import java.util.*;
import java.util.stream.Collectors;


public class SortingMastery {
    public static void main(String[] args) {

        //sort in ascending order
        List<Integer> numbers = List.of(5, 2, 8, 1, 3);
        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);


        //sort in descending order
        List<Integer> sortedReverseOrder = numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sortedReverseOrder);

        //sort alphabetically ignoring case
        List<String> words = List.of("Banana", "apple", "Cherry", "date");
        List<String> sortedString = words.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
        System.out.println(sortedString);

        //sort employees by salary
        List<Employee> employees = List.of(
                new Employee("Alice", 5000),
                new Employee("Bob", 7000),
                new Employee("Charlie", 4000)
        );
        List<Employee> sortedEmployees = employees.stream().
                sorted(Comparator.comparingInt(e -> e.salary))
                .toList();
        System.out.println(sortedEmployees);


        //sort multiple fields name ,salary
        List<Employee> sortBy2Fields = employees.stream()
                .sorted(Comparator
                        .comparing((Employee e) -> e.name)
                        .thenComparingInt(e -> e.salary))
                .collect(Collectors.toList());
        System.out.println(sortBy2Fields);

        Map<String, Integer> map = Map.of(
                "Banana", 3,
                "Apple", 5,
                "Cherry", 2
        );

        //sort a map by key
        Map<String, Integer> sortedMapByKey = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        System.out.println(sortedMapByKey);

        //sort by value
        Map<String, Integer> sortedMapByValue = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println(sortedMapByValue);

        //sort list by string length
        List<String> wordsList = List.of("elephant", "dog", "cat", "hippopotamus");
        List<String> sortedByLength = wordsList.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println(wordsList.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList());

        //sort & limit to top 3
        List<Integer> numbersList = List.of(5, 9, 1, 7, 3, 10, 4);
        System.out.println(numbersList.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList());

        //⚖️ 40️⃣ Stable vs Unstable Sort — how does Streams behave?
        List<Employee> people = List.of(
                new Employee("Alice", 3000),
                new Employee("Bob", 3000),
                new Employee("Charlie", 4500)
        );

        System.out.println(people.stream()
                .sorted(Comparator.comparingInt(e -> e.salary))
                .toList());
        /*
        Their original order (Alice before Bob) is preserved → ✅ stable sort.

        ⚙️ Internally:

        Java Streams’ sorted() uses TimSort, which is stable.
         */
    }

    public record Employee(String name, int salary) {
    }
}
