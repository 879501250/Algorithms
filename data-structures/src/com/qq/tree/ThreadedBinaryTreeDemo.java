package com.qq.tree;

/**
 * 线索化二叉树
 * 1) n 个结点的二叉链表中含有 n+1 【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，
 * 存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
 * 2) 这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
 * 根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
 * 3) 一个结点的前一个结点，称为前驱结点
 * 4) 一个结点的后一个结点，称为后继结点
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        ThreadedBinaryTreeNode root = new ThreadedBinaryTreeNode(1, "tom");
        ThreadedBinaryTreeNode node2 = new ThreadedBinaryTreeNode(3, "jack");
        ThreadedBinaryTreeNode node3 = new ThreadedBinaryTreeNode(6, "smith");
        ThreadedBinaryTreeNode node4 = new ThreadedBinaryTreeNode(8, "mary");
        ThreadedBinaryTreeNode node5 = new ThreadedBinaryTreeNode(10, "king");
        ThreadedBinaryTreeNode node6 = new ThreadedBinaryTreeNode(14, "dim");
        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
        //测试: 以 10 号节点测试
        ThreadedBinaryTreeNode leftNode = node5.getLeft();
        ThreadedBinaryTreeNode rightNode = node5.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10 号结点的后继结点是 =" + rightNode); //1
        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

// 定义一个线索化二叉树
class ThreadedBinaryTree {
    private ThreadedBinaryTreeNode root;
    private ThreadedBinaryTreeNode pre = null; // 定义一个前驱节点

    public ThreadedBinaryTree() {

    }

    public ThreadedBinaryTree(ThreadedBinaryTreeNode root) {
        this.root = root;
        // 将各个节点线索化;
        infixThreadedNodes(root);
    }

    // 中序遍历线索化二叉树的方法
    public void threadedList() {
//        ThreadedBinaryTreeNode temp = this.root;
//        while (temp.getLeftType()!=1) // 找到左子树线索化的第一个节点
//            temp=temp.getLeft();
//        System.out.println(temp);
//        // 打印左子树及根节点
//        while (temp.getRight()!=null&&temp!=this.root){
//            temp=temp.getRight();
//            System.out.println(temp);
//        }
//        if(temp.getRight()!=null){
//            temp=temp.getRight();
//            while (temp.getLeftType()!=1) // 找到右子树线索化的第一个节点
//                temp=temp.getLeft();
//            System.out.println(temp);
//            // 打印右子树及根节点
//            while (temp.getRight()!=null){
//                temp=temp.getRight();
//                System.out.println(temp);
//            }
//        }
        //定义一个变量，存储当前遍历的结点，从 root 开始
        ThreadedBinaryTreeNode node = root;
        while (node != null) {
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            //后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRight();
        }
    }

    /**
     * 对二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的结点
     */
    public void preThreadedNodes(ThreadedBinaryTreeNode node) {
        //如果 node==null, 不能线索化
        if (node == null) {
            return;
        }
        boolean flag = false; // 定义一个当前节点是否线索化的标志
        //(一)线索化当前结点[有难度]
        // 设置当前节点的前缀节点
        if (node.getLeft() == null) { //如果存在左子节点就不用线索化
            node.setLeft(pre);
            node.setLeftType(1);
            flag = true;
        }
        // 设置前缀节点的后缀节点是当前节点
        if (pre != null && pre.getRight() == null) { //如果前缀节点存在右子节点就不用线索化
            pre.setRight(node);
            pre.setRightType(1);
        }
        if (flag) //!!! 只有当前节点线索化后才让当前结点是下一个结点的前驱结点
            pre = node;
        //(二)先线索化左子树
        if (node.getLeftType() != 1) {
            // 如果当前节点的下一个节点是要线索化的第一个节点，设置当前节点是一个前缀节点
            if (pre == null && node.getLeft().getLeft() == null)
                pre = node;
            preThreadedNodes(node.getLeft());
        }
        //(三)在线索化右子树
        if (node.getRightType() != 1)
            preThreadedNodes(node.getRight());
    }

    /**
     * 对二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的结点
     */
    public void infixThreadedNodes(ThreadedBinaryTreeNode node) {
        //如果 node==null, 不能线索化
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        infixThreadedNodes(node.getLeft());
        //(二)线索化当前结点[有难度]
        // 设置当前节点的前缀节点
        if (node.getLeft() == null) { //如果存在左子节点就不用线索化
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 设置前缀节点的后缀节点是当前节点
        if (pre != null && pre.getRight() == null) { //如果前缀节点存在右子节点就不用线索化
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //(三)在线索化右子树
        infixThreadedNodes(node.getRight());
    }

    /**
     * 对二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的结点
     */
    public void suffixThreadedNodes(ThreadedBinaryTreeNode node) {
        //如果 node==null, 不能线索化
        if (node == null) {
            return;
        }
        //(一)先线索化左子树
        suffixThreadedNodes(node.getLeft());
        //(二)在线索化右子树
        suffixThreadedNodes(node.getRight());
        boolean flag = false; // 定义一个当前节点是否线索化的标志
        //(三)线索化当前结点[有难度]
        // 设置当前节点的前缀节点
        if (node.getLeft() == null) { //如果存在左子节点就不用线索化
            node.setLeft(pre);
            node.setLeftType(1);
            flag = true;
        }
        // 设置前缀节点的后缀节点是当前节点
        if (pre != null && pre.getRight() == null) { //如果前缀节点存在右子节点就不用线索化
            pre.setRight(node);
            pre.setRightType(1);
        }
        if (flag)//!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
            pre = node;
    }

    // 判断树是否为空
    public boolean isEmpty() {
        return root == null ? true : false;
    }
}

// 定义一个线索化二叉树节点
class ThreadedBinaryTreeNode {
    private int no;
    private String name;
    private ThreadedBinaryTreeNode left; //默认 null
    private ThreadedBinaryTreeNode right; //默认 null
    //说明
    //1. 如果 leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
    //2. 如果 rightType == 0 表示指向是右子树, 如果 1 表示指向后继结点
    private int leftType;
    private int rightType;

    public ThreadedBinaryTreeNode(int no, String name) {
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

    public ThreadedBinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedBinaryTreeNode left) {
        this.left = left;
    }

    public ThreadedBinaryTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedBinaryTreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedBinaryTreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}