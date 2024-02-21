package com.qq.Behavioral.Iterator.demo1;

/**
 * 学生的迭代器接口
 */
public interface StudentIterator {
    /**
     * 获取下一个学生对象
     * @return
     */
    Student nextStudent();

    /**
     * 是否是最后一个
     * @return
     */
    boolean isLast();
}
