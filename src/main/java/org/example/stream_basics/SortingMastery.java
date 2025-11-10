package org.example.stream_basics;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingMastery {
    public static void main(String[] args) {

        //sort in ascending order
        List<Integer> numbers = List.of(5, 2, 8, 1, 3);
        List<Integer> sorted=numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);


        //sort in descending order
        List<Integer> sortedReverseOrder=numbers
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(sortedReverseOrder);

        //sort alphabetically ignoring case
        List<String> words = List.of("Banana", "apple", "Cherry", "date");
        List<String> sortedString=words.stream()
                        .sorted(String::compareToIgnoreCase)
                                .collect(Collectors.toList());
        System.out.println(sortedString);

        //sort employees by salary
        List<Employee> employees = List.of(
                new Employee("Alice", 5000),
                new Employee("Bob", 7000),
                new Employee("Charlie", 4000)
        );
        List<Employee> sortedEmployees=employees.stream().
                sorted(Comparator.comparingInt(e->e.salary))
                .toList();
        System.out.println(sortedEmployees);


        //sort multiple fields name ,salary
        List<Employee> sortBy2Fields=employees.stream()
                .sorted(Comparator
                        .comparing((Employee e) -> e.name)
                        .thenComparingInt(e -> e.salary))
                .collect(Collectors.toList());
        System.out.println(sortBy2Fields);
    }

    public record Employee(String name,int salary){}
}
