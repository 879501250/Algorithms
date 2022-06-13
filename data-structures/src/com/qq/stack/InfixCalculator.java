package com.qq.stack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 栈实现综合计算器(中缀表达式)
 */
public class InfixCalculator {
    public static void main(String[] args) {
        //根据前面老师思路，完成表达式的运算，如何处理多位数的问题？7*2*2-5+1-5+3-4
        String expression = "--++70*2*--2-+50+10-50+30-40/2*5"; // 120
        //创建两个栈，数栈，一个符号栈，这里的栈是之前创建的ArrayStackDemo里的ArrayStack
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        infixcalculator(expression, numStack, operStack); //一次遍历
        StringBuffer buffer1 = new StringBuffer(); //储存一次遍历后，后面全是*或/的表达式
        StringBuffer buffer2 = new StringBuffer(); //储存剩余的表达式
        int n = operStack.getTop();
        //为buffer1赋值
        for (int i = 0; i <= n; i++) {
            if (priority(operStack.get()) == 1) {
                buffer1.insert(0, numStack.pop());
                buffer1.insert(0, (char) operStack.pop());
                buffer1.insert(0, numStack.pop());
            }
        }
        if (buffer1.length() != 0) {
            n = operStack.getTop();
            for (int i = 0; i <= n; i++) {
                buffer2.insert(0, (char) operStack.pop());
                buffer2.insert(0, numStack.pop());
            }
            infixcalculator(buffer1.toString(), numStack, operStack); //计算buffer1表达式
            //将计算后的结果添加到buffer2表达式后面
            int value = cal(numStack.pop(), numStack.pop(), operStack.pop());
            if (buffer2.length() == 0) { //若为空直接打印
                System.out.println(value);
                return;
            }
            buffer2.append(value);
        }
        n = operStack.getTop();
        for (int i = 0; i <= n; i++) {
            buffer2.insert(0, numStack.pop());
            buffer2.insert(0, (char) operStack.pop());
            buffer2.insert(0, numStack.pop());
        }
        infixcalculator(buffer2.toString(), numStack, operStack);
        int value = cal(numStack.pop(), numStack.pop(), operStack.pop());
        System.out.println(value);
    }

    /**
     * 中缀计算器核心代码
     *
     * @param expression 表示即将运算的表达式
     * @param numStack   表示数栈（放数字）
     * @param operStack  表示符号栈（放运算符号）
     */
    public static void infixcalculator(String expression, ArrayStack numStack, ArrayStack operStack) {
        char[] chs = expression.toCharArray();//将expression转化成字符数组
        String keepNum = ""; //用于拼接 多位数
        boolean flag = false; //用于判断到上一个数字之间的字符串的-是否是奇数
        //开始 for 循环的扫描 expression
        for (int i = 0; i < chs.length; i++) {
            if (i == 0) { //如果是第一个字符
                if (isOper(chs[i])) { //如果是运算符号
                    if (priority(chs[i]) == 1) //如果不是+或-号则表达式错误，结束
                        throw new RuntimeException("表达式错误~");
                    if (chs[i] == '-') { //如果是-，标志flag
                        flag = true;
                        operStack.push('+');
                        continue;
                    }
                    operStack.push(chs[i]);
                } else //如果是数字进行拼接数字，后续加入数栈
                    keepNum += chs[i];
                //进行第二次遍历
                continue;
            }
            //判断是否是运算符
            if (isOper(chs[i])) { //是运算符
                if (keepNum != "") { //如果之前的是数字，就代表这是数字后的第一个运算符
                    numStack.push(Integer.parseInt(keepNum));//将之前的数字加入数栈
                    keepNum = ""; //入栈后清空数字
                    flag=false; //刷新标志
                } else { //如果之前的字符是符号
                    if (priority(chs[i]) == 1) //如果当前运算符不是+或-号则表达式错误，结束
                        throw new RuntimeException("表达式错误~");
                    else { //如果当前运算符是+或-
                        if (chs[i] == '-') //如果是-，修改flag
                            flag = !flag;
                        continue;
                    }
                }
                if (numStack.getTop() == 0 && operStack.getTop() == 0)
                    operStack.pop();
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) { //不为空则比较符号优先级，进行计算
                    if (priority(operStack.get()) < priority(chs[i]))
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(chs[i]);
                    else { //否则要先对之前的运算符进行运算
                        //把运算结果入数栈
                        int x = cal(numStack.pop(), numStack.pop(), operStack.pop());
                        numStack.push(x);
                        if (chs[i] == '-') { //如果是-，修改flag，再入栈
                            flag = true;
                            operStack.push('+');
                            continue;
                        }
                        operStack.push(chs[i]); //然后将当前的操作符入符号栈
                    }
                } else { //若为空说明是第一个运算符
                    if (chs[i] == '-') { //如果是-，修改flag
                        flag = !flag;
                        //入栈符号，如果是-，后面的数字取相反数，使用不管+还是-都入栈+，直接进行下次遍历
                        operStack.push('+');
                    } else //不是-，则直接入栈
                        operStack.push(chs[i]);
                }
            } else { //是数字
                //判断之前是否存在奇数个-
                if (flag) { //如果存在，则说明是前面是符号，这是第一个数字，则取相反数并直接进行添加
                    int n = -(chs[i] - '0'); //获取这个数字字符本身的字并取相反数
                    keepNum += n; //添加这个数字
                    flag = !flag;
                } else //不管是不是第一个数字直接拼串都对
                    keepNum += chs[i];
            }
        }
        //还剩最后一个数字keepNum，将它入数栈
        numStack.push(Integer.parseInt(keepNum));
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public static int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            throw new RuntimeException("表达式错误~");
            //return -1; // 假定目前的表达式只有 +, - , * , /
        }
    }

    //判断是不是一个运算符
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public static int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;// 注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}
