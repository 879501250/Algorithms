package com.qq.search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割法)查找
 * <br>
 * 基本介绍:
 * <br>
 * 1) 黄金分割点是指把一条线段分割为两部分，使其中一部分与全长之比等于另一部分与这部分之比。取其前三位
 * 数字的近似值是 0.618。由于按此比例设计的造型十分美丽，因此称为黄金分割，也称为中外比。这是一个神
 * 奇的数字，会带来意向不大的效果。
 * <br>
 * 2) 斐波那契数列 {1, 1, 2, 3, 5, 8, 13, 21, 34, 55 } 发现斐波那契数列的两个相邻数 的比例，
 * 无限接近 黄金分割值0.618
 * <br>
 * ========================
 * <br>
 * 原理:
 * <br>
 * 斐波那契查找原理与前两种相似，仅仅改变了中间结点（mid）的位置，mid 不再是中间或插值得到，而是位
 * 于黄金分割点附近，即 mid=low+F(k-1)-1（F 代表斐波那契数列）
 * <br>
 * ===========================
 * <br>
 * 对 F(k-1)-1 的理解：
 * <br>
 * 1) 由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 。
 * 该式说明：只要顺序表的长度为 F[k]-1，则可以将该表分成长度为 F[k-1]-1 和 F[k-2]-1 的两段
 * <br>
 * 2) 类似的，每一子段也可以用相同的方式分割
 * <br>
 * 3) 但顺序表长度 n 不一定刚好等于 F[k]-1，所以需要将原来的顺序表长度 n 增加至 F[k]-1。
 * 这里的 k 值只要能使得 F[k]-1 恰好大于或等于 n 即可，由以下代码得到,顺序表长度增加后，
 * 新增的位置（从 n+1 到 F[k]-1 位置），都赋为 n 位置的值即可。
 * <br>
 * while(n>fib(k)-1)
 * <br>
 * k++;
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        fibSearch(arr, 1234);
    }

    public static void fibSearch(int[] arr, int findVal) {
        int k = 0; // 表示斐波那契分割数值的下标
        int[] fib = fibonacci();
        while (arr.length > fib[k])
            k++;
        // 因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(arr, fib[k]);
        // 实际上需求使用 a 数组最后的数填充 temp
        // 举例:
        // temp = {1,8, 10, 89, 1000, 1234, 0, 0} => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = arr.length; i < temp.length; i++)
            temp[i] = arr[arr.length - 1];
        if (temp[0] > findVal || temp[temp.length - 1] < findVal) { // 数据不在数组内
            System.out.println("该数组不存在此数据~");
            return;
        }
        // 定义一个斐波那契中点
        int mid = fib[k - 1] - 1;
        while (temp[mid] != findVal) {
            if (temp[mid] > findVal) { // 在左边查找新的斐波那契中点
                /**
                 * 为甚是 k--
                 * 说明
                 * 1. 全部元素 = 前面的元素 + 后边元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                 * 即 在 f[k-1] 的前面继续查找 k--
                 * 即下次循环 mid = f[k-1-1]-1
                 */
                k--;
                mid = fib[k - 1];
            } else {// 在右边
                /**
                 * 为什么是 k -=2
                 * 说明
                 * 1. 全部元素 = 前面的元素 + 后边元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                 * 4. 即在 f[k-2] 的前面进行查找 k -=2
                 * 5. 即下次循环 mid = f[k - 1 - 2] - 1
                 */
                k -= 2;
                mid += fib[k - 1];
            }
        }
        if (mid >= arr.length) // 如果找到的位置在temp填充的新数据处，则是原数组最后一个位置
            System.out.println("该数据的下标位置为" + (arr.length - 1));
        else
            System.out.println("该数据的下标位置为" + mid);
    }

    // 返回一个储存前20个斐波那契数的数组
    public static int[] fibonacci() {
        int[] fib = new int[20];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib;
    }
}
