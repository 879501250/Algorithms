package com.qq.sort;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 * 基数排序：O(n*k)，k指桶的个数
 * ---------->从个位开始遍历，将所有数字按照当前遍历位数的数值放进0~9的桶里，再依次
 * ---------->拿出，进行下次遍历
 * 1) 基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）
 * 或 bin sort，顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
 * 2) 基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
 * 3) 基数排序(Radix Sort)是桶排序的扩展
 * 4) 基数排序是 1887 年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个
 * 位数分别比较。
 * ================================
 * 说明:
 * 1) 基数排序是对传统桶排序的扩展，速度很快.
 * 2) 基数排序是经典的空间换时间的方式，占用内存很大, 当对海量数据排序时，容易造成 OutOfMemoryError 。
 * 3) 基数排序时稳定的。[注:假定在待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序，这些
 * 记录的相对次序保持不变，即在原序列中，r[i]=r[j]，且 r[i]在 r[j]之前，而在排序后的序列中，
 * r[i]仍在 r[j]之前，则称这种排序算法是稳定的；否则称为不稳定的]
 * 4) 有负数的数组，我们不用基数排序来进行排序, 如果要支持负数，
 * 参考: https://code.i-harness.com/zh-CN/q/e98fa9
 */
public class RadixSort {

    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214};
//        80000000 * 11 * 4 / 1024 / 1024 / 1024 =3.3G
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        System.out.println("排序前的时间是=" + data1.getTime());
        //测试桶排序 -> 90
        radixSort(arr);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("基数排序后 " + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        int maxLength = 1;
        boolean flag = false;
        List<List> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            lists.add(new ArrayList<Integer>());
        Bubble bubbles = new Bubble(lists); //创建一个有10个桶的桶
        /**
         * @parm i 当前遍历次数-1
         * @parm n 当前遍历的位数
         */
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                if (!flag && arr[j] > maxLength) //通过第一次遍历获取此数组中最大的数
                    maxLength = arr[j];
                bubbles.setBucket(arr[j], n);
            }
            bubbles.cleanBucket(arr);
            if (i == 0) { //获取最大数的位数，以后不用再获取了
                maxLength = (maxLength + "").length();
                flag = true;
            }
        }
    }

}

class Bubble {
    private List<List> bubbles;

    public List<List> getBubbles() {
        return bubbles;
    }

    /**
     * 创建桶
     *
     * @param bubbles 创建的桶
     */
    public Bubble(List<List> bubbles) {
        this.bubbles = bubbles;
    }

    /**
     * 清空桶
     *
     * @param arr 数据要放的数组
     */
    public void cleanBucket(int[] arr) {
        int sum = 0;
        for (List<Integer> bubble : bubbles) { //遍历每个桶
            for (int i : bubble) {
                arr[sum] = i;
                sum++;
            }
            bubble.clear();
        }
    }

    /**
     * 为桶放数据
     *
     * @param i 要放的数据
     * @param n 要判断的位数
     */
    public void setBucket(int i, int n) {
        bubbles.get(i / n % 10).add(i);
    }
}
