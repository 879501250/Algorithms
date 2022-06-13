package com.qq.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序：O(n log n)
 * ---------->先将数组变换成一个大顶堆，取出最大的数即根节点放在数组最后，对剩下的数组再进行遍历
 * 1) 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复
 * 杂度均为 O(nlogn)，它也是不稳定排序。
 * 2) 堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆, 注意 : 没有
 * 要求结点的左孩子的值和右孩子的值的大小关系。
 * 3) 每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆
 * 4) 一般升序采用大顶堆，降序采用小顶堆
 * =====================
 * 堆排序的基本思想是：
 * 1) 将待排序序列构造成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * 2) 此时，整个序列的最大值就是堆顶的根节点。
 * 3) 将其与末尾元素进行交换，此时末尾就为最大值。
 * 4) 然后将剩余 n-1 个元素重新构造成一个堆，这样会得到 n 个元素的次小值。如此反复执行，便能得到一个有序
 * 序列了。
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        //int arr[] = {4, 6, 8, 5, 9};
        // 创建要给 80000 个的随机的数组
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000); // 生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        System.out.println("排序前的时间是=" + data1.getTime());
        //测试堆排序 -> 20
        heapSort(arr);
        Date data2 = new Date();
        System.out.println("排序后的时间是=" + data2.getTime());
        System.out.println(data2.getTime() - data1.getTime());
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        //从最后一个非叶子节点开始向上循环，直至根节点（自底向上，自右向左）
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /**
         * 将剩下length-1个元素也按照从小到大的次序排序
         * 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换
         * 步骤，直到整个序列有序。
         */
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 因为只变换了根节点，其余子树都是满足堆的要求，所以只要对根节点进行循环调整就行了
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组(二叉树)的一部分调整成一个大顶堆
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例 int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param lenght 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int lenght) {
        if (i * 2 + 1 >= lenght) // 如果当前节点不存在子节点
            return;
        int temp = arr[i];
        /**
         * 每次循环寻找父节点的左右节点中最大的数
         * 若比arr[i]大，则赋值给父节点，将较大的子树作为下一个父节点进行下一次循环
         * 若比父节点小，跳出循环
         */
        for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            if (k + 1 < lenght && arr[k + 1] > arr[k])  // 将k指向左右节点中较大的下标
                k++;
            if (arr[k] > temp) { // 如果较大的数大于顶部元素
                arr[i] = arr[k];
                i = k; // 将i指向较大元素的下标，当作下次循环的父节点
            } else
                break;
        }
        arr[i] = temp;
    }
}
