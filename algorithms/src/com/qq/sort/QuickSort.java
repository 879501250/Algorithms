package com.qq.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序：O(n log n)
 * <br>
 * ---------->先随便找一个基数，一次排序后的结果是，这个基数的左边都比它小，右边都比他大
 * <br>
 * ---------->分别对基数的左右数组进行递归
 * <br>
 * 快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两
 * 部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排
 * 序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 */
public class QuickSort {
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
        //测试快速排序 -> 70
        quickSort(arr, 0, arr.length - 1);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param left  左边界下标
     * @param right 右边界下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，作为交换时使用
        //while 循环的目的是让比 pivot 值小放到左边
        //比 pivot 值大放到右边
        while (l < r) {
            //在 pivot 的左边一直找,找到大于等于 pivot 值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在 pivot 的右边一直找,找到小于等于 pivot 值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果 l >= r 说明 pivot 的左右两的值，已经按照左边全部是
            //小于等于 pivot 值，右边全部是大于等于 pivot 值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个 arr[r] == pivot 值 相等 l++， 后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果 l == r, 必须 l++, r--, 否则为出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if(right > l) {
            quickSort(arr, l, right);
        }
        /**
         * 自己写的，可以排序，但一旦数据大就有问题
         */
        // 默认取数组中间的数为基数
//        int n = (left + right) / 2;
//        int i = left; // 左下标
//        int j = right; // 右下标
//        if (i >= j)
//            return;
//        while (i < j) {
//            if (i == n) { // 当左下标和基数下标一样时，只需遍历右边即可
//                for (; j > n; j--) { // 遍历右边，查找是否存在小于基数的数
//                    if (arr[j] < arr[n]) { // 如果存在小于基数的数，直接与基数交换
//                        int temp = arr[n];
//                        arr[n] = arr[j];
//                        arr[j] = temp;
//                        n = j; // 改变基数下标
//                        break;
//                    }
//                }
//            }
//            if (arr[i] > arr[n]) { // 如果左边存在一个大于基数的数
//                if (j != n) { // 如果右下标还未到达基数的位置
//                    for (; j > n; j--) { // 遍历右边，查找是否存在小于基数的数
//                        if (arr[j] < arr[n]) { // 如果存在小于基数的数，与右边大于基数的数交换
//                            int temp = arr[j];
//                            arr[j] = arr[i];
//                            arr[i] = temp;
//                            break;
//                        }
//                    }
//                    if (n == j) { // 说明右边没有比基数大的数，让基数和左边的数交换
//                        int temp = arr[n];
//                        arr[n] = arr[i];
//                        arr[i] = temp;
//                        n = i; // 改变基数下标
//                    }
//                }
//                continue;
//            }
//            i++;
//        }
//        quickSort(arr, left, n - 1); // 遍历左边数组
//        quickSort(arr, n + 1, right); // 遍历左边数组
    }
}
