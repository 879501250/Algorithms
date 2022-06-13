package com.qq.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序：O(n log n)
 * ---------->将原数组拆为n组小数组分别排序，小数组内部相邻元素都是原数组内下标相差一个增量的元素，
 * ---------->分别排序完后，减小增量，重复排序，最后一趟排序增量为1
 * 希尔排序是希尔（Donald Shell）于 1959 年提出的一种排序算法。希尔排序也是一种插入排序，它是简单插入
 * 排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
 * <p>
 * 因为当使用插入排序需要插入的数是较小的数时，与前面的数字依次比较的次数明显增多，
 * 对效率有影响，所以要设置增量
 * ===========================
 * 基本思想
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含
 * 的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止
 */
public class ShellSort {
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
        //测试插入排序 -> 移动法：12999  位移法：29
        shellSort(arr, arr.length / 2);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param n 增量
     */
    public static void shellSort(int[] arr, int n) {
        for (int i = 0; i + n < arr.length; i++) {
            // 对根据增量分出来的小数组内部进行排序
            // 移动法
//            for (int j = i; j + n < arr.length; j += n) {
//                if (arr[j] > arr[j + n]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + n];
//                    arr[j + n] = temp;
//                }
//            }
            //移位法
            int j = i + n;
            int temp = arr[j];
            for (; j >= n; j -= n) {
                if (temp < arr[j - n])
                    arr[j] = arr[j - n];
                else { // 找到合适的位置
                    arr[j] = temp;
                    break;
                }
            }
            if (j < n)
                arr[j] = temp;
        }
        if (n == 1) // 当这次排序的增量变是1时，结束
            return;
        shellSort(arr, n / 2);
    }
}
