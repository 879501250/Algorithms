# 访问者模式

## 业务

模拟超市结账，超市内有不同的商品（水果，酒，糖果），不同商品的结账方式不同（水果按斤卖，啤酒论瓶卖）

## demo1

使用**访问者模式**。

提供抽象商品类（Product），包含商品的基本信息，具体商品分别为不同的实现类，包含其特有的属性。

声明一个访问者接口（Visitor），使用**重载**的方式提供对不同商品的访问逻辑，具体的访问逻辑由实现类决定，创建一个计算商品打折信息的访问者实现类（DiscountVisitor），对不同的商品进行折扣计算。

目前已经实现了选择不同商品进行结算的功能，但目前只支持单个商品结算。

```java
//小黑兔奶糖，生产日期：2018-10-1，原价：￥20.00
Candy candy = new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f);
Visitor discountVisitor = new DiscountVisitor(LocalDate.of(2019, 1, 1));
discountVisitor.visit(candy);
```



若购物车内包含多种不同类型的商品，则需要将商品放在一个 List 中，但不同商品类型不同，List 只能使用泛型设置存储 Product。

```java
// 三件商品加入购物车
List<Product> products = Arrays.asList(
    new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f),
    new Wine("猫泰白酒", LocalDate.of(2017, 1, 1), 1000.00f),
    new Fruit("草莓", LocalDate.of(2018, 12, 26), 10.00f, 2.5f)
);
```



但由于使用了泛型，从购物车中取出的商品类型为 Product，访问者无法判断商品类型，不能知道该调用哪个接口对商品进行结算，因此遍历每个商品，判断商品的类型然后再调用访问者中**重载**的不同接口进行结算，日后商品增多了 ifelse 语句也相应增加，不好维护。

```java
// 迭代购物车轮流结算
for (Acceptable product : products) {
    if (product instanceof Candy) {
        discountVisitor.visit((Candy) product);
    } else if (product instanceof Wine) {
        discountVisitor.visit((Wine) product);
    } else if (product instanceof Fruit) {
        discountVisitor.visit((Fruit) product);
    }
}
```



于是利用“**双派发**“（double dispatch）巧妙地绕过这个错误，访问者无法判断资源类型，但被访问者知道，因此让被访问者自己通知访问者调用。

定义一个接待者接口（Acceptable），被访问者即商品类需要实现该接口，具体方法逻辑为主动将自己交给访问者进行访问。