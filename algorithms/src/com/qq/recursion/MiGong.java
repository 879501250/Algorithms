package com.qq.recursion;

import java.util.Scanner;

/**
 * 使用递归回溯来给小球找路
 * 说明
 * 1. map 表示地图
 * 2. i,j 表示从地图的哪个位置开始出发 (1,1)
 * 3. 如果小球能到 map[n][n] 位置，则说明通路找到.
 * 4. 约定： 当 map[i][j] 为 0 表示该点没有走过 当为 1 表示墙 ； 2 表示通路可以走 ； 3 表示该点已经
 * 走过，但是走不通
 * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
 * ==============================================
 * 对迷宫问题的讨论
 * 1) 小球得到的路径，和程序员设置的找路策略有关即：找路的上下左右的顺序相关
 * 2) 再得到小球路径时，可以先使用(下右上左)，再改成(上右下左)，看看路径是不是有变化
 * 3) 测试回溯现象
 * 4) 思考: 如何求出最短路径? 思路-》将深度优先算法转化成广度优先算法
 */
public class MiGong {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] map = newMap(n);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++)
                System.out.print(map[i][j] + "   ");
            System.out.println("");
        }
        System.out.println("==========解迷==========");
        setWay(map, 1, 1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++)
                System.out.print(map[i][j] + "   ");
            System.out.println("");
        }
    }

    //走迷宫

    /**
     * @param map 表示地图
     * @param x   从哪个位置开始找
     * @param y
     * @return 如果找到通路，就返回 true, 否则返回 false
     */
    public static boolean setWay(int[][] map, int x, int y) {
        if (map[x][y] == 1) //判断接下来要走的地方是不是墙
            return false;
        else if (map[x][y] == 2) { //判断接下来要走的地方是不是已经走过的
            map[x][y] = 3;
            return false;
        } else //标记接下来的路为2
            map[x][y] = 2;
        //到达终点
        if (x == map.length - 2 && y == map.length - 2)
            return true;
        // 按照下、右、上、左的顺序依次遍历
        if (setWay(map, x + 1, y))
            return true;
        else if (setWay(map, x, y + 1))
            return true;
        else if (setWay(map, x - 1, y + 1))
            return true;
        else if (setWay(map, x, y - 1))
            return true;
        //此路不通
        map[x][y] = 3;
        return false;
    }

    // 随机创建一个n*n的迷宫，完全随机不一定有通路
    public static int[][] newMap(int n) {
        int[][] map = new int[n + 2][n + 2];
        //为迷宫的四面加上墙
        for (int i = 0; i < n + 2; i++) {
            map[0][i] = 1;
            map[n + 1][i] = 1;
            map[i][0] = 1;
            map[i][n + 1] = 1;
        }
        //为迷宫内部随机生成墙
        for (int i = 0; i <= (int) (n * 1.5); i++) {
            //随机生成[1,n+2)的整数
            int x = (int) (Math.random() * (n + 1) + 1);
            int y = (int) (Math.random() * (n + 1) + 1);
            map[x][y] = 1;
        }
        //保证迷宫的起点和终点不是墙
        map[1][1] = 0;
        map[map.length - 2][map.length - 2] = 0;
        return map;
    }
}
