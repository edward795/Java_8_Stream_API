package org.example.stream_basics;

import java.util.*;
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
                .collect(Collectors.groupingBy(Employee::salary, Collectors.counting())));


        //first collect & then count the list
        int count = names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        List::size
                ));
        System.out.println("size : " + count);

        //wrap collectors into a custom object
        Result summary = names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        e -> new Result(e.size())
                ));
        System.out.println(summary);

        //sort after collecting into a set
        Set<String> sortedSet=names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        s->s.stream()
                                .sorted()
                                .collect(Collectors.toCollection(LinkedHashSet::new))
                ));
        System.out.println(sortedSet);

        //find longest string after collecting
        String longest=names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        l->l.stream()
                                .max(Comparator.comparingInt(String::length))
                                .orElse(null)
                ));
        System.out.println(longest);

        List<Employee1> employeesList = List.of(
                new Employee1("Alice",  "IT",      90000),
                new Employee1("Bob",    "IT",      70000),
                new Employee1("Carol",  "HR",      65000),
                new Employee1("David",  "HR",      85000),
                new Employee1("Eve",    "Finance", 78000),
                new Employee1("Frank",  "Finance", 88000)
        );

        //get max salary after collecting employees by department
        Map<String,Integer> maxSalaryByDept=employeesList.stream()
                .collect(Collectors.groupingBy(
                    Employee1::department,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Employee1::salary)),
                                opt->opt.get().salary()
                        )
                ));
        System.out.println(maxSalaryByDept);

        //remove duplicates,then sort & make unmodifiable
        List<String> cleanSorted=names.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        s->s.stream()
                                .sorted()
                                .toList()
                ));
        System.out.println(cleanSorted);

        //convert list to set,then back to list & a unique list
        List<String> uniqueNames=names
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        ArrayList::new
                ));
        System.out.println(uniqueNames);

        //find the longest string after collecting everything
        String longestString=names
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        l->l.stream()
                                .max(Comparator.comparingInt(String::length))
                                .orElse(null)
                ));
        System.out.println(longestString);

        List<Integer> nums=List.of(1,2,3,4,5);
        double avg=nums.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        l->l.stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .orElse(0)
                ));
        System.out.println(avg);

        //group by department but make the list unmodifiable
        Map<String,List<Employee1>> groupedByDept=
                employeesList.stream()
                        .collect(Collectors.groupingBy(
                                Employee1::department,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        Collections::unmodifiableList
                                )
                        ));
        System.out.println(groupedByDept);

        String smallest=names
                .stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        s->s.stream()
                                .sorted()
                                .findFirst()
                                .orElse(null)
                ));
        System.out.println(smallest);
    }



    public record Employee(String name, int salary) {
    }

    public record Result(int count) {
    }

   public record Employee1 (String name,String department,int salary){}

}