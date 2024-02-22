# 策略模式

## 业务

日常购物时会寻找各种优惠卷，但优惠卷的策略有很多如直减券、满减卷、折扣卷、n元购等。

## demo1

提供一个促销活动服务（PromotionService），计算不同活动下的折扣金额。

优惠券的设计最初可能非常简单，就是一个金额的抵扣，没有现在这么多种类型，因此折扣算法写在业务中，但随着产品功能的增加，不断的扩展将会不断增加 ifelse 语句。

## demo2

使用**策略模式**和**工厂模式**。

将促销活动抽象为一个接口（PromotionStrategy），具体促销活动的逻辑算法由其实现类决定，即不同策略。

创建一个工厂类（PromotionStrategyFactory），根据传入的活动类型不同，返回不同的策略对象。

用户无需关心策略的具体逻辑及创建过程，只要知道使用何种策略即可。