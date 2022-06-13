package com.qq.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器*************************************
 * 1) 输入一个表达式，将中缀表达式转化为逆波兰表达式（后缀表达式）
 * 1) 使用栈(Stack), 计算其结果
 * 2) 支持小括号和多位数整数，因为这里我们主要讲的是数据结构，因此计算器进行简化，只支持对整数的计算。
 */
public class ReversePolishMultiCalc {

    public static void main(String[] args) {
        List<String> expreesion = parseSuffixExpreesion("1+-(-(2+3)*4)-5");
        System.out.println(calculate(expreesion));
    }

    //逆波兰计算器
    public static int calculate(List<String> expreesion) {
        int value = 0;
        while (true) {
            for (int i = 0; i < expreesion.size(); i++) {
                if (expreesion.get(i).length() == 1) {
                    char c = expreesion.get(i).charAt(0);
                    if (isOper(c)) {
                        value = cal(Integer.parseInt(expreesion.get(i - 2)),
                                Integer.parseInt(expreesion.get(i - 1)), expreesion.get(i));
                        expreesion.remove(i--);
                        expreesion.remove(i--);
                        expreesion.remove(i);
                        expreesion.add(i, String.valueOf(value));
                        break;
                    }
                }
            }
            if (expreesion.size() == 1)
                break;
        }

        return value;
    }

    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
    //方法：将得到的中缀表达式对应的 string => 后缀表达式对应的 string
    public static List parseSuffixExpreesion(String infix) {
        //定义两个栈
        Stack<String> operStack = new Stack<>(); // 符号栈
        //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        Stack<String> numStack = new Stack<>(); // 储存中间结果的栈 s2
        char[] chs = infix.toCharArray();//将expression转化成字符数组
        String keepNum = ""; //用于拼接 多位数
        List<Boolean> flagList = new ArrayList<>(); //用于储存判断到上一个数字之间的字符串的-是否是奇数
        flagList.add(false); //默认有一个
        ArrayList list = new ArrayList(); //定义转换完成的后缀表达式
        //开始 for 循环的扫描 infix
        for (int i = 0; i < chs.length; i++) {
            if (i == 0 || operStack.isEmpty()) { //如果是第一个字符
                if (keepNum != "") {
                    numStack.push(keepNum);
                    keepNum = "";
                }
                if (isOper(chs[i])) { //如果是运算符号
                    //如果不是+或-或'('号则表达式错误，结束
                    if ((priority(chs[i]) == 1 || chs[i] == ')')) {
                        if (numStack.isEmpty())
                            throw new RuntimeException("表达式错误~");
                        if (priority(chs[i]) == 1)
                            operStack.push(String.valueOf(chs[i]));
                    } else if (chs[i] == '-') { //如果是-，标志flag
                        flagList.add(true);
                        if (!numStack.isEmpty()) //如果数栈不为空，入符号栈
                            operStack.push("+");
                    } else if (chs[i] == '(') { //如果是(，入栈,添加flag标志
                        operStack.push("(");
                        flagList.add(false);
                    } else if (!numStack.isEmpty()) //如果数栈不为空，入符号栈
                        operStack.push(String.valueOf(chs[i]));
                } else //如果是数字进行拼接数字，后续加入数栈
                    keepNum += chs[i];
                //进行第二次遍历
                continue;
            }
            //判断是否是运算符
            if (isOper(chs[i])) { //是运算符
                if (keepNum != "") { //如果之前的是数字，就代表这是数字后的第一个运算符
                    numStack.push(keepNum);//将之前的数字加入数栈
                    keepNum = ""; //入栈后清空数字
                    flagList.remove(flagList.size() - 1);
                    flagList.add(false); //刷新标志
                } else { //如果之前的字符是符号
                    if (chs[i - 1] == ')') { //如果前面是)，插入运算符
                        if (chs[i] == '-') { //如果是-，修改flag
                            flagList.add(!flagList.remove(flagList.size() - 1));
                            operStack.push("+");
                        } else
                            operStack.push(String.valueOf(chs[i]));
                        continue;
                    } else if (priority(chs[i]) == 1)  // 当前符号是*或/，前面不能出现符号
                        throw new RuntimeException("表达式错误~");
                    else if (chs[i] == ')') { //如果当前符号是)
                        if (chs[i - 1] != ')') //如果前面的符号不是)，报错
                            throw new RuntimeException("表达式错误~");
                        //依次弹出符号栈顶的符号，压入数栈，直至遇到(
                        while (operStack.peek() != "(")
                            numStack.push(operStack.pop());
                        operStack.pop(); //弹出(
                        //清空flag
                        flagList.remove(flagList.size() - 1);
                        flagList.remove(flagList.size() - 1);
                        flagList.add(false);
                    } else { //如果之前和现在的运算符都是+或-或(
                        if (chs[i] == '-') //如果是-，修改flag
                            flagList.add(!flagList.remove(flagList.size() - 1));
                        if (chs[i] == '(') { //如果是(，添加flag标志，入栈
                            operStack.push("(");
                            flagList.add(false);
                        }
                        continue;
                    }
                }
                //判断当前的符号栈是否为空，且之前是数字时
                if (!operStack.isEmpty()) { //不为空则比较符号优先级
                    if (operStack.peek() == "(") { //如果之前的符号是(
                        if (chs[i - 1] == '(') { //判断是不是两个(连起来
                            if (chs[i] == '-') //如果是-，修改flag
                                flagList.add(!flagList.remove(flagList.size() - 1));
                            else if (chs[i] == '(') { //如果是(，添加flag标志，入栈
                                operStack.push("(");
                                flagList.add(false);
                            } else if (chs[i] == ')') // 出栈(
                                operStack.pop();
                            else if (chs[i] != '+') // 如果是*或/，报错
                                throw new RuntimeException("表达式错误~");
                        } else { //如果不是两个(连起来，说明中间有数字相隔
                            if (chs[i] == '-') { //如果是-，修改flag
                                flagList.add(!flagList.remove(flagList.size()));
                                //入栈符号，如果是-，后面的数字取相反数，使用不管+还是-都入栈+
                                operStack.push("+");
                            } else if (chs[i] == ')') { //如果第一个运算符是)，报错
                                operStack.pop(); // 出栈前面的(
                            } else //不是-，则直接入栈
                                operStack.push(String.valueOf(chs[i]));
                        }
                    } else if (chs[i] == ')') { //如果当前的符号是)
                        //依次弹出符号栈顶的符号，压入数栈，直至遇到(
                        while (operStack.peek() != "(")
                            numStack.push(operStack.pop());
                        operStack.pop(); //弹出(
                        //清空flag
                        flagList.remove(flagList.size() - 1);
                        flagList.remove(flagList.size() - 1);
                        flagList.add(false);
                    } else {
                        while (true) { //一直比较新运算符的优先级
                            if (operStack.isEmpty()) {
                                if (chs[i] == '-') {
                                    flagList.remove(flagList.size() - 1);
                                    flagList.add(true);
                                    operStack.push("+");
                                } else
                                    operStack.push(String.valueOf(chs[i]));
                                break;
                            }
                            // 之前的运算符不是(，进行优先级比较
                            if (priority(operStack.peek().charAt(0)) < priority(chs[i])) {
                                //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                                operStack.push(String.valueOf(chs[i]));
                                break; //不用比较了
                            } else //否则要先将符号栈顶的符号压入数栈，再继续循环
                                numStack.push(operStack.pop());
                        }
                    }
                } else { //若为空说明是第一个运算符
                    if (chs[i] == '-') { //如果是-，修改flag
                        flagList.add(!flagList.remove(flagList.size()));
                        //入栈符号，如果是-，后面的数字取相反数，使用不管+还是-都入栈+，直接进行下次遍历
                        operStack.push("+");
                    } else if (chs[i] == ')') { //如果第一个运算符是)，报错
                        throw new RuntimeException("运算表达式错误~");
                    } else //不是-，则直接入栈
                        operStack.push(String.valueOf(chs[i]));
                }
            } else { //是数字
                //判断前面是否还是数字
                if (chs[i - 1] < 48 || chs[i - 1] > 57) { //之前不是数字，要判断符号
                    if (chs[i - 1] == 48) //如果第一个数是0，报错
                        throw new RuntimeException("表达式错误~");
                    boolean sign = false;
                    for (boolean flag : flagList) { //遍历之前所有的-，确定接下来数字的正负
                        if (flag == true)
                            sign = !sign;
                    }
                    if (sign) //如果前面有奇数个-，这个数字要取相反数
                        keepNum += "-";
                }
                keepNum += chs[i];
            }
        }
        //还剩最后一个数字keepNum，将它入数栈
        numStack.push(keepNum);
        //先将符号栈清空，直接插到字符串后面
        while (!operStack.isEmpty())
            list.add(operStack.pop());
        //再将数栈清空，插到字符串前面
        while (!numStack.isEmpty())
            list.add(0, numStack.pop());
        return list;
    }

    //判断是不是一个运算符
    public static boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/' || val == '(' || val == ')';
    }

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public static int priority(char oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else if (oper == '(' || oper == ')') {
            return 0;
        } else {
            throw new RuntimeException("表达式错误~");
            //return -1; // 假定目前的表达式只有 +, - , * , /
        }
    }

    //计算方法
    public static int cal(int num1, int num2, String oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case "+":
                res = num1 + num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                res = num2 / num1;// 注意顺序
                break;
            default:
                break;
        }
        return res;
    }
}
