package net.mypieceofthe.java8.oraclesamplequestions;

import java.util.stream.Stream;

/**
 * Created by kgolebiowski on 08/05/2017.
 */
public class Q7 {
    public static void main(String[] args) {
        Stream<Integer> nums = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> nums2 = nums.filter(n -> n % 2 == 1); // Returns new stream, old is not usable any more
        Stream<Integer> nums3 = nums2.map(integer -> integer * 2);
        nums3.forEach(System.out::print);
        //nums.forEach(System.out::print); // java.lang.IllegalStateException: stream has already been operated upon or closed
    }

}
