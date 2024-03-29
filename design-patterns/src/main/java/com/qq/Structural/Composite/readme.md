# 组合模式

## 业务

构建公司的组织架构，公司总部下面有人力资源部、财务部等，并可以灵活的新增、删除、展示部门。

## demo1

将公司和部门分别抽象为不同的类型（Organization、Department），结构清晰且功能各司其职。

## demo2

随着公司规模不断扩大，原本的架构不能满足需求，总部下面不仅有部门，还开始出现分公司，之前的设计明显是不可以用了，接口 `Organization` 中 `addOrg`、`removeOrg` 方法都是面对于 `Department` 的。现在需要**不区分部门、分公司和总部，且组织结构是用树形结构来体现层次关系的**。

因此使用**组合模式**，总部下可以添加分公司和部门，分公司下也可以添加分公司和部门，甚至部门下也可以添加分公司和部门，做到了不用区分整体和部分。只是部门下添加分公司和部门是不起作用的。

