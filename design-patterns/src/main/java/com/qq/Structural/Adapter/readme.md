# 适配器模式

## 业务

系统之间通过 MQ 传递消息，但各系统之间的 MQ 数据格式不同，如果一个个的去开发，就会耗费很大的成本，同时对于后期的拓展也有一定的难度。因此需要增加一个系统把外部的 MQ 接入后进行统一配置（对属性进行适配），对后台接口进行整合（对接口进行适配）。

mq 下的三个类模拟了三个不同类型的 MQ 消息，而在消息体中都有一些必要的字段，比如用户 ID、时间、业务 ID，但是每个 MQ 的字段属性并不一样。就像用户 ID 在不同的 MQ 里也有不同的字段：uId、userId 等。

service 下提供了两个不同类型的接口，一个用于查询内部订单订单下单数量，一个用于查询第三方是否首单。

## demo1

目前需要接收三种MQ消息，所有就有了三个对应的类（NewAccountService、OrderMqService、POPOrderDeliveredService），当 MQ 数量少时这样的写法没什么问题，但是随着数量的增加，就需要考虑用一些设计模式来解决。

提供一个统一的接口（OrderController），通过 ifelse 的方式调用后端不同的接口，同样随着后端数量的增加，不好维护。

## demo2

使用**适配器模式**。

创建了一个统一的 MQ 消息体（MQInfo），用于把所有接入进来的不同格式的 MQ 消息进行统一的处理，通过传入映射管理 map，将原始 mq 中的属性名称映射为统一 mq 消息体中的属性名称。

```java
// 单元测试
@Test
public void test_MQAdapter() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    NewAccount newAccount = new NewAccount();
    newAccount.setNumber("100001");
    newAccount.setAddress("河北省.廊坊市.广阳区.大学里职业技术学院");
    newAccount.setAccountDate(new Date());
    newAccount.setDesc("在校开户");

    HashMap<String, String> link01 = new HashMap<String, String>();
    link01.put("userId", "number");
    link01.put("bizId", "number");
    link01.put("bizTime", "accountDate");
    link01.put("desc", "desc");
    MQInfo mqInfo01 = MQAdapter.filter(newAccount.toString(), link01);
    System.out.println("mq.create_account(适配前)" + newAccount.toString());
    System.out.println("mq.create_account(适配后)" + JSON.toJSONString(mqInfo01));

    System.out.println("");

    OrderMq orderMq = new OrderMq();
    orderMq.setUid("100001");
    orderMq.setSku("10928092093111123");
    orderMq.setOrderId("100000890193847111");
    orderMq.setCreateOrderTime(new Date());

    HashMap<String, String> link02 = new HashMap<String, String>();
    link02.put("userId", "uid");
    link02.put("bizId", "orderId");
    link02.put("bizTime", "createOrderTime");
    MQInfo mqInfo02 = MQAdapter.filter(orderMq.toString(), link02);

    System.out.println("mq.orderMq(适配前)" + orderMq.toString());
    System.out.println("mq.orderMq(适配后)" + JSON.toJSONString(mqInfo02));
}
```

定义了一个统一适配接口（OrderAdapterService），无需在业务层编写 if 语句判断调用后端哪种接口，只需调用统一接口的具体实现类即可，具体的逻辑包装到实现类中，满足**单一职责**。

```java
// 单元测试
@Test
public void test_itfAdapter() {
    OrderAdapterService popOrderAdapterService = new POPOrderAdapterServiceImpl();
    System.out.println("判断首单，接口适配(POP)：" + popOrderAdapterService.isFirst("100001"));   

    OrderAdapterService insideOrderService = new InsideOrderService();
    System.out.println("判断首单，接口适配(自营)：" + insideOrderService.isFirst("100001"));
}
```

