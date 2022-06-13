package com.qq.sort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        for (int i =0;i<list.size();i++){
            if(i/2==0)
                list.remove(i);
        }
        System.out.println(list);
    }
}
