package org.example.stream_basics;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsMastery {
    public static void main(String[] args) {
        //collect to a list
        List<Integer> numberList = List.of(1, 2, 3, 4, 5);
        List<Integer> list = numberList.stream()
                .collect(java.util.stream.Collectors.toList());
        System.out.println(list);

        //collect unique numbers to a set
        List<Integer> numbersList2 = List.of(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> set = numbersList2.stream()
                .collect(java.util.stream.Collectors.toSet());
        System.out.println(set);

        //collect employee's name & salary into a map
        List<Employee> employees = List.of(
                new Employee("Alice", 5000),
                new Employee("Bob", 7000),
                new Employee("Charlie", 4000)
        );
        Map<String, Integer> empMap = employees.stream()
                .collect(Collectors.toMap(Employee::name, Employee::salary));
        System.out.println(empMap);

        //what happens when you have dups
        List<Employee> employeesWithDups = List.of(
                new Employee("Alice", 5000),
                new Employee("Bob", 7000),
                new Employee("Charlie", 4000),
                new Employee("Alice", 7000)
        );

        //provide a merge function to handle the duplicates
        //Keep the 1st occurrence
        Map<String, Integer> employeeMap = employeesWithDups
                .stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        (existing, replacement) -> existing
                ));
        System.out.println(employeeMap);

        //keep the 2nd occurrence
        Map<String, Integer> employeeMap2 = employeesWithDups
                .stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        (existing, replacement) -> replacement
                ));
        System.out.println(employeeMap2);

        //sum salaries of duplicate elements
        Map<String, Integer> employeeMap3 = employeesWithDups
                .stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        Integer::sum
                ));
        System.out.println(employeeMap3);

        //keep the higher salary
        System.out.println(employeesWithDups.stream()
                .collect(Collectors.toMap(
                        Employee::name,
                        Employee::salary,
                        (e1, e2) -> Math.max(e1, e2)
                )));


        //post process result with collectingAndThen
        List<String> names = List.of("Alice", "Bob", "Charlie");
        List<String> unmodifiableList = names
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList
                ));
        System.out.println(unmodifiableList);

        long distinctCount = names
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        s -> (long) s.size()
                ));
        System.out.println(distinctCount);

        //joining
        System.out.println(names.stream()
                .collect(Collectors.joining(",", "[", "]")));

        System.out.println(names.stream().collect(Collectors.counting()));

        //grouping by
        System.out.println(employees.stream()
                .collect(Collectors.groupingBy(Employee::salary,Collectors.counting())));

    }

    public record Employee(String name, int salary) {
    }

    ;
}
