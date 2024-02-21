package com.qq.Behavioral.Iterator.demo1;

import java.util.ArrayList;

/**
 * 学生管理器
 */
public class StudentAggregate {
    /**
     * 学生集合
     */
    private ArrayList<Student> list;

    public StudentAggregate() {
        this.list = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.list.add(student);
    }

    public void removeStudent(Student student) {
        this.list.remove(student);
    }

    public StudentIterator getStudentIterator() {
        return new StudentIteratorImpl(this.list);
    }
}
