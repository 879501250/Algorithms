package com.qq.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插值查找算法
 * 注意：使用二分查找的前提是 该数组是有序的.
 * 1) 插值查找原理介绍:
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找。
 * 2) 将折半查找中的求 mid 索引的公式 , low 表示左边索引 left, high 表示右边索引 right.
 * key 就是前面我们讲的 findVal
 * 3) int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]) ;
 * 对应前面的代码公式：
 * int mid=left+(right – left)*(findVal – arr[left])/(arr[right] – arr[left])
 * ============================
 * 注意事项：
 * 1) 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
 * 2) 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        insertValueSearch(arr, 0, arr.length - 1, 1000);
//        int index = binarySearch(arr, 0, arr.length, 1);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插值查找算法
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 查找值
     */
    public static void insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (arr[left] > findVal || arr[right] < findVal) { // 判断是否在数组中
            System.out.println("该数组不存在此数据~");
            return;
        }
        // 利用公式查找中间值
        int mid = (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left) + left;
        if (arr[mid] > findVal) // 遍历左边数组
            insertValueSearch(arr, left, mid - 1, findVal);
        else if (arr[mid] < findVal) // 遍历右边数组
            insertValueSearch(arr, mid + 1, right, findVal);
        else { // 如果找到了，查看左右是否存在重复的数据
            List<Integer> list = new ArrayList<>();
            int i = mid - 1;
            while (i >= 0) {
                if (arr[i] == findVal) {
                    list.add(i);
                    i--;
                } else
                    break;
            }
            list.add(mid);
            i = mid + 1;
            while (i < arr.length) {
                if (arr[i] == findVal) {
                    list.add(i);
                    i++;
                } else
                    break;
            }
            System.out.println("该数据的下标位置为" + list.toString());
        }
    }
}
