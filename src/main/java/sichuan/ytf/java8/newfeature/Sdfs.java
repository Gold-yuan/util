package sichuan.ytf.java8.newfeature;

import java.util.function.Supplier;

import sichuan.ytf.java8.newfeature.ConsumerTest.Employee;

public class Sdfs {

    public static void main(String[] args) {
        // 生成固定工资的员工
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee1 = supplier.get();
        employee1.setName("test1");
        Employee employee2 = supplier.get();
        System.out.println("employee1:" + employee1);
        System.out.println("employee2:" + employee2);
    }

}
