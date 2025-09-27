package org.example.stream_basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FIlteringAndMapping {
    public static void main(String[] args) {
        List<Integer> numsTo100= IntStream.range(1,100)
                .boxed()
                .collect(Collectors.toList());

        //filter() for evens
        List<Integer> evens=numsTo100.stream().filter(n->n%2==0).toList();
        System.out.println(evens);

        //map() for squares
        List<Integer> squares=numsTo100.stream().map(n->n*n).toList();
        System.out.println(squares);

        //extract Length of strings
        List<String> lowerCase=List.of("apple","banana","orange");
        List<Integer> stringLens=lowerCase.stream().map(String::length)
                .toList();
        System.out.println(stringLens);

        //Flatten list of lists
        List<List<Integer>> list= Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(4,5,6));
        List<Integer> flattenedList=list.stream().flatMap(List::stream).toList();
        System.out.println(flattenedList);

        //removes nulls from a list
        ArrayList<Integer> arrOfNulls=new ArrayList<>(Arrays.asList(null,1,2,null,3,4,null));
        List<Integer> nonNullList=arrOfNulls.stream().filter(Objects::nonNull).toList();
        System.out.println(nonNullList);

        //get distinct elements in a list
        List<Integer> dupsList=List.of(1,2,2,3,3,3,4,4,4,4);
        List<Integer> uniqueList=dupsList.stream().distinct().toList();
        System.out.println(uniqueList);

        //skip first 2 elements
        dupsList.stream().skip(2).forEach(System.out::println);

        //take only first 3 elements
        dupsList.stream().limit(3).forEach(System.out::println);

    }
}
