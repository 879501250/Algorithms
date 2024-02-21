package com.qq.Behavioral.Iterator.demo1;

public class Test {
    public static void main(String[] args) {
        // 创建 3 个学生对象
        Student tom = new Student("Tom");
        Student jerry = new Student("Jerry");
        Student trump = new Student("Trump");

        // 构造学生对象集合
        StudentAggregate studentAggregate = new StudentAggregate();
        studentAggregate.addStudent(tom);
        studentAggregate.addStudent(jerry);
        studentAggregate.addStudent(trump);

        // 获取学生对象的迭代器
        StudentIterator studentIterator = studentAggregate.getStudentIterator();
        // 判断是否是最后一个对象 , 如果不是 , 获取下一个对象 , 并打印
        while (!studentIterator.isLast()) {
            Student student = studentIterator.nextStudent();
            System.out.println(student);
        }

        // 删除一个对象
        studentAggregate.removeStudent(trump);
        System.out.println("删除 Trump");

        studentIterator = studentAggregate.getStudentIterator();
        // 判断是否是最后一个对象 , 如果不是 , 获取下一个对象 , 并打印
        while (!studentIterator.isLast()) {
            Student student = studentIterator.nextStudent();
            System.out.println(student);
        }
    }
}
