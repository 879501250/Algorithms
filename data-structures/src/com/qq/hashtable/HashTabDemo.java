package com.qq.hashtable;

import java.util.List;
import java.util.Scanner;

/**
 * 哈希表
 * 散列表（Hash table，也叫哈希表），是根据关键码值(Key value)而直接进行访问的数据结构。也就是说，它通
 * 过把关键码值映射到表中一个位置来访问记录，以加快查找的速度。这个映射函数叫做散列函数，存放记录的数组
 * 叫做散列表。
 */
public class HashTabDemo {
    /**
     * Google 上机题
     * 1) 看一个实际需求，google 公司的一个上机题:
     * 2) 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,住址..),
     * 当输入该员工的 id 时,要求查找到该员工的 所有信息.
     * 3) 要求: 不使用数据库,尽量节省内存,速度越快越好=>哈希表(散列)
     */
    public static void main(String[] args) {
        //创建哈希表
        EmpHashTable hashTab = new EmpHashTable(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入 id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Employee emp = new Employee(id, name);
                    hashTab.addEmp(emp);
                    break;
                case "list":
                    hashTab.showAll();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.queryEmp(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 创建哈希表
class EmpHashTable {
    private EmpLinkedList[] empLinkedListArray; // 哈希表实质是一个数组
    private int size; //表示有多少条链表

    public EmpHashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        /**
         * 坑！一定要初始化每个链表，不然要报空指针异常
         */
        for (int i = 0; i < size; i++)
            this.empLinkedListArray[i] = new EmpLinkedList();
    }

    //编写散列函数, 使用一个简单取模法
    public int hashFun(int no) {
        return no % size;
    }

    // 展示所有员工
    public void showAll() {
        boolean flag = false;
        for (EmpLinkedList empLinkedList : empLinkedListArray)
            if (!empLinkedList.isEmpty()) {
                empLinkedList.showAll();
                flag = true;
            }
        if (!flag)
            System.out.println("散列表暂无数据~");
    }

    // 根据no查询指定员工
    public void queryEmp(int no) {
        empLinkedListArray[hashFun(no)].queryEmp(no);
    }

    // 修改员工
    public void updateEmp(Employee employee) {
        empLinkedListArray[hashFun(employee.getNo())].updateEmp(employee);
    }

    // 根据no删除员工
    public void delEmp(int no) {
        empLinkedListArray[hashFun(no)].delEmp(no);
    }

    // 添加员工，默认添加到最后，（若要按某种规则排序，可以修改）
    public void addEmp(Employee employee) {
        empLinkedListArray[hashFun(employee.getNo())].addEmp(employee);
    }
}

// 创建哈希表中的链表
class EmpLinkedList {
    //头指针，执行第一个 Emp,因此我们这个链表的 head 是直接指向第一个 Emp
    private Employee head;

    // 展示所有员工
    public void showAll() {
        Employee temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    // 根据no查询指定员工
    public void queryEmp(int no) {
        if (isEmpty()) {
            System.out.println("表中无该员工~");
            return;
        }
        Employee temp = head;
        while (temp != null) {
            if (temp.getNo() == no) {
                System.out.println(temp);
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("表中无该员工~");
    }

    // 修改员工
    public void updateEmp(Employee employee) {
        if (isEmpty()) {
            System.out.println("表中无该员工~");
            return;
        }
        Employee temp = head;
        while (temp != null) {
            if (temp.getNo() == employee.getNo()) {
                temp.setName(employee.getName());
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("表中无该员工~");
    }

    // 根据no删除员工
    public void delEmp(int no) {
        if (isEmpty()) {
            System.out.println("表中无该员工~");
            return;
        }
        Employee temp = head;
        if (temp.getNo() == no) {
            head = head.getNext();
            return;
        }
        while (temp.getNext() != null) {
            if (temp.getNext().getNo() == no) {
                temp.setNext(temp.getNext().getNext());
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("表中无该员工~");
    }

    // 添加员工，默认添加到最后，（若要按某种规则排序，可以修改）
    public void addEmp(Employee employee) {
        if (isEmpty())
            head = employee;
        else {
            Employee temp = head;
            while (temp.getNext() != null) {
                if (temp.getNo() == employee.getNo()) {
                    System.out.println("该员工已经存在~");
                    return;
                }
                temp = temp.getNext();
            }
            temp.setNext(employee);
        }
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }
}

// 创建员工节点
class Employee {
    private int no; // 编号（原来排序）
    private String name; // 员工姓名
    private Employee next; // 指向的下一个员工，默认为null

    public Employee(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getNext() {
        return next;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "employee{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
