package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonStreamOperations {
    public static void main(String[] args) {
        //elements
        int[] integerArr=new int[]{1,2,2,3,3,3,4,4,4,4};

        //filter out even elements
        List<Integer> evenList= Arrays.stream(integerArr)
                .boxed()
                .filter(n->n%2==0)
                .toList();
        System.out.println(evenList);

        //square individual elements
        List<Integer> squaredElements=Arrays.stream(integerArr)
                .boxed()
                .map(n->n*n)
                .toList();
        System.out.println(squaredElements);

        //capitalize strings
        String[] arrOfWords=new String[]{"cat","dog","kauala"};
        List<String> capitalizedWords= Arrays.stream(arrOfWords)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(capitalizedWords);

        //Extract Length of words
        List<Integer> lenOfWords= Arrays.stream(arrOfWords)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lenOfWords);

        //Flatten list of lists
        List<List<Integer>> list=Arrays.asList(Arrays.asList(1,2),Arrays.asList(3,4));
        System.out.println(list);
        List<Integer> flatList=list.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println(flatList);

        //filter out nulls
        List<Integer> numbersWithNulls=new ArrayList<>(Arrays.asList(1,2,null,3,4,null,5));
        List<Integer> filteredList=numbersWithNulls.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        System.out.println(filteredList);

        //print disticnt elements alone
        List<Integer> repeatedNums=List.of(1,2,2,3,3,3,4,4,4,4);
        List<Integer> distinctNums=repeatedNums.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctNums);
    }
}
