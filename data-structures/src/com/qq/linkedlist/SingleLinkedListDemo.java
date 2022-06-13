package com.qq.linkedlist;

import java.util.Stack;

/**
 * 单链表
 * 1) 链表是以节点的方式来存储,是链式存储
 * 2) 每个节点包含 data 域， next 域：指向下一个节点.
 * 3) 如图：发现链表的各个节点不一定是连续存储（主要是地址不连续）.
 * 4) 链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定
 */
public class SingleLinkedListDemo {
    //使用带 head 头的单向链表实现 –水浒英雄排行榜管理完成对英雄人物的增删改查操作
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
        //singleLinkedList.addNode(hero1);
        //singleLinkedList.addNode(hero4);
        //singleLinkedList.addNode(hero2);
        //singleLinkedList.addNode(hero3);
        //加入按照编号的顺序
        singleLinkedList.addNodeByNo(hero1);
        singleLinkedList.addNodeByNo(hero4);
        singleLinkedList.addNodeByNo(hero2);
        singleLinkedList.addNodeByNo(hero3);
        singleLinkedList.addNodeByNo(new HeroNode(9, "小李广", "花 荣"));
        singleLinkedList.addNodeByNo(new HeroNode(6, "豹子头", "林 冲"));
        //显示一把
        System.out.println("==========当前链表==========");
        singleLinkedList.queryNode();
        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.updateNode(newHeroNode);
        System.out.println("==========修改卢俊义后的链表==========");
        singleLinkedList.queryNode();
        //删除一个节点
        singleLinkedList.delNode(1);
        singleLinkedList.delNode(4);
        System.out.println("==========删除1、4号英雄后的链表==========");
        singleLinkedList.queryNode();
        SingleLinkedList list = new SingleLinkedList();
        list.addNodeByNo(new HeroNode(5, "大 刀", "关 胜"));
        list.addNodeByNo(new HeroNode(7, "霹雳火", "秦 明"));
        list.addNodeByNo(new HeroNode(8, "双 鞭", "呼延灼"));
        list.addNodeByNo(new HeroNode(10, "小旋风", "柴 进"));
        singleLinkedList.addNodeByNo(hero1);
        singleLinkedList.addNodeByNo(hero4);
        System.out.println("==========新链表==========");
        list.queryNode();
        System.out.println("==========合并完两个有序的单链表后，此链表为==========");
        SingleLinkedList newList = mergeList(singleLinkedList.getHeaderNode(), list.getHeaderNode());
        newList.queryNode();
        System.out.println("==========当前链表的有效结点个数为==========");
        System.out.println(getLength(newList.getHeaderNode()));
        //随机获取[1,链表长度+1)的数字
        int k = (int) (Math.random() * getLength(newList.getHeaderNode()) + 1);
        System.out.println("==========当前链表的倒数第 " + k + " 个结点为==========");
        System.out.println(findLastIndexNode(newList.getHeaderNode(), k));
        System.out.println("==========反转后的链表为==========");
        reversetList(newList.getHeaderNode());
        newList.queryNode();
        System.out.println("==========从尾到头打印当前链表==========");
        reversePrint(newList.getHeaderNode());
    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     * 自己写的
     */
    public static SingleLinkedList mergeList(HeroNode node1, HeroNode node2) {
        //创建新的有序单链表
        SingleLinkedList newList = new SingleLinkedList();
        HeroNode temp1 = node1; //使temp1从第一个链表的头节点开始遍历
        HeroNode temp2;
        boolean flag; //判断两个单链表中的两个节点的no是否相等
        //遍历第二个链表，依次取出节点插入第一个链表
        while (node2.getNext() != null) {
            //相当于让temp2等于第二个链表除头节点外的第一个节点，方便后续操作
            temp2 = node2.getNext();
            //让第二个链表的头节点指向除头节点外的第二个节点，目的让temp2独立出来，方便后续操作
            node2.setNext(temp2.getNext());
            //默认不存在no相同的节点
            flag = false;
            //开始遍历第一个链表，开始并在合适位置插入temp2
            //优化：从temp1的位置开始遍历，因为两个链表都是有序的，前面已经比较过了以后就不用比较了
            while (temp1.getNext() != null) {
                //当temp1的下个节点no大于temp2这个节点的no时，就是合适位置
                if (temp1.getNext().getNo() > temp2.getNo()) {
                    //使temp2后续的节点赋值为temp1后续的节点
                    temp2.setNext(temp1.getNext());
                    break;
                }
                //两个节点no相等，跳出遍历，默认不插入
                else if(temp1.getNext().getNo() == temp2.getNo()){
                    flag = true;
                    break;
                }
                //当temp1的下个节点no小于temp2这个节点的no时，继续遍历第一个链表，寻找合适位置
                else
                    temp1=temp1.getNext();
            }
            //若no不相等，插入temp2，将temp1的后续节点设置为temp2
            if (!flag)
                temp1.setNext(temp2);
        }
        //将要返回的链表的头节点后面的节点设置为node1后面的节点
        newList.getHeaderNode().setNext(node1.getNext());
        return newList;
    }

    /**
     * 从尾到头打印单链表 【百度，要求方式 1：反向遍历 。 方式 2：TestStack 栈】
     * 1)先反转再遍历
     * 2）利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，实现逆序打印
     * 这里使用方式二
     */
    public static void reversePrint(HeroNode head) {
        if (head.getNext() == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.getNext();
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.getNext(); //cur 后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack 的特点是先进后出
        }
    }

    /**
     * 单链表的反转【腾讯面试题，有点难度】
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode temp = new HeroNode();
        int count = getLength(head);
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
        for (int i = 0; i < count; i++) {
            //先暂时保存当前节点的下一个节点，因为后面需要使用
            HeroNode next = head.getNext();
            //将 head 的下一个节点指向新的链表的最前端
            head.setNext(next.getNext());
            //将 temp 连接到新的链表上
            next.setNext(temp.getNext());
            //让 next 后移
            temp.setNext(next);
        }
        head.setNext(temp.getNext());
    }

    /**
     * 查找单链表中的倒数第 k 个结点 【新浪面试题】
     * //思路===============快慢指针？前后两个指针相差 k？
     * //1. 编写一个方法，接收 head 节点，同时接收一个 index
     * //2. index 表示是倒数第 index 个节点
     * //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * //4. 得到 size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * //5. 如果找到了，则返回该节点，否则返回 nulll
     */
    public static HeroNode findLastIndexNode(HeroNode node, int k) {
        int count = getLength(node);
        if (count == 0)
            throw new RuntimeException("该链表暂无结点");
        else if (count < k)
            throw new RuntimeException("该链表结点个数小于" + k);
        else {
            HeroNode temp = node.getNext();
            for (int i = 0; i < count - k; i++)
                temp = temp.getNext();
            return temp;
        }
    }

    /**
     * 传入获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
     */
    public static int getLength(HeroNode node) {
        int i = 0;
        while (node.getNext() != null) {
            node = node.getNext();
            i++;
        }
        return i;
    }
}

//定义 SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //定义一个默认的链表头，除了next指向别的不能改,头节点不要动, 不存放具体的数据
    private HeroNode headerNode = new HeroNode(0, "", "");

    //获取头结点
    public HeroNode getHeaderNode() {
        return this.headerNode;
    }

    /**
     * 可以定义一个节点指向最后一个节点，加快遍历、查询、添加等操作速度
     * private HeroNode lastNode;
     */

    // 插入英雄，默认是从最后一个开始插入，若编号存在，插入失败
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的 next 指向 新的节点
    public void addNode(HeroNode node) {
        if (headerNode.getNext() == null)
            headerNode.setNext(node);
        else {
            //因为 head 节点不能动，因此我们需要一个辅助遍历 temp
            HeroNode temp = headerNode.getNext();
            while (temp != null) {
                if (temp.getNo() == node.getNo()) {
                    System.out.println("该英雄编号已存在，无法插入~");
                    return;
                }
                if (temp.getNext() != null)
                    temp = temp.getNext();
                else {
                    temp.setNext(node);
                    System.out.println("插入英雄成功~");
                    return;
                }
            }
        }
    }

    // 按照英雄编号升序插入英雄(如果有这个排名，则添加失败，并给出提示)
    public void addNodeByNo(HeroNode node) {
        if (headerNode.getNext() == null)
            headerNode.setNext(node);
        else {
            //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
            HeroNode temp = headerNode;
            //因为单链表，因为我们找的 temp 是位于 添加位置的前一个节点，否则插入不了
            while (temp.getNext() != null) {
                if (temp.getNext().getNo() < node.getNo()) {
                    temp = temp.getNext();
                    continue;
                } else if (temp.getNext().getNo() == node.getNo()) {
                    //说明希望添加的 heroNode 的编号已然存在
                    System.out.println("该英雄编号已存在，无法插入~");
                    return;
                } else {
                    //位置找到，就在 temp 的后面插入
                    node.setNext(temp.getNext());
                    temp.setNext(node);
                    System.out.println("插入英雄成功~");
                    return;
                }
            }
            //说明 temp 已经在链表的最后
            temp.setNext(node);
            System.out.println("插入英雄成功~");
        }
    }

    // 删除指定英雄
    public void delNode(int no) {
        if (headerNode.getNext() == null)
            System.out.println("该链表不存在英雄，请先添加~");
        else {
            //head 不能动，因此我们需要一个 temp 辅助节点找到待删除节点的前一个节点
            HeroNode temp = headerNode;
            while (temp.getNext() != null) {
                //说明我们在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
                if (temp.getNext().getNo() == no) {
                    temp.setNext(temp.getNext().getNext());
                    System.out.println("删除英雄成功~");
                    return;
                }
                temp = temp.getNext();
            }
            System.out.println("该英雄编号不存在~");
        }
    }

    //修改节点的信息, 根据 no 编号来修改，即 no 编号不能改.
    public void updateNode(HeroNode node) {
        if (headerNode.getNext() == null)
            System.out.println("该链表不存在英雄，请先添加~");
        else {
            //定义一个辅助变量
            HeroNode temp = headerNode.getNext();
            while (temp.getNext() != null) {
                //找到需要修改的节点, 根据 no 编号
                if (temp.getNo() == node.getNo()) {
                    temp.setName(node.getName());
                    temp.setNickname(node.getNickname());
                    System.out.println("修改英雄成功~");
                    return;
                }
                temp = temp.getNext();
            }
            //已经遍历完链表
            System.out.println("该英雄编号不存在~");
        }
    }

    //查询指定英雄
    public void queryNode(int no) {
        if (headerNode.getNext() == null)
            System.out.println("该链表不存在英雄，请先添加~");
        else {
            HeroNode temp = headerNode.getNext();
            while (temp.getNext() != null) {
                if (temp.getNo() == no) {
                    System.out.println(temp.toString());
                    return;
                }
                temp = temp.getNext();
            }
            System.out.println("该英雄编号不存在~");
        }
    }

    //查询所有英雄,显示链表[遍历]
    public void queryNode() {
        if (headerNode.getNext() == null)
            System.out.println("该链表不存在英雄，请先添加~");
        else {
            HeroNode temp = headerNode;
            while (temp.getNext() != null) {
                System.out.println(temp.getNext().toString());
                temp = temp.getNext();
            }
        }
    }
}

//定义 HeroNode ， 每个 HeroNode 对象就是一个节点
class HeroNode {
    private int no;         //每个英雄的编号
    private String name;     //每个英雄的名字
    private String nickname; //每个英雄的别称
    private HeroNode next;   //指向下一个节点

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
        this.next = null;
    }

    public int getNo() {
        return no;
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

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    //只输出属于自己的内容，将next指向的节点省去
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}