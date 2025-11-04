package org.example.stream_basics;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


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

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 95000),
                new Employee("Bob", "IT", 88000),
                new Employee("Charlie", "IT", 99000),
                new Employee("David", "IT", 87000),
                new Employee("Eva", "HR", 78000),
                new Employee("Frank", "HR", 85000),
                new Employee("Grace", "HR", 88000),
                new Employee("Helen", "HR", 91000),
                new Employee("Ian", "Sales", 65000),
                new Employee("Jack", "Sales", 70000),
                new Employee("Kara", "Sales", 72000)
        );

        Map<String, List<Employee>> top3ByDept=employees.stream()
                .collect(Collectors.groupingBy(Employee::department,Collectors.collectingAndThen(
                        toList(),
                        list->list.stream()
                                .sorted(Comparator.comparingDouble(Employee::salary))
                                .limit(3)
                                .toList()
                )));
        System.out.println(top3ByDept);

        Map<String, List<Employee>> deptMap = new HashMap<>();
        deptMap.put("IT", List.of(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "IT", 85000)
        ));
        deptMap.put("HR", List.of(
                new Employee("Grace", "HR", 87000),
                new Employee("Alice", "HR", 90000)
        ));

        List<Employee> allDistinctEmployees=deptMap.values()
                .stream()
                .flatMap(List::stream)
                .distinct()
                .toList();
        System.out.println(allDistinctEmployees);

        List<Integer> numbers = List.of(10, 25, 5, 42, 17);
        int maxValue=numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow();
        System.out.println(maxValue);

        int maximumVal=numbers.stream()
                .reduce(Integer.MIN_VALUE,(a,b)->a>b?a:b);
        System.out.println(maximumVal);

        List<Employee> employees1 = List.of(
                new Employee("Alice", "HR", 55000),
                new Employee("Bob", "Finance", 75000),
                new Employee("Charlie", "IT", 98000),
                new Employee("Diana", "IT", 88000),
                new Employee("Eve", "Finance", 91000)
        );
        Employee highestPaid=employees1.stream().
                max(Comparator.comparingDouble(Employee::salary))
                        .orElseThrow();
        System.out.println(highestPaid);

        Employee emp=employees1.stream()
                .reduce((e1,e2)->e1.salary>e2.salary?e1:e2)
                .orElseThrow();
        System.out.println(emp);

        //sort by marks desc & then by name asc
        Map<String, Integer> marksMap = new HashMap<>();
        marksMap.put("Ravi", 85);
        marksMap.put("Amit", 92);
        marksMap.put("Sneha", 85);
        marksMap.put("Karan", 92);
        marksMap.put("Divya", 78);
        Map<String,Integer> sortedMap1=marksMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey())
                ).collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2)->e1,
                        LinkedHashMap::new));

        System.out.println(sortedMap1);


        //sort first by name & then by salary
        List<Employee1> employeesList = List.of(
                new Employee1("Alice", 70000),
                new Employee1("Bob", 85000),
                new Employee1("Alice", 72000),
                new Employee1("Charlie", 65000),
                new Employee1("Bob", 79000)
        );
        List<Employee1> sortedEmployeesList=employeesList.stream()
                        .sorted(Comparator.comparing(Employee1::name)
                                .thenComparingInt(Employee1::salary))
                                .toList();
        System.out.println(sortedEmployeesList);

        //reverse sorting by salary
        List<Employee1> sortByReverseOrder=employeesList.stream()
                .sorted(Comparator.comparing(Employee1::name)
                        .thenComparingInt(Employee1::salary).reversed())
                .toList();
        System.out.println(sortByReverseOrder);

        //sort entirely by salary
        List<Employee1> sortEntirelyBySalary=employeesList.stream()
                .sorted(Comparator.comparing(Employee1::salary).reversed()
                        .thenComparingInt(Employee1::salary))
                .toList();
        System.out.println(sortEntirelyBySalary);

        //you can use collections.sort() to achive the same

        List<Employee1> listOfEmployees=new ArrayList<>(employeesList);
        listOfEmployees.sort(Comparator.comparing(Employee1::name)
                .thenComparing(Employee1::salary));
        System.out.println(listOfEmployees);

        Map<String, Integer> scores = new HashMap<>();

        scores.put("Alice", 85);
        scores.put("Bob", 92);
        scores.put("Charlie", 78);

        Map<String,Integer> sortedMap2=scores.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldVal,newVal)->oldVal,
                LinkedHashMap::new
        ));
        System.out.println(sortedMap2);

        Map<String,Integer> sortedMap3=scores.entrySet()
                .stream()
                .sorted(Map.Entry.<String,Integer>comparingByKey()
                        .thenComparing(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue,newValue)->oldValue,
                                LinkedHashMap::new));
        System.out.println(sortedMap3);

//        Map<String,Integer> sortedMap4=scores.entrySet()
//                .stream().sorted(
//                        Map.Entry.<String,Integer>comparingByKey()
//                                .thenComparing(Map.Entry.comparingByValue().reversed())
//                )
//                .collect(Collectors.toMap(Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldValue,newValue)->oldValue),
//                        LinkedHashMap::new);

//        System.out.println(sortedMap4);


    }

    public record Employee(String name,String department,double salary){}
    public record Employee1(String name,int salary){};
}
