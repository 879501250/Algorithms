package com.qq.Structural.Composite.demo2;

/**
 * 公司组织或部门
 */
public interface Company {

    /**
     * 添加子架构
     * 因为叶子节点不需要，所以将该方法写出一个空实现，这样叶子节点继承时就不会被迫实现不需要的方法了
     *
     * @param company
     */
    default void addOrg(Company company) {
        throw new UnsupportedOperationException();
    }

    /**
     * 移除子架构
     * 因为叶子节点不需要，所以将该方法写出一个空实现，这样叶子节点继承时就不会被迫实现不需要的方法了
     *
     * @param company
     */
    default void removeOrg(Company company) {
        throw new UnsupportedOperationException();
    }

    /**
     * 打印组织架构，不管子节点还是叶子节点都需要实现
     *
     * @param depth
     */
    void showOrg(int depth);

    default void print(int dept) {
        while (dept-- > 0) {
            System.out.print("-");
        }
    }
}
