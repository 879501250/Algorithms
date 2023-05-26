package com.qq.recursion;

/**
 * 递归-八皇后问题(回溯算法)
 * <br>
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于
 * 1848 年提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、
 * 同一列或同一斜线上，问有多少种摆法(92)。
 */
public class Queue8 {
    static int sum = 0;

    public static void main(String[] args) {
        // 定义棋子的位置
        int[] checkerboard = new int[8];
        check(checkerboard, 0);
        System.out.println(sum);
    }

    public static void check(int[] checkerboard, int n) {
        // 如果已经遍历完，回溯
        if (n == checkerboard.length) {
            sum++;
            return;
        } else // 下棋
            judge(checkerboard, n);
    }

    public static void judge(int[] checkerboard, int n) {
        for (int i = 0; i < checkerboard.length; i++) {
            // 遍历每个位置
            checkerboard[n] = i;
            boolean flag = false;
            // 判断这个位置是否可以
            for (int j = 0; j < n; j++) {
                // 如果要下的棋子和之前的棋子在同一直线或同一斜线，则不可用
                if (checkerboard[j] == checkerboard[n] || Math.abs(checkerboard[j] - checkerboard[n]) == n - j) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                // 遍历下一个棋子
                check(checkerboard, n + 1);
        }
    }
}
