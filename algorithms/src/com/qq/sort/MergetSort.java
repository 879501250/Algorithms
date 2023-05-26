package com.qq.sort;

import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序：O(n log n)
 * <br>
 * ---------->将原数组分成n个小数组，相邻的小数组按次序组合成新的大数组，再比较组合
 * <br>
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）
 * 策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修
 * 补"在一起，即分而治之)。
 */
public class MergetSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));
        //为了容量理解，我们把冒泡排序的演变过程，给大家展示
        //测试一下冒泡排序的速度 O(n^2), 给 80000 个数据，测试
        //创建要给 80000 个的随机的数组
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000); //生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        System.out.println("排序前的时间是=" + data1.getTime());
        //测试插入排序 -> 70
        mergetSort(arr, 1);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param size 小数组的大小
     */
    public static void mergetSort(int[] arr, int size) {
        if (arr.length <= size) //已经拼接回原数组，回溯
            return;
        else {
            int[] temp = new int[arr.length];
            for (int i = 0; i < arr.length; i += 2 * size) {
                if (i + size >= arr.length) { // 当接下来只有一组数组时，无法拼接，跳出循环
                    while (i < arr.length) {
                        temp[i] = arr[i];
                        i++;
                    }
                    break;
                }
                //拼接接下来两个数组
                int x = i; // 第一个数组的初始位置
                int y = i + size; // 第二个数组的初始位置
                int j = 0; // 此时是处于拼接后数组的哪个位置
                while (x < i + size) { // 遍历第一个数组
                    // 遍历第二个数组
                    while (y < ((i + 2 * size) < arr.length ? (i + 2 * size) : arr.length)) {
                        if(x < i + size) {
                            if (arr[x] < arr[y]) { // x数组的数据小，x入位
                                temp[i + j] = arr[x];
                                j++;
                                x++;
                            } else { // y数组的数据小，y入位
                                temp[i + j] = arr[y];
                                j++;
                                y++;
                            }
                        }else { // 当x数组都入位了，入剩下的y
                            temp[i + j] = arr[y];
                            j++;
                            y++;
                        }
                    }
                    if (x < i + size) { // 当y数组全部入位了，如果还剩x数组，入位x
                        temp[i + j] = arr[x];
                        j++;
                        x++;
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                arr[i] = temp[i];
            }
        }
        mergetSort(arr, 2 * size);
    }
}
