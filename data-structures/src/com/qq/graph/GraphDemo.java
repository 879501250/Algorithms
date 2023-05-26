package com.qq.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ==========为什么要有图==========
 * <br>
 * 1) 前面我们学了线性表和树
 * <br>
 * 2) 线性表局限于一个直接前驱和一个直接后继的关系
 * <br>
 * 3) 树也只能有一个直接前驱也就是父节点
 * <br>
 * 4) 当我们需要表示多对多的关系时， 这里我们就用到了图。
 * <br>
 * ==========图==========
 * <br>
 * 图是一种数据结构，其中结点可以具有零个或多个相邻元素。两个结点之间的连接称为边。 结点也可以称为
 * 顶点
 * <br>
 * ==========图的表示方式==========
 * <br>
 * 图的表示方式有两种：二维数组表示（邻接矩阵）；链表表示（邻接表）
 * <br>
 * ==========邻接矩阵==========
 * <br>
 * 邻接矩阵是表示图形中顶点之间相邻关系的矩阵，对于 n 个顶点的图而言，矩阵是的 row 和 col 表示的是
 * 1....n个点。
 * <br>
 * ==========邻接表==========
 * <br>
 * 1) 邻接矩阵需要为每个顶点都分配 n 个边的空间，其实有很多边都是不存在,会造成空间的一定损失.
 * <br>
 * 2) 邻接表的实现只关心存在的边，不关心不存在的边。因此没有空间浪费，邻接表由数组+链表组成
 * <br>
 * ==========图的遍历==========
 * <br>
 * 所谓图的遍历，即是对结点的访问。一个图有那么多个结点，如何遍历这些结点，需要特定策略，一般有两种
 * 访问策略: (1)深度优先遍历 (2)广度优先遍历
 * <br>
 * ==========图的深度优先搜索(Depth First Search)==========
 * <br>
 * 1) 深度优先遍历，从初始访问结点出发，初始访问结点可能有多个邻接结点，深度优先遍历的策略就是首先访问
 * 第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点，访问它的第一个邻接结点， 可以这样理解：
 * 每次都在访问完当前结点后首先访问当前结点的第一个邻接结点。
 * <br>
 * 2) 我们可以看到，这样的访问策略是优先往纵向挖掘深入，而不是对一个结点的所有邻接结点进行横向访问。
 * <br>
 * 3) 显然，深度优先搜索是一个递归的过程
 * <br>
 * ==========广度优先搜索(Broad First Search)==========
 * <br>
 * 类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的结点的顺序，以便按这个顺序来
 * 访问这些结点的邻接结点
 */
public class GraphDemo {
    public static void main(String[] args) {
        //  测试一把图是否创建 ok
        int n = 8; // 结点的个数
        //String Vertexs[] = {"A", "B", "C", "D", "E"};
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环的添加顶点
//        for(String vertex: Vertexs) {
//            graph.insertVertex(vertex);
//        }
        // 添加边
        //A-B A-C B-C B-D B-E
        // graph.insertEdge(0, 1, 1); // A-B
        // graph.insertEdge(0, 2, 1); //
        // graph.insertEdge(1, 2, 1); //
        // graph.insertEdge(1, 3, 1); //
        // graph.insertEdge(1, 4, 1); //
        // 更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        // 显示一把邻结矩阵
        graph.showGraph();
        // 测试一把，我们的 dfs 遍历是否 ok
        System.out.println("深度遍历");
        graph.dfs(); // A->B->C->D->E [1->2->4->8->5->3->6->7]
        System.out.println();
        System.out.println("广度优先!");
        graph.bfs(); // A->B->C->D-E [1->2->3->4->5->6->7->8]
    }
}

// 创建一个 Graph
class Graph {
    private int[][] graph; // 表示一个图

    /**
     * 创建一个图，如何需要节点和数组下标的映射关系，添加员工map储存
     *
     * @param size 节点的数量
     */
    public Graph(int size) {
        this.graph = new int[size][size];
    }

    // 插入一个节点
    public void insertVertex() {
        int[][] ints = new int[this.graph.length + 1][this.graph.length + 1];
        for (int i = 0; i < this.graph.length; i++)
            for (int j = 0; j < this.graph.length; j++)
                ints[i][j] = this.graph[i][j];
        this.graph = ints;
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标即使第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        // 此时为无向图，两边都要定义
        this.graph[v1][v2] = weight;
        this.graph[v2][v1] = weight;
    }

    /**
     * 深度优先遍历算法步骤
     * <br>
     * 1) 访问初始结点 v，并标记结点 v 为已访问。
     * <br>
     * 2) 查找结点 v 的第一个邻接结点 w。
     * <br>
     * 3) 若 w 存在，则继续执行 4，如果 w 不存在，则回到第 1 步，将从 v 的下一个结点继续。
     * <br>
     * 4) 若 w 未被访问，对 w 进行深度优先遍历递归（即把 w 当做另一个 v，然后进行步骤 123）。
     * <br>
     * 5) 查找结点 v 的 w 邻接结点的下一个邻接结点，转到步骤 3
     */
    public void dfs() {
        // 储存已经走过的节点，数组下标与图中节点下标相同，1表示已走过，0表示未走过
        Map<Integer, Boolean> map = new HashMap<>();
        // 标记0这个节点已经走过了
        map.put(0, true);
        System.out.print(0 + " ");
        dfs(0, map);
    }

    /**
     * 深度优先遍历
     *
     * @param i   要走的当前节点
     * @param map 储存已经走过的节点
     */
    private void dfs(int i, Map<Integer, Boolean> map) {
        // 遍历全部与i节点相连通的节点
        for (int n = 0; n < graph.length; n++) {
            if (graph[i][n] == 1) { // 如果当前节点i与节点n相连通
                if (map.get(n) == null) { // 如果节点n还未走过，则标记已走过
                    map.put(n, true);
                    System.out.print(n + " ");
                    dfs(n, map); // 因为是深度优先，直接开始递归节点n，再查找与i相连通的节点
                }
            }
        }
    }

    /**
     * 广度优先遍历算法步骤
     * <br>
     * 1) 访问初始结点 v 并标记结点 v 为已访问。
     * <br>
     * 2) 结点 v 入队列
     * <br>
     * 3) 当队列非空时，继续执行，否则算法结束。
     * <br>
     * 4) 出队列，取得队头结点 u。
     * <br>
     * 5) 查找结点 u 的第一个邻接结点 w。
     * <br>
     * 6) 若结点 u 的邻接结点 w 不存在，则转到步骤 3；否则循环执行以下三个步骤：
     * <br>
     * 6.1 若结点 w 尚未被访问，则访问结点 w 并标记为已访问。
     * <br>
     * 6.2 结点 w 入队列
     * <br>
     * 6.3 查找结点 u 的继 w 邻接结点后的下一个邻接结点 w，转到步骤 6。
     */
    public void bfs() {
        // 储存已经走过的节点，数组下标与图中节点下标相同，1表示已走过，0表示未走过
        Map<Integer, Boolean> map = new HashMap<>();
        // 标记0这个节点已经走过了
        map.put(0, true);
        System.out.print(0 + " ");
        List<Integer> list = new ArrayList<>();
        list.add(0);
        do {
            list = bfs(list, map); // 先遍历
        } while (list.size() != 0); // 如果遍历完后没有新节点，遍历结束
    }

    /**
     * 广度优先遍历
     *
     * @param list 要遍历的下标集合
     * @param map  储存已经走过的节点
     */
    private List<Integer> bfs(List<Integer> list, Map<Integer, Boolean> map) {
        // 遍历list内所有未经过的节点，添加入一个新的集合，并返回
        List<Integer> newList = new ArrayList<>();
        for (int i : list) { // 取出list内一个下标
            // 遍历全部与i节点相连通的节点
            for (int n = 0; n < graph.length; n++) {
                if (graph[i][n] == 1) { // 如果当前节点i与节点n相连通
                    if (map.get(n) == null) { // 如果节点n还未走过，则标记已走过
                        map.put(n, true);
                        System.out.print(n + " ");
                        newList.add(n);
                    }
                }
            }
        }
        return newList;
    }

    // 打印图
    public void showGraph() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println();
        }
    }
}
