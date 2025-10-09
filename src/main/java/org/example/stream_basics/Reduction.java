package org.example.stream_basics;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reduction {
    public static void main(String[] args) {
        List<Integer> numbersList=List.of(2,4,6,8,10);

        //sum
        Integer sum=numbersList.stream().reduce(0,Integer::sum);
        System.out.println(sum);

        //product
        Integer product=numbersList.stream().reduce(1,(a,b)->a*b);
        System.out.println(product);

        //concatenate strings
        List<String> words=List.of("Apple","Orange","Mango","Grapes");
        System.out.println(words.stream().reduce("",(a,b)->a+b));

        //find longest word in a sentence
        String txt="This is a Cat.I feed this cat,every day!";
        String longestTxt= Stream.of(txt).reduce((a,b)-> a.length()>b.length()?a:b).get();
        System.out.println(longestTxt);

        //Count characters in a string
        long count=txt.chars().count();
        System.out.println(count);

        //Factorial using range closed
        int fact= IntStream.rangeClosed(1,5).reduce(1,(a,b)->a*b);
        System.out.println("factorial : "+fact);

        //maximum using reduce
        List<Integer> nums=List.of(1,2,3,4,5);
        System.out.println(nums.stream().reduce(Integer::max).get());
    }
}
