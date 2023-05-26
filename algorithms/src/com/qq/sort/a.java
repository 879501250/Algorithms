package com.qq.sort;

import com.qq.search.BinarySearch;
import javafx.beans.binding.When;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.junit.Test;
import org.w3c.dom.ls.LSException;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class a {
    static HashSet<String> codeSet = new HashSet<>();
    public static void main(String[] args) {
        int[][] nums = {{0,0,0,0},
                        {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {1,1,1,0},
                {0,0,0,0}};
        int[] ints = {1,3,2,4,1};
        //System.out.println(maxIceCream(ints,7));
        //System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }
    @Test
    public void test() throws IOException, InterruptedException {
//        int time = new Random().nextInt(1);
//        time += 0;
//        System.out.println("======================准备倒计时" + time + "秒==========================");
//        while (time > 0) {//循环一次是1s 当循环次数大于输入的数时 跳出循环
//            try {
//                Thread.currentThread().sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            time--;
//            System.out.println("还剩" + time + "秒");
//        }
//        System.out.println("====================计时结束==================");
//        System.out.println(shortestCompletingWord("GrC8950",new String[]{"measure","other","every","base","according","level","meeting","none","marriage","rest"}));
//        System.out.println(convertToBase7(-7));Mon Mar 07 14:50:12 CST 2022
//        Date date = new Date(new Date().getTime()+(2*24*60*60*1000));
//        System.out.println(platesBetweenCandles("***|**|*****|**||**|*",
//                new int[][]{{1, 17}, {4, 5}, {14, 17}, {5, 11}, {15, 16}}));"".contains()
//        System.out.println(reverseStr("abcdefgh"
//                ,3));
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(1);
//        TreeNode node3 = new TreeNode(1);
//        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(5);
//        TreeNode node6 = new TreeNode(1);
//        TreeNode node7 = new TreeNode(4);
//        node1.left=node2;
//        node1.right=node3;
//        node2.left=node4;
//        node2.right=node5;
//        node3.left=node6;
//        node3.right=node7;21,25,27,29,30,31,32,33,31
//        System.out.println(findSecondMinimumValue(node1));
//        System.out.println(1);
//        String test= "edcba";
//        char[] ar = test.toCharArray();
//        Arrays.sort(ar);
//        System.out.println(ar);
//        String sorted = String.valueOf(ar);
//        System.out.println(sorted);
//        String str = "[89,100],[30,43],[92,100],[31,49],[59,76],[60,73],[31,49],[80,99],[48,60],[36,52],[67,82],[96,100],[22,35],[18,32],[9,24],[11,27],[94,100],[12,22],[61,74],[3,20],[14,28],[27,37],[5,20],[1,11],[96,100],[33,44],[90,100],[40,54],[23,35],[18,32],[78,89],[56,66],[83,93],[45,59],[40,59],[94,100],[99,100],[86,96],[43,61],[29,45],[21,36],[13,31],[17,30],[16,30],[80,94],[38,50],[15,30],[62,79],[25,39],[73,85],[39,56],[80,97],[42,57],[32,47],[59,78],[35,53],[56,74],[75,85],[39,54],[63,82]";
//        tran(str,"three.book");
//        listDirectory(new File("D:\\Java\\Tool\\apache-maven-3.6.3\\localRepository"));
        File file = new File("D:\\Java\\Tool\\apache-maven-3.6.3\\localRepository");
        listDirectory(file);
    }

    /**
     * 遍历指定目录下（包括其子目录）的所有文件，并删除以 lastUpdated 结尾的文件
     * @param dir 目录的位置 path
     * @throws IOException
     */
    public static void listDirectory(File dir) throws IOException {
        if (!dir.exists())
            throw new IllegalArgumentException("目录：" + dir + "不存在.");
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir + " 不是目录。");
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory())
                    //递归
                    listDirectory(file);
                else{ // 删除以 lastUpdated 结尾的文件
                    String fileName = file.getName();
                    boolean isLastupdated = fileName.toLowerCase().endsWith("lastupdated");
                    if (isLastupdated){
                        boolean is_delete = file.delete();
                        System.out.println("删除的文件名 => " + file.getName() + "  || 是否删除成功？ ==> " + is_delete);
                    }
                }
            }
        }
    }

    public void tran(String str,String method) {
        StringBuilder builder = new StringBuilder();
        str = str.substring(1, str.length() - 1);
        String[] splits = str.split("],\\[");
        for (String splite : splits) {
            builder.append("System.out.println(").append(method).append("(").append(splite).append("));\n");
        }
        System.out.println(builder.toString());
    }

    // 超级丑数
    // 动态规划
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (primes.length == 1)
            return (int) Math.pow(primes[0], n - 1);
        // ints[i]表示第i+1个丑数
        int[] ints = new int[n];
        // 下标与primes对应，表示目前primes[i]需要乘的丑数为ints[indexs[i]]，因此初始都为0
        int[] indexs = new int[primes.length];
        // 目前除第一个外的primes最小下标
        int min = 1;
        // 第二小
        int temp=primes.length>2?2:1;
        ints[0] = 1;
        for (int i = 1; i < n; i++) {
            if (primes[0] * ints[indexs[0]] < primes[min] * ints[indexs[min]]) {
                if(primes[0] * ints[indexs[0]]!= ints[i-1]) // 防止重复
                    ints[i] = primes[0] * ints[indexs[0]];
                indexs[0]++; // 下标增加
            } else {
                if (primes[min] * ints[indexs[min]] != ints[i - 1]) // 防止重复
                    ints[i] = primes[min] * ints[indexs[min]];
                indexs[min]++; // 下标增加
                // 取新的temp
                if (temp - min == 1) {
                    min = temp;
                    if (temp == primes.length - 1 ||
                            primes[(temp + 1) % primes.length] * ints[indexs[(temp + 1) % primes.length]]
                            >= primes[1] * ints[1])
                        temp = 1;
                    else
                        temp++;
                } else {

                }
            }
        }
        return ints[n - 1];
    }
}
class NumArray {

    public NumArray(int[] nums) {

    }

    public void update(int index, int val) {

    }

    public int sumRange(int left, int right) {
        return 1;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

