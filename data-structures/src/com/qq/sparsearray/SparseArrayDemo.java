package com.qq.sparsearray;

import java.io.File;
import java.util.Scanner;

/**
 * 稀疏数组
 * 1) 记录数组一共有几行几列，有多少个不同的值
 * 2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 *
 * 提高：
 * 1) 在前面的基础上，将稀疏数组保存到磁盘上，比如 map.data
 * 2) 恢复原来的数组时，读取 map.data 进行恢复
 */
public class SparseArrayDemo {
    //根据输入的n创建二维数组，并打印对应的稀疏数组
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入一个n
        int n = scanner.nextInt();
        if (n < 1)
            return;
        // 创建一个原始的二维数组 n * n
        int arry[][] = newArry(n);
        //打印原数组
        System.out.println("==========原数组==========");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arry[i][j] + "\t");
            }
            System.out.print("\n");
        }
        int[][] sparseArrs = arryTranSparse(arry);
        //打印稀疏数组
        System.out.println("==========稀疏数组==========");
        for (int i = 0; i < sparseArrs.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sparseArrs[i][j] + "\t");
            }
            System.out.print("\n");
        }
        int newArry[][] = sparseTranSarry(sparseArrs);
        //打印稀疏数组
        System.out.println("==========转化后的二维数组==========");
        for (int i = 0; i < sparseArrs[0][0]; i++) {
            for (int j = 0; j < sparseArrs[0][1]; j++) {
                System.out.print(newArry[i][j] + "\t");
            }
            System.out.print("\n");
        }
    }

    //将传入的稀疏数组转化为二维数组
    public static int[][] sparseTranSarry(int[][] sparseArrs) {
        //先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int arry[][] = new int[sparseArrs[0][0]][sparseArrs[0][1]];
        //在读取稀疏数组后几行的数据(从第二行开始)，并赋给原始的二维数组
        for (int i = 1; i < sparseArrs.length; i++) {
            arry[sparseArrs[i][0]][sparseArrs[i][1]] = sparseArrs[i][2];
        }
        return arry;
    }

    //将传入的二维数组转化为稀疏数组
    public static int[][] arryTranSparse(int[][] arry) {
        int sum = 0;
        //先遍历二维数组 得到非 0 数据的个数
        for (int i = 0; i < arry.length; i++) {
            for (int j = 0; j < arry.length; j++) {
                if (arry[i][j] != 0)
                    sum++;
            }
        }
        //创建对应的稀疏数组
        int sparseArrs[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        //原数组的行数
        sparseArrs[0][0] = arry.length;
        //原数组的列数
        sparseArrs[0][1] = arry.length;
        //原数组中不为0的位置数量
        sparseArrs[0][2] = sum;
        //遍历二维数组，将非 0 的值存放到 sparseArr 中
        sum = 0;
        for (int i = 0; i < arry.length; i++) {
            for (int j = 0; j < arry.length; j++) {
                if (arry[i][j] != 0) {
                    sum++;
                    sparseArrs[sum][0] = i;
                    sparseArrs[sum][1] = j;
                    sparseArrs[sum][2] = arry[i][j];
                }
            }
        }
        return sparseArrs;
    }

    //根据传入的值n，创建一个n*n的二维数组，默认为0，随机给n给位置赋随机值
    public static int[][] newArry(int n) {
        //int类型的数组，若未赋值，默认为0
        //string为null
        int arry[][] = new int[n][n];
        for (int i = 1; i <= n; i++) {
            //随机生成[0,n)之间的整数：(int)(Math.random()*n)
            int x = (int) (Math.random() * n);
            int y = (int) (Math.random() * n);
            arry[x][y] = i;
        }
        return arry;
    }

}
