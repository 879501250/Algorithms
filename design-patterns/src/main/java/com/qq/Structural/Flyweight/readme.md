# 享元模式

## 业务

浏览一个商品详情，需要从数据库查询商品信息，再传到前端进行渲染。

定义了一个商品类（Goods），其中包含了商品基本信息和库存类（Stock）

## demo1

每次都从数据库查询商品信息。

## demo2

当用户越来越多时，对数据库的压力太大，而商品详情一般是不会改变的，只有库存才会经常变化，因此可以将商品详细信息缓存起来，商品库存存储在 Redis 中。

提供的是一个享元工厂（GoodsFactory），通过 map 结构存放已经从库表或者接口中查询到的数据，存放到内存中，用于下次可以直接获取。

创建一个 redis 工具，模拟从 redis 中获取商品库存信息