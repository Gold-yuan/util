package sichuan.ytf;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws Exception {
        // 反推如下
        List<? super Appel> list = null;
        list = getFoods();
        // list.addAll(getFoods()); // 此处报错，编译不通过，原因是如果这个通过，那么下面这句话也该通过
        // list.addAll(getFruits()); // 如果这两个都通过，则说明List中含有Food、Fruit两个实例，从而违背了本文第一句话。
        // <? extends Fruit> 同理
        list.addAll(new ArrayList<Appel>());
    }

    class Food{

    }
    class Fruit extends Food{

    }
    class Appel extends Fruit{

    }

    public static ArrayList<Food> getFoods(){
        return new ArrayList<Food>();
    }
    public static ArrayList<Fruit> getFruits(){
        return new ArrayList<Fruit>();
    }
}
