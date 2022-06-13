package com.qq.tree;

/**
 * 使用数组
 * 数组未排序， 优点：直接在数组尾添加，速度快。 缺点：查找速度慢. [示意图]
 * 数组排序，优点：可以使用二分查找，查找速度快，缺点：为了保证数组有序，在添加新数据时，找到插入位
 * 置后，后面的数据需整体移动，速度慢。[示意图]
 * 使用链式存储-链表
 * 不管链表是否有序，查找速度都慢，添加数据速度比数组快，不需要数据整体移动。
 * ===========二叉排序树介绍==============
 * 二叉排序树：BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，
 * 要求左子节点的值比当前节点的值小，右子节点的值比当前节点的值大。
 * 特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree(new BinarySortTreeNode(arr[0]));
        //循环的添加结点到二叉排序树
        for (int i = 1; i < arr.length; i++) {
            binarySortTree.addNode(new BinarySortTreeNode(arr[i]));
        }
        System.out.println("==========中序遍历==========");
        System.out.println("==========删除节点前==========");
        binarySortTree.infixOrder();
        System.out.println("==========删除节点后==========");
        binarySortTree.delNode(12);
        binarySortTree.infixOrder();
    }
}

// 创建二叉排序树
class BinarySortTree {
    private BinarySortTreeNode root; // 根节点

    public BinarySortTree(BinarySortTreeNode root) {
        this.root = root;
    }

    // 删除节点（重载）
    public void delNode(int value) {
        if (root.getValue() == value) { // 当要删除的节点是根节点时
            if (root.getLeft() == null && root.getRight() == null)
                root = null; // 如果没有子节点
            else if (root.getRight() != null && root.getLeft() != null)
                root = getSuffixNode(root); // 如果有两个子节点
            else // 只有一个子节点
                root = root.getLeft() == null ? root.getRight() : root.getLeft();
            return;
        }
        delNode(root, value);
    }

    /**
     * @param temp  当前节点
     * @param value 要删除的节点值
     */
    private void delNode(BinarySortTreeNode temp, int value) {
        // 当要删除的节点在根节点的左子树时
        if (temp.getLeft() != null && temp.getValue() > value) {
            if (temp.getLeft().getValue() == value) { // 找到要删除的节点
                if (temp.getLeft().getLeft() == null && temp.getLeft().getRight() == null)
                    temp.setLeft(null); // 如果没有子节点
                else if (temp.getLeft().getRight() != null && temp.getLeft().getLeft() != null)
                    temp.setLeft(getSuffixNode(temp.getLeft())); // 如果有两个子节点
                else // 只有一个子节点
                    temp.setLeft(temp.getLeft().getLeft() == null ?
                            temp.getLeft().getRight() : temp.getLeft().getLeft());
                return;
            } else {
                delNode(temp.getLeft(), value);
                return;
            }
        }
        // 当要删除的节点在根节点的右子树时
        if (temp.getRight() != null && temp.getValue() < value) {
            if (temp.getRight().getValue() == value) {
                if (temp.getRight().getLeft() == null && temp.getRight().getRight() == null)
                    temp.setRight(null); // 如果没有子节点
                else if (temp.getRight().getRight() != null && temp.getRight().getLeft() != null)
                    temp.setRight(getSuffixNode(temp.getRight())); // 如果有两个子节点
                else // 只有一个子节点
                    temp.setRight(temp.getRight().getLeft() == null ?
                            temp.getRight().getRight() : temp.getRight().getLeft());
                return;
            } else {
                delNode(temp.getRight(), value);
                return;
            }
        }
    }

    // 获取该树的后缀点
    private BinarySortTreeNode getSuffixNode(BinarySortTreeNode node) {
        if (node.getRight() != null) { // 如果有右节点
            if (node.getRight().getLeft() != null) { // 如果右节点的左节点不为空
                BinarySortTreeNode treeNode = node.getRight();
                while (treeNode.getLeft().getLeft() != null)
                    treeNode = treeNode.getLeft();
                // 此时treeNode即后缀点的父节点
                // 取出后缀点
                BinarySortTreeNode suffixNode = treeNode.getLeft();
                // 将后缀点的右子树替换后缀点当作后缀点父节点的左子树
                treeNode.setLeft(suffixNode.getRight());
                // 设置后缀点的左右子树分别为node的左右子树
                suffixNode.setLeft(node.getLeft());
                suffixNode.setRight(node.getRight());
                return suffixNode;
            } else { // 右节点的左节点为空，则当前节点为后缀点
                node.getRight().setLeft(node.getLeft());
                return node.getRight();
            }
        } else return node;
    }

    // 添加节点（重载）
    public void addNode(BinarySortTreeNode node) {
        addNode(root, node);
    }

    /**
     * @param parentNode 当前节点
     * @param node       要插入的节点
     */
    public void addNode(BinarySortTreeNode parentNode, BinarySortTreeNode node) {
        if (parentNode.getValue() > node.value) { // 如果当前节点大于要插入的节点，遍历左子树
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
    }

    // 中序遍历（重载）
    public void infixOrder() {
        if (root != null)
            infixOrder(root);
        else
            System.out.println("该树无节点~");
    }

    public void infixOrder(BinarySortTreeNode node) {
        if (node.getLeft() != null)
            infixOrder(node.getLeft());
        System.out.println(node.toString());
        if (node.getRight() != null)
            infixOrder(node.getRight());
    }
}

// 创建二叉排序树节点
class BinarySortTreeNode {
    int value;
    BinarySortTreeNode left;
    BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortTreeNode left) {
        this.left = left;
    }

    public BinarySortTreeNode getRight() {
        return right;
    }

    public void setRight(BinarySortTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }
}
