package com.qq.stack;

import java.util.Scanner;

/**
 * 栈
 * <br>
 * 1) 栈的英文为(stack)
 * <br>
 * 2) 栈是一个先入后出(FILO-First In Last Out)的有序列表。
 * <br>
 * 3) 栈(stack)是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。允许插入和删除的
 * 一端，为变化的一端，称为栈顶(Top)，另一端为固定的一端，称为栈底(Bottom)。
 * <br>
 * 4) 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元
 * 素最先删除，最先放入的元素最后删除
 * <br>
 * 5) 图解方式说明出栈(pop)和入栈(push)的概念
 * <p>
 * =============应用场景==================
 * <br>
 * 1) 子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以
 * 回到原来的程序中。
 * <br>
 * 2) 处理递归调用：和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆
 * 栈中。
 * <br>
 * 3) 表达式的转换[中缀表达式转后缀表达式]与求值(实际解决)。
 * <br>
 * 4) 二叉树的遍历。
 * <br>
 * 5) 图形的深度优先(depth 一 first)搜索法。
 * <p>
 * ===========练习===============
 * <br>
 * 将老师写的程序改成使用链表来模拟栈
 */
public class ArrayStackDemo {
    /**
     * 用数组模拟栈的使用，由于栈是一种有序列表，当然可以使用数组的结构来储存栈的数据内容，
     * 下面我们就用数组模拟栈的出栈，入栈等操作。
     */
    public static void main(String[] args) {
        // 测试一下 ArrayStack 是否正确
        // 先创建一个 ArrayStack 对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

// 定义一个 ArrayStack 表示栈
class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据就放在该数组
    private int top = -1;// top 表示栈顶，初始化为-1

    public int getTop() {
        return top;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈-push
    public void push(int i) {
        if (isFull()) {
            System.out.println("栈已满，无法添加数据~");
            return;
        }
        stack[++top] = i;
    }

    // 出栈-pop, 将栈顶的数据返回
    public int pop() {
        if (isEmpty())
            throw new RuntimeException("栈为空，无法出栈~");
        return stack[top--];
    }

    // 增加一个方法，可以返回当前栈顶的值, 但是不是真正的 pop
    public int get() {
        if (isEmpty())
            throw new RuntimeException("栈为空，没有头数据~");
        return stack[top];
    }

    // 显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
    public void show() {
        if (isEmpty())
            System.out.println("栈为空，没有数据~");
        for (int i = top; i >= 0; i--)
            System.out.println(stack[i]);
    }

}
