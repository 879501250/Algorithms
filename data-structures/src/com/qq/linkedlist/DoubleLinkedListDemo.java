package com.qq.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 双向链表
 * <br>
 * 管理单向链表的缺点分析:
 * <br>
 * 1) 单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
 * <br>
 * 2) 单向链表不能自我删除，需要靠辅助节点 ，而双向链表，则可以自我删除，所以前面我们单链表删除
 * 时节点，总是找到 temp,temp 是待删除节点的前一个节点(认真体会).
 * <br>
 * 3) 分析了双向链表如何完成遍历，添加，修改和删除的思路
 */
public class DoubleLinkedListDemo {
    // 使用带 head 头的双向链表实现 –水浒英雄排行榜
    public static void main(String[] args) {
        // 测试
        System.out.println("======双向链表的测试======");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        List<HeroNode2> list = new ArrayList<>();
        doubleLinkedList.addByNo(hero4);
        doubleLinkedList.addByNo(hero2);
        doubleLinkedList.addByNo(hero3);
        doubleLinkedList.addByNo(hero4);
        list = doubleLinkedList.queryAll();
        System.out.println("======当前的链表情况======");
        for (HeroNode2 node2 : list) {
            System.out.println(node2);
        }
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("======修改后的链表情况======");
        list = doubleLinkedList.queryAll();
        for (HeroNode2 node2 : list) {
            System.out.println(node2);
        }
        // 删除
        doubleLinkedList.del(3);
        System.out.println("======删除后的链表情况~~======");
        list = doubleLinkedList.queryAll();
        for (HeroNode2 node2 : list) {
            System.out.println(node2);
        }
    }
}

class DoubleLinkedList {
    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode2 head = new HeroNode2(-1, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return this.head;
    }

    // 添加一个节点到双向链表的最后.
    public void add(HeroNode2 node) {
        HeroNode2 temp = this.head;
        while (temp.getNext() != null)
            temp = temp.getNext();
        // 当退出 while 循环时，temp 就指向了链表的最后
        // 形成一个双向链表
        temp.setNext(node);
        node.setPre(temp);
    }

    // 双向链表的第二种添加方式,按照编号顺序 ,按照单链表的顺序添加
    public void addByNo(HeroNode2 node) {
        HeroNode2 temp = this.head;
        // 判断链表是否为空
        if (temp.getNext() == null)
            throw new RuntimeException("链表为空");
        while (temp.getNext() != null) {
            if (temp.getNext().getNo() > node.getNo()) {
                node.setPre(temp);
                node.setNext(temp.getNext());
                temp.getNext().setPre(node);
                temp.setNext(node);
                return;
            } else if (temp.getNext().getNo() == node.getNo()) {
                System.out.println("该英雄编号已经存在，无法插入~");
                return;
            }
            temp = temp.getNext();

        }
        temp.setNext(node);
        node.setPre(temp);
    }

    // 从双向链表中删除一个节点,
    public void del(int no) {
        HeroNode2 temp = this.head;
        // 判断链表是否为空
        if (temp.getNext() == null)
            throw new RuntimeException("链表为空");
        while (temp.getNext() != null) {
            if (temp.getNext().getNo() == no) {
                if (temp.getNext().getNext() != null) {
                    temp.getNext().getNext().setPre(temp);
                }
                temp.setNext(temp.getNext().getNext());
                System.out.println("删除" + no + "号英雄成功~");
                return;
            }
            temp = temp.getNext();
        }
        // 已经到链表的最后
        System.out.println(no + "号英雄不存在，无法删除~");
    }

    // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    // 只是 节点类型改成 HeroNode2
    public void update(HeroNode2 node) {
        HeroNode2 temp = this.head;
        // 判断链表是否为空
        if (temp.getNext() == null)
            throw new RuntimeException("链表为空");
        while (temp.getNext() != null) {
            // 找到需要修改的节点, 根据 no 编号
            if (temp.getNext().getNo() == node.getNo()) {
                temp.getNext().setName(node.getName());
                temp.getNext().setNickname(node.getNickname());
                System.out.println("修改" + node.getNo() + "号英雄成功~");
                return;
            }
            temp = temp.getNext();
        }
        System.out.println(node.getNo() + "号英雄不存在，无法修改~");
    }

    // 获取指定编号的英雄
    public HeroNode2 queryByNo(int no) {
        HeroNode2 temp = this.head;
        // 判断链表是否为空
        if (temp.getNext() == null)
            throw new RuntimeException("链表为空");
        while (temp.getNext() != null) {
            if (temp.getNext().getNo() == no) {
                return temp.getNext();
            }
            temp = temp.getNext();
        }
        throw new RuntimeException(no + "号英雄不存在，无法查询~");
    }

    // 遍历双向链表的方法
    // 显示链表[遍历]
    public List<HeroNode2> queryAll() {
        HeroNode2 temp = this.head;
        // 判断链表是否为空
        if (temp.getNext() == null)
            throw new RuntimeException("链表为空");
        List<HeroNode2> list = new ArrayList<>();
        while (temp.getNext() != null) {
            temp = temp.getNext();
            list.add(temp);
        }
        return list;
    }
}

// 定义 HeroNode2 ， 每个 HeroNode 对象就是一个节点
class HeroNode2 {
    private HeroNode2 pre; // 指向前一个节点, 默认为 null
    private HeroNode2 next;// 指向下一个节点, 默认为 null
    private int no;
    private String name;
    private String nickname;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
        this.next = null;
        this.pre = null;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
