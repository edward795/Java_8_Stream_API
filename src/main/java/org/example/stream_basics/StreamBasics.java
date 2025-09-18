package org.example.stream_basics;

import java.util.Arrays;
import java.util.List;
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

    }
}
