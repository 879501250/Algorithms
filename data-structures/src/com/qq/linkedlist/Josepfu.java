package com.qq.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 单向环形链
 * <br>
 * ======================
 * <br>
 * Josephu(约瑟夫、约瑟夫环) 问题
 * <br>
 * Josephu 问题为：设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数
 * 到 m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由
 * 此产生一个出队编号的序列。
 * <p>
 * 提示：用一个不带头结点的循环链表来处理 Josephu 问题：先构成一个有 n 个结点的单循环链表，然后由 k 结
 * 点起从 1 开始计数，计到 m 时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从 1 开始计数，直
 * 到最后一个结点从链表中删除算法结束。
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList(5);
        List<Boy> boys = list.show();
        for (Boy boy : boys)
            System.out.println(boy);
        System.out.println("===========出圈顺序为===========");
        list.countBoy(1, 2, 5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    private Boy first;

    // 根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (countNum < 1) {
            System.out.println("请输入合适的次数来数~");
            return;
        }
        if (startNo > nums) {
            System.out.println("请检查小孩个数~");
            return;
        }
        // 将temp定位到startNo之前一个小孩的位置
        Boy temp = first;
        // 当startNo大于1时，即开始小孩至少在first小孩后1个位置时，使temp等于开始小孩上一个小孩
        if (startNo > 1) {
            for (int i = 2; i < startNo; i++)
                temp = temp.getNext();
        }
        // 否则当startNo等于1，即开始小孩就是first小孩时，使temp等于开始小孩上一个小孩
        else {
            while (temp.getNext() != first)
                temp = temp.getNext();
        }
        // 依次让小孩出列
        while (temp != temp.getNext()) {
            // 开始报数，使temp等于要出列之前一个的小孩
            for (int i = 2; i <= countNum; i++)
                temp = temp.getNext();
            // 打印出列小孩编号
            System.out.print(temp.getNext().getNo() + "=>");
            // 将小孩出列
            temp.setNext(temp.getNext().getNext());
        }
        // 打印最后一个小孩
        System.out.print(temp.getNo());
    }

    CircleSingleLinkedList(int num) {
        if (num > 0) {
            // 辅助指针，帮助构建环形链表，每次指向最后一个小孩
            Boy temp = null;
            for (int i = 1; i <= num; i++) {
                // 当创建第一个小孩时，是为first赋值
                if (i == 1) {
                    first = new Boy(i);
                    // 指向第一个小孩
                    temp = first;
                    continue;
                }
                // 第一次创建时已经指定过temp了，所以不用管
                if (temp.getNext() != null)
                    // 指向下一个小孩
                    temp = temp.getNext();
                // 在末尾添加小孩
                temp.setNext(new Boy(i));
            }
            // 头尾相连，指向第一个小孩
            temp.getNext().setNext(first);
        }
    }

    // 遍历全部小孩
    public List<Boy> show() {
        List<Boy> boys = new ArrayList<>();
        Boy temp = first;
        do {
            boys.add(temp);
            temp = temp.getNext();
        } while (temp != first);
        return boys;
    }
}

// 创建一个 Boy 类，表示一个节点
class Boy {
    private int no;   // 编号
    private Boy next; // 指向下一个节点,默认 null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
