package sichuan.ytf.java8.newfeature;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class FunctionTest {
    public static void main(String[] args) {
        biConsumerTest();
        biFunctionTest();
    }

    public static void biConsumerTest() {
        BiConsumer<Integer, Integer> add = (a, b) -> System.out.println(a + "" + b);
        add.accept(1, 2);
    }

    public static void biFunctionTest() {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        Integer apply = add.apply(1, 2);
        System.out.println(apply);
    }

}
