package org.example.stream_basics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdvancedExxamples {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice Johnson", "Engineering", 95000),
                new Employee("Rahul Mehta", "Finance", 78000),
                new Employee("Sonia Verma", "Human Resources", 65000),
                new Employee("David Kim", "Marketing", 72000),
                new Employee("Priya Nair", "Engineering", 105000),

                // More Engineering
                new Employee("Karan Singh", "Engineering", 88000),
                new Employee("Meera Rao", "Engineering", 110000),
                new Employee("Tom Harris", "Engineering", 99000),

                // More Finance
                new Employee("Anita Desai", "Finance", 82000),
                new Employee("George Patel", "Finance", 76000),
                new Employee("Lina Joseph", "Finance", 90000),

                // More Human Resources
                new Employee("Riya Kapoor", "Human Resources", 68000),
                new Employee("Samuel Paul", "Human Resources", 72000),
                new Employee("Nina Kurian", "Human Resources", 63000),

                // More Marketing
                new Employee("Arjun Das", "Marketing", 76000),
                new Employee("Sandra Lopez", "Marketing", 81000),
                new Employee("Henry Adams", "Marketing", 69000)
        );

        //top 2 salaries employees from each department
//        Map<String, List<Employee>> employeeVsSalary=employees.stream()
//                .collect(Collectors.groupingBy(Employee::department,
//                        Collectors.collectingAndThen(Collectors.toList(),
//                                l->l.stream()
//                                        .sorted(Comparator.comparingInt(Employee::salary).reversed()))
//                                .limit(2)
//                                .toList()
//                ));
//        System.out.println(employeeVsSalary);

        //group by department get average salary per department
                Map<String,Double> employeeVsAvgSal=employees
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingInt(Employee::salary)
                ));
        System.out.println(employeeVsAvgSal);

        //count employees per department
        Map<String,Long> countByDept=
                employees
                        .stream()
                        .collect(Collectors.groupingBy(
                                Employee::department,
                                Collectors.counting()
                        ));
        System.out.println(countByDept);
    }

    public record Employee(String name,String department,int salary){}
}
