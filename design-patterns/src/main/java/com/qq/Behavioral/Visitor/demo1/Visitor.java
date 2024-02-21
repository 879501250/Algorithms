package com.qq.Behavioral.Visitor.demo1;

import com.qq.Behavioral.Visitor.demo1.POJO.Candy;
import com.qq.Behavioral.Visitor.demo1.POJO.Fruit;
import com.qq.Behavioral.Visitor.demo1.POJO.Wine;

/**
 * 访问者接口
 */
public interface Visitor {
    // 糖果重载方法
    public void visit(Candy candy);

    // 酒类重载方法
    public void visit(Wine wine);

    // 水果重载方法
    public void visit(Fruit fruit);
}
