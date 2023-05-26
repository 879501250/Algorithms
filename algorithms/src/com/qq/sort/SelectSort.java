package com.qq.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序：O(n^2)
 * <br>
 * ---------->依次从后面全部找出最小的数放到前面
 * <br>
 * 选择式排序也属于内部排序法，是从欲排序的数据中，按指定的规则选出某一元素，再依规定交换位置后达到
 * 排序的目的。
 * <br>
 * ========================
 * <p>
 * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：第一次从 arr[0]~arr[n-1]中
 * 选取最小值，与 arr[0]交换，第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，
 * 第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2]交换，…，第 i 次从 arr[i-1]~arr[n-1]中选取最小值，
 * 与 arr[i-1]交换，…, 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值，与 arr[n-2]交换，
 * 总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列。
 */
public class SelectSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
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
        //测试选择排序 -> 5556
        selectSort(arr);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        // 将前面length-1个数据排好时，最后一个数据也是有序的了，所以不要遍历
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i]; // 用来标记最小的数据
            int n = i; // 用来标记最小数据的下标
            for (int j = i + 1; j < arr.length; j++) { // 遍历已经排序好后面的全部数据找出最小的数据
                if (min > arr[j]) { // 如果存在更小的值时记录下来
                    min = arr[j];
                    n = j;
                }
            }
            if (n != i) { //遍历的第一个数据不是最小值时，交换位置
                arr[n] = arr[i];
                arr[i] = min;
            }
        }
    }
}
