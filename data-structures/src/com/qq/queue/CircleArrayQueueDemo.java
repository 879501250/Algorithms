package com.qq.queue;

import java.util.Scanner;

/**
 * 环形队列
 * <br>
 * 对前面的数组模拟队列的优化，充分利用数组. 因此将数组看做是一个环形的。(通过取模的方式来实现即可)
 * <br>
 * 1) 尾索引的下一个为头索引时表示队列满，即将队列容量空出一个作为约定,这个在做判断队列满的
 * 时候需要注意 (rear + 1) % maxSize == front [满]
 * <br>
 * 2) rear == front [空]
 * <p>
 * ******************
 * <br>
 * 取头数据时将 front 后移, 这里必须考虑取模（没考虑到）  front = (front + 1) % maxSize;
 * <br>
 * 添加数据时将 rear 后移, 这里必须考虑取模（没考虑到）   rear = (rear + 1) % maxSize;
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("需要创建的队列大小：");
        int n = scanner.nextInt();
        // 根据传入的值创建队列
        CircleArrayQueue queue;
        if (n > 0)
            queue = new CircleArrayQueue(n);
        else
            queue = new CircleArrayQueue();
        char key = ' '; // 接收用户输入
        boolean loop = true;
        while (loop) {
            System.out.println("==========请进行操作操作==========");
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            System.out.println("v(valid): 获取队列中的有效数据个数");
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
                case 'v': // 退出
                    System.out.println(queue.valid());
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

// 使用数组模拟队列-编写一个 ArrayQueue 类
class CircleArrayQueue {
    private int maxSize; // 表示数组的最大容量
    // front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
    // front 的初始值 = 0
    private int front;
    // rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定
    // rear 的初始值 = 0
    private int rear;
    private int[] arry;  // 该数据用于存放数据, 模拟队列

    // 若未传值，默认创建最大容量为10的队列
    CircleArrayQueue() {
        // 调用有参构造器
        this(10);
    }

    CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arry = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        //return (rear + 1) % maxSize == front;
        return (rear - front + 1 + maxSize) % maxSize == 0;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void add(int i) {
        // 判断队列是否满
        if (!isFull()) {
            // 直接将数据加入
            arry[rear] = i;
            // 将 rear 后移, 这里必须考虑取模（没考虑到）
            rear = (rear + 1) % maxSize;
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
        int value = arry[front];
        // 将 front 后移, 这里必须考虑取模（没考虑到）
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的头数据， 注意不是取出数据
    public int getHead() {
        if (isEmpty())
            throw new RuntimeException("队列为空，不能取数据~");
        return arry[front];
    }

    // 显示队列的所有数据
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，暂时没有数据~");
            return;
        }
        // 遍历从front到rear的数据
        // 若rear已经一轮过去，当rear<front时
        // 先遍历从front到maxSize的数据，再遍历从0到rear的数据
        int n = rear > front ? rear : maxSize;
        for (int i = front; i < n; i++)
            System.out.println(arry[i]);
        if (rear < front) {
            for (int i = 0; i < rear; i++)
                System.out.println(arry[i]);
        }
        /**
         * 思路：从 front 开始遍历，遍历多少个元素
         * 比我的更简单
         * for (int i = front; i < front + valid() ; i++) {
         * System.out.printf("arry[%d]=%d\n", i % maxSize, arry[i % maxSize]);
         */
    }

    // 获取队列中的有效数据个数
    public int valid() {
        return (rear - front + maxSize) % maxSize;
    }
}
