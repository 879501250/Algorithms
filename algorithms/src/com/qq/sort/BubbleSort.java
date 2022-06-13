package com.qq.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序：O(n^2)
 * ---------->依次从前面全部找到最大的数换到最后
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较
 * 相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
 * ========================
 * 优化：
 * 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在
 * 排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排
 * 序写好后，在进行)
 */
public class BubbleSort {
    public static void main(String[] args) {
//         int arr[] = {3, 9, -1, 10, 20};
//         System.out.println("排序前");
//         System.out.println(Arrays.toString(arr));
        //为了容量理解，我们把冒泡排序的演变过程，给大家展示
        //测试一下冒泡排序的速度 O(n^2), 给 80000 个数据，测试
        //创建要给 80000 个的随机的数组
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000); //生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        System.out.println("排序前的时间是=" + data1.getTime());
        //测试冒泡排序 -> 25020
        bubbleSort(arr);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        // 定义一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较
        boolean flag = true;
        // 最多进行length-1次遍历
        for (int i = 0; i < arr.length - 1; i++) {
            // 进行一趟排序
            flag = true;
            // 因为冒泡是先将最大的元素排好，所以遍历次数可以-i，不用比较后面的数据了
            for (int j = 0; j < arr.length - 1 -i; j++) {
                // 如果前数比后数大，则交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) // 如果这次排序已经有序，则不用接下来的排序
                break;
        }
    }
}
