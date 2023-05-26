package com.qq.tree;

/**
 * 顺序存储二叉树
 * <br>
 * 从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组，
 * 看右面的示意图。
 * <br>
 * ==============================
 * <br>
 * 特点:
 * <br>
 * 1) 顺序二叉树通常只考虑完全二叉树
 * <br>
 * 2) 第 n 个元素的左子节点为 2 * n + 1
 * <br>
 * 3) 第 n 个元素的右子节点为 2 * n + 2
 * <br>
 * 4) 第 n 个元素的父节点为 (n-1) / 2
 * <br>
 * 5) n : 表示二叉树中的第几个元素(按 0 开始编号)
 */
public class ArrBinaryTreeDemo {
    /**
     * 需求: 给你一个数组 {1,2,3,4,5,6,7}，要求以二叉树前序遍历的方式进行遍历。 前序遍历的结果应当为
     * 1,2,4,5,3,6,7
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        // arrBinaryTree.preOrder(); // 前序遍历：1,2,4,5,3,6,7
        // arrBinaryTree.infixOrder(); // 中序遍历：4,2,5,1,6,3,7
        // arrBinaryTree.suffixOrder(); // 后序遍历：4,5,2,6,7,3,1
    }
}

// 定义一个顺序二叉树
class ArrBinaryTree {
    private int[] arr;// 存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载 preOrder
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        if (arr.length == 0) {
            System.out.println("数组为空，没有数据~");
            return;
        }
        // 前序遍历，先打印当前数据
        System.out.println(arr[index]);
        // 若存在左子节点，打印
        if (index * 2 + 1 < arr.length)
            preOrder(index * 2 + 1);
        // 若存在右子节点，打印
        if (index * 2 + 2 < arr.length)
            preOrder(index * 2 + 2);
    }

    // 重载 infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    private void infixOrder(int index) {
        if (arr.length == 0) {
            System.out.println("数组为空，没有数据~");
            return;
        }
        // 若存在左子节点，打印
        if (index * 2 + 1 < arr.length)
            infixOrder(index * 2 + 1);
        // 中序遍历，打印当前数据
        System.out.println(arr[index]);
        // 若存在右子节点，打印
        if (index * 2 + 2 < arr.length)
            infixOrder(index * 2 + 2);
    }

    // 重载 suffixOrder
    public void suffixOrder() {
        this.suffixOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的后序遍历
     *
     * @param index 数组的下标
     */
    private void suffixOrder(int index) {
        if (arr.length == 0) {
            System.out.println("数组为空，没有数据~");
            return;
        }
        // 若存在左子节点，打印
        if (index * 2 + 1 < arr.length)
            suffixOrder(index * 2 + 1);
        // 若存在右子节点，打印
        if (index * 2 + 2 < arr.length)
            suffixOrder(index * 2 + 2);
        // 后序遍历，打印当前数据
        System.out.println(arr[index]);
    }
}
