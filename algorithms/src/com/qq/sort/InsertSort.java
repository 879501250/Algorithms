package com.qq.sort;

import java.util.Date;

/**
 * 插入排序：O(n^2)
 * <br>
 * ---------->从后面依次拿一个（按顺序拿，无所谓大小）放到前面合适的位置
 * <br>
 * 插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的。
 * <br>
 * ===============================
 * <br>
 * 插入排序（Insertion Sorting）的基本思想是：把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有
 * 序表中只包含一个元素，无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，把它的排
 * 序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 */
public class InsertSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//         System.out.println("排序前");
//         System.out.println(Arrays.toString(arr));
        //为了容易理解，我们把冒泡排序的演变过程，给大家展示
        //测试一下冒泡排序的速度 O(n^2), 给 80000 个数据，测试
        //创建要给 80000 个的随机的数组
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000); //生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        System.out.println("排序前的时间是=" + data1.getTime());
        //测试插入排序 -> 3188
        insertSort(arr);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        // 第一个不用遍历，然后依次遍历后面每一个数据
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            //取出来的这个数据与前面的数据从后往前依次比较，直到找到合适的位置
            for (int j = i; j > 0; j--) {
                // 如果取出来的数比前面的数小，交换位置
                if (temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else // 如果取出来的数没前面的数小，说明已经到了合适的位置
                    break;
            }
        }
    }
}
