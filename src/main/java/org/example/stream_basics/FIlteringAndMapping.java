package org.example.stream_basics;

import java.util.List;
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

        //

    }
}
