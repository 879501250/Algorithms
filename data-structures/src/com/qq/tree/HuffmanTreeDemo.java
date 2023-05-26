package com.qq.tree;

import java.util.*;

/**
 * 赫夫曼树-->用赫夫曼树进行编码和解码
 * <br>
 * 1) 给定 n 个权值作为 n 个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小，称这样的二叉树为
 * 最优二叉树，也称为哈夫曼树(Huffman Tree), 还有的书翻译为霍夫曼树。
 * <br>
 * 2) 赫夫曼树是带权路径长度最短的树，权值较大的结点离根较近
 * <br>
 * =======================
 * <br>
 * 1) 路径和路径长度：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之间的通路，称为路径。通路
 * 中分支的数目称为路径长度。若规定根结点的层数为 1，则从根结点到第 L 层结点的路径长度为 L-1
 * <br>
 * 2) 结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值称为该结点的权。结
 * 点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积
 * <br>
 * 3) 树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为 WPL(weighted path
 * length) ,权值越大的结点离根结点越近的二叉树才是最优二叉树。
 * <br>
 * ============步骤============
 * <br>
 * 1) 从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * <br>
 * 2) 取出根节点权值最小的两颗二叉树
 * <br>
 * 3) 组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * <br>
 * 4) 再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复 1-2-3-4 的步骤，直到数列中，所有的数
 * 据都被处理，就得到一颗赫夫曼树
 * <br>
 * ============注意事项==============
 * <br>
 * 1) 如果文件本身就是经过压缩处理的，那么使用赫夫曼编码再压缩效率不会有明显变化, 比如视频,ppt 等等文件
 * [举例压一个 .ppt]
 * <br>
 * 2) 赫夫曼编码是按字节来处理的，因此可以处理所有的文件(二进制文件、文本文件) [举例压一个.xml 文件]
 * <br>
 * 3) 如果一个文件中的内容，重复的数据不多，压缩效果也不会很明显.
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        System.out.println("利用霍夫曼树对数组进行排序=============");
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree root = new HuffmanTree(arr);
        // 测试一把
        System.out.println(root.getRoot());
        System.out.println("利用霍夫曼树对字符串进行编码，解压=============");
        String str = "i like like like java do you like a java";
        System.out.println("==========原数据==========");
        System.out.println(str);
        HuffmanTree tree = new HuffmanTree(str);
        System.out.println("==========编码后==========");
        byte[] bytes = tree.getBytes();
        for (byte b : bytes)
            System.out.print(b + ",");
        System.out.println();
        System.out.println("==========解码后==========");
        bytes = tree.decode(tree.getHuffmanMap(), tree.getBytes());
        for (byte b : bytes)
            System.out.print((char) b);
    }
}

// 创建霍夫曼树
class HuffmanTree {
    private HuffmanTreeNode root; // 根节点
    private Map<Byte, String> huffmanMap = new HashMap<>(); // 储存每个节点的编码值
    private byte[] bytes; // 储存编码后的byte

    // 若输入的是字符串，遍历每一个字符放入霍夫曼树中
    public HuffmanTree(String str) {
        // 将每个byte储存到一个map中并记录权重
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte huffmanCodeByte : str.getBytes()) {
            if (map.get(huffmanCodeByte) != null)
                map.put(huffmanCodeByte, map.get(huffmanCodeByte) + 1);
            else
                map.put(huffmanCodeByte, 1);
        }
        // 遍历map，将所有键值对储存为一个个节点
        List<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
        }
        // 利用这些节点构建树
        while (nodes.size() > 1) { // 当集合里的根节点数大于1时，即不止一棵树
            //排序 从小到大,HuffmanTreeNode类已继承Comparable接口，并重写了compareTo方法
            Collections.sort(nodes);
            // 将集合中最小的两个节点组合成一个节点，并分别设置为左右子节点
            HuffmanTreeNode node = new HuffmanTreeNode
                    (nodes.get(0).getValue() + nodes.get(1).getValue());
            node.setLeft(nodes.get(0));
            node.setRight(nodes.get(1));
            // 删除最小的两个节点，并加上新的节点
            nodes.remove(1);
            nodes.remove(0);
            nodes.add(node);
        }
        // 将根节点设置为唯一剩下的节点
        this.root = nodes.get(0);
        // 对树的各个叶子节点进行编码
        StringBuilder builder = new StringBuilder();
        getCodes(root.getLeft(), "0", builder);
        getCodes(root.getRight(), "1", builder);
        // 根据每个字符的编码对原字符串进行编码
        encode(this.huffmanMap, str);
    }

    /**
     * 根据每个字符的编码对原字符串进行编码
     *
     * @param huffmanMap 赫夫曼编码表 map
     * @param str        原字符串
     */
    private void encode(Map<Byte, String> huffmanMap, String str) {
        StringBuffer buffer = new StringBuffer(); // 储存原字符串对应的二进制的字符串
        for (Byte b : str.getBytes()) // 拼接字符串
            buffer.append(huffmanMap.get(b));
        // 将 拼接后的字符串 转成 byte[]
        // 统计返回 byte[] huffmanCodeBytes 长度
        // 一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if (buffer.length() % 8 == 0)
            len = buffer.length() / 8;
        else
            len = buffer.length() / 8 + 1;
        // 创建 存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        for (int i = 0; i < buffer.length(); i += 8) { // 因为是每 8 位对应一个 byte,所以步长 +8
            String strByte;
            if (i + 8 > buffer.length()) {// 不够 8 位
                strByte = buffer.substring(i);
            } else {
                strByte = buffer.substring(i, i + 8);
            }
            // 将 strByte 转成一个 byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[i / 8] = (byte) Integer.parseInt(strByte, 2);
        }
        this.bytes = huffmanCodeBytes;
    }

    /**
     * 将一个 byte 转成一个二进制的字符串
     * 码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补,
     *             如果是最后一个字节，无需补高位
     * @return 是该 b 对应的二进制的字符串，（注意是按补码返回）
     */
    private String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b; // 将 b 转成 int
        // 如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; // 按位与 256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); // 返回的是 temp 对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 编写一个方法，完成对压缩数据的解码
     * <br>
     * 还有问题：需要判断最后一位是不是刚好8位，还有如果是0010这样的情况，需要记录0的个数
     * <br>
     * ->还有就是在解码的时候，最后一个byte数还原的时候，需要判断它的位数，大于8位就需要截取后8位，
     * <br>
     * ->小于8位就需要判断前面可能需要补0，例如：末尾是0010压缩时是2，但是解码的时候2是变成10
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    public byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到 huffmanBytes 对应的 二进制的字符串，形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        // 将 byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        // 把字符串安装指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放 byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {// 1010100010111... //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);// i 不动，让 count 移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {// 说明没有匹配到
                    count++;
                } else { //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;// i 直接移动到 count
        }
        // 当 for 循环结束后，我们 list 中就存放了所有的字符 "i like like like java do you like a java"
        // 把 list 中的数据放入到 byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    // 输入一个数组构建霍夫曼树
    public HuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1. 遍历 arr 数组
        // 2. 将 arr 的每个元素构成成一个 Node
        // 3. 将 Node 放入到 ArrayList 中
        List<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>();
        for (int value : arr) {
            nodes.add(new HuffmanTreeNode(value));
        }
        while (nodes.size() > 1) { // 当集合里的根节点数大于1时，即不止一棵树
            // 排序 从小到大,HuffmanTreeNode类已继承Comparable接口，并重写了compareTo方法
            Collections.sort(nodes);
            // 将集合中最小的两个节点组合成一个节点，并分别设置为左右子节点
            HuffmanTreeNode node = new HuffmanTreeNode
                    (nodes.get(0).getValue() + nodes.get(1).getValue());
            node.setLeft(nodes.get(0));
            node.setRight(nodes.get(1));
            // 删除最小的两个节点，并加上新的节点
            nodes.remove(1);
            nodes.remove(0);
            nodes.add(node);
        }
        // 将根节点设置为唯一剩下的节点
        this.root = nodes.get(0);
    }

    /**
     * 功能：将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanMap 集合
     *
     * @param node          传入结点
     * @param code          路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    public void getCodes(HuffmanTreeNode node, String code, StringBuilder stringBuilder) {
        StringBuilder builder = new StringBuilder(stringBuilder);
        // 将 code 加入到 stringBuilder2
        builder.append(code);
        if (node != null) { // 如果 node == null 不处理
            // 判断当前 node 是叶子结点还是非叶子结点
            if (node.getData() == null) { //非叶子结点
                // 向左递归
                getCodes(node.getLeft(), "0", builder);
                // 向右递归
                getCodes(node.getRight(), "1", builder);
            } else // 说明是一个叶子结点
                // 添加到huffmanMap
                huffmanMap.put(node.getData(), builder.toString());
        }
    }

    public HuffmanTreeNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanTreeNode root) {
        this.root = root;
    }

    public Map<Byte, String> getHuffmanMap() {
        return huffmanMap;
    }

    public void setHuffmanMap(Map<Byte, String> huffmanMap) {
        this.huffmanMap = huffmanMap;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

// 创建结点类
// 为了让 Node 对象持续排序 Collections 集合排序
// 让 Node 实现 Comparable 接口
class HuffmanTreeNode implements Comparable<HuffmanTreeNode> {
    private Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    private int value; // 结点权值
    private HuffmanTreeNode left; // 指向左子结点
    private HuffmanTreeNode right; // 指向右子结点

    public HuffmanTreeNode(Byte data, int value) {
        this.data = data;
        this.value = value;
    }

    public HuffmanTreeNode(int value) {
        this.value = value;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanTreeNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanTreeNode left) {
        this.left = left;
    }

    public HuffmanTreeNode getRight() {
        return right;
    }

    public void setRight(HuffmanTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanTreeNode{" +
                "data=" + data +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(HuffmanTreeNode o) {
        // 表示从小到大排序
        return this.value - o.value;
    }
}