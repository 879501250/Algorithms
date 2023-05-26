package com.qq.queue;

import java.util.Scanner;

/**
 * 队列
 * <br>
 * 1) 队列是一个有序列表，可以用数组或是链表来实现。
 * <br>
 * 2) 遵循先入先出的原则。即：先存入队列的数据，要先取出。后存入的要后取出
 * <p>
 * 队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 maxSize 是该队
 * 列的最大容量。
 * <br>
 * 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后端的下标，
 * front 会随着数据输出而改变，而 rear 则是随着数据输入而改变
 * <p>
 * 问题分析并优化
 * <br>
 * 1) 目前数组使用一次就不能用， 没有达到复用的效果
 * <br>
 * 2) 将这个数组使用算法，改进成一个环形的队列 取模：%
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("需要创建的队列大小：");
        int n = scanner.nextInt();
        // 根据传入的值创建队列
        ArrayQueue queue;
        if (n > 0)
            queue = new ArrayQueue(n);
        else
            queue = new ArrayQueue();
        char key = ' '; // 接收用户输入
        boolean loop = true;
        while (loop) {
            System.out.println("==========请进行操作操作==========");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.getHead();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}

// 使用数组模拟队列-编写一个 ArrayQueue 类
class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front;   // 队列头
    private int rear;    // 队列尾
    private int[] arry;  // 该数据用于存放数据, 模拟队列

    // 若未传值，默认创建最大容量为10的队列
    ArrayQueue() {
        // 调用有参构造器
        this(10);
    }

    ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arry = new int[maxSize];
        front = -1; // 指向队列头部，分析出 front 是指向队列头的前一个位置
        rear = -1;  // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void add(int i) {
        // 判断队列是否满
        if (!isFull()) {
            // 显示数据并让 rear 后移
            arry[++rear] = i;
            System.out.println("添加成功！");
            return;
        }
        System.out.println("队列已满，无法添加~");
    }

    // 获取队列的数据, 出队列
    public int get() {
        // 判断队列是否空
        if (isEmpty())
            // 因为这个方法有返回值使用需要通过抛出异常，不能直接在控制台打印
            throw new RuntimeException("队列空，不能取数据~");
        return arry[++front];// front 后移
    }

    // 显示队列的头数据， 注意不是取出数据
    public int getHead() {
        if (isEmpty())
            throw new RuntimeException("队列为空，不能取数据~");
        return arry[front + 1];
    }

    // 显示队列的所有数据
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，暂时没有数据~");
            return;
        }
        for (int i = front + 1; i <= rear; i++)
            System.out.println(arry[i]);
    }
}
