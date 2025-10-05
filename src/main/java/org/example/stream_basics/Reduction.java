package org.example.stream_basics;

import java.util.List;

public class Reduction {
    public static void main(String[] args) {
        List<Integer> numbersList=List.of(2,4,6,8,10);

        //sum
        Integer sum=numbersList.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        //product
        Integer product=numbersList.stream().reduce(1,(a,b)->a*b);
        System.out.println(product);
    }
}
