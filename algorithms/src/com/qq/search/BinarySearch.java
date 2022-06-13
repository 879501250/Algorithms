package com.qq.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 注意：使用二分查找的前提是 该数组是有序的.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        BinarySearchNoRecur(arr, 0, arr.length - 1, 1);
        binarySearch(arr, 0, arr.length - 1, 1);
    }

    /**
     * 二分查找算法（非递归法）
     * @param arr 查询的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查询的数据
     */
    public static void BinarySearchNoRecur(int[] arr,int left, int right,int findVal){
        if(arr[0]<=findVal&&arr[arr.length-1]>=findVal){
            int mid=(left+right)/2;
            while (arr[mid]!=findVal){
                if(arr[mid]>findVal)
                    right=mid-1;
                else
                    left=mid+1;
                mid=(left+right)/2;
            }
            // 如果找到了，查看左右是否存在重复的数据
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
        }else
            System.out.println("该数组不存在此数据~");
    }

    /**
     * 二分查找算法（递归法）
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static void binarySearch(int[] arr, int left, int right, int findVal) {
        if (arr[left] > findVal || arr[right] < findVal) {
            System.out.println("该数组不存在此数据~");
            return;
        }
        int mid = (left + right) / 2; // 查找中间值
        if (arr[mid] > findVal) // 遍历左边数组
            binarySearch(arr, left, mid - 1, findVal);
        else if (arr[mid] < findVal) // 遍历右边数组
            binarySearch(arr, mid + 1, right, findVal);
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
