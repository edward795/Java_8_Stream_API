package org.example.stream_basics;

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


    }

    public record Employee(String name, int salary) {
    }

    ;
}
