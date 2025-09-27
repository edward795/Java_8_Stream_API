package org.example.stream_basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasics {
    public static void main(String[] args) {

        //Stream from list
        List<Integer> nums= Arrays.asList(1,2,3,4,5);
        Stream<Integer> s=nums.stream();

        //Stream of fixed values
        Stream<String> st=Stream.of("a","b","c");

        //Creating an infinite stream
        Stream<Integer> even=Stream.iterate(0,n->n+2);

        //limiting an infinite stream
        Stream.iterate(0,n->n+1)
                .limit(5)
                .forEach(System.out::print);

        System.out.println(

        );

        //convert a stream back to array
        String[] arr=Stream.of("a","b")
                .toArray(String[]::new);
        for(String ele:arr)
            System.out.println(ele);

        //converting primitve stream to a boxed stream
        List<Integer> convertedList= IntStream.range(1,100)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(convertedList);

        //converting string to toUpperCase()
        List<String> lowerCase=List.of("ramu","kiran","edgar");
        List<String> upperCase=lowerCase.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upperCase);




    }
}
