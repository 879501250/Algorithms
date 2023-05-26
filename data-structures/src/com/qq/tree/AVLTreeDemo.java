package com.qq.tree;

/**
 * 给你一个数列{1,2,3,4,5,6}，要求创建一颗二叉排序树(BST), 并分析问题所在.
 * <br>
 *  左边 BST 存在的问题分析:
 * <br>
 * 1) 左子树全部为空，从形式上看，更像一个单链表. 2) 插入速度没有影响
 * <br>
 * 3) 查询速度明显降低(因为需要依次比较), 不能发挥 BST的优势，因为每次还需要比较左子树，其查询速度比
 * 单链表还慢
 * <br>
 * 4) 解决方案-平衡二叉树(AVL)
 * <br>
 * ============平衡二叉树(AVL)==============
 * <br>
 * 1) 平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为 AVL 树,
 * 可以保证查询效率较高。
 * <br>
 * 2) 具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵
 * 平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等。
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4,3,6,5,7,8};
        // int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = {10, 11, 7, 6, 8, 9};
        // 创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree(new AVLTreeNode(arr[0]));
        // 添加结点
        for (int i = 1; i < arr.length; i++) {
            avlTree.addNode(new AVLTreeNode(arr[i]));
        }
        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().getHeight()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().getLeft().getHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().getRight().getHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8

    }
}

// 创建平衡二叉树
class AVLTree {
    private AVLTreeNode root;

    public AVLTree(AVLTreeNode root) {
        this.root = root;
    }

    // 左旋转
    private void leftRotate() {
        // 如果右子树较高，进行左旋
        if (root.getRightHeight() - root.getLeftHeight() > 1) {
            AVLTreeNode rootRight = root.getRight(); // 定义一个右子树节点
            // 注意！！！如果右子树本身存在高度差需要，先对右子树进行右旋
            if (rootRight != null &&
                    rootRight.getLeftHeight() - root.getRightHeight() > 0) {
                AVLTreeNode rootRightLeft = rootRight.getLeft();
                rootRight.setLeft(rootRightLeft.getRight());
                rootRightLeft.setRight(rootRight);
                root.setRight(rootRightLeft);
            }
            rootRight = root.getRight();
            root.setRight(rootRight.getLeft());
            rootRight.setLeft(root);
            root = rootRight;
        }
    }

    // 右旋转
    private void rightRotate() {
        // 如果左子树较高，进行右旋
        if (root.getLeftHeight() - root.getRightHeight() > 1) {
            AVLTreeNode rootLeft = root.getLeft(); // 定义一个左子树节点
            // 注意！！！如果左子树本身存在高度差需要，先对左子树进行右旋
            if (rootLeft != null &&
                    rootLeft.getRightHeight() - rootLeft.getLeftHeight() > 0) {
                AVLTreeNode rootLeftRight = rootLeft.getRight();
                rootLeft.setRight(rootLeftRight.getLeft());
                rootLeftRight.setLeft(rootLeft);
                root.setLeft(rootLeftRight);
            }
            rootLeft = root.getLeft();
            root.setLeft(rootLeft.getRight());
            rootLeft.setRight(root);
            root = rootLeft;
        }
    }

    // 添加节点（重载）
    public void addNode(AVLTreeNode node) {
        addNode(root, node);
    }

    /**
     * @param parentNode 当前节点
     * @param node       要插入的节点
     */
    public void addNode(AVLTreeNode parentNode, AVLTreeNode node) {
        if (parentNode.getValue() > node.getValue()) { // 如果当前节点大于要插入的节点，遍历左子树
            if (parentNode.getLeft() == null)
                parentNode.setLeft(node);
            else
                addNode(parentNode.getLeft(), node);
        } else {
            if (parentNode.getRight() == null)
                parentNode.setRight(node);
            else
                addNode(parentNode.getRight(), node);
        }
        leftRotate();
        rightRotate();
    }

    // 中序遍历（重载）
    public void infixOrder() {
        if (this.root != null)
            infixOrder(this.root);
        else
            System.out.println("该树无节点~");
    }

    // 中序遍历
    private void infixOrder(AVLTreeNode node) {
        if (node.getLeft() != null)
            infixOrder(node.getLeft());
        System.out.println(node);
        if (node.getRight() != null)
            infixOrder(node.getRight());
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }
}

// 创建平衡二叉树结点
class AVLTreeNode {
    private int value;
    private AVLTreeNode left;
    private AVLTreeNode right;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    // 获取当前节点的高
    public int getHeight() {
        return Math.max(this.left != null ? this.left.getHeight() : 0,
                this.right != null ? this.right.getHeight() : 0) + 1;
    }

    // 获取当前节点左子树的高
    public int getLeftHeight() {
        if (this.left == null)
            return 0;
        AVLTreeNode temp = this.left;
        return Math.max(temp.getLeft() != null ? temp.getLeft().getHeight() : 0,
                temp.getRight() != null ? temp.getRight().getHeight() : 0) + 1;
    }

    // 获取当前节点右子树的高
    public int getRightHeight() {
        if (this.right == null)
            return 0;
        AVLTreeNode temp = this.right;
        return Math.max(temp.getLeft() != null ? temp.getLeft().getHeight() : 0,
                temp.getRight() != null ? temp.getRight().getHeight() : 0) + 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLTreeNode getLeft() {
        return left;
    }

    public void setLeft(AVLTreeNode left) {
        this.left = left;
    }

    public AVLTreeNode getRight() {
        return right;
    }

    public void setRight(AVLTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                '}';
    }
}