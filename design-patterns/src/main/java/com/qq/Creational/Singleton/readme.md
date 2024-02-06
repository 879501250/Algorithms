# 单例模式

## 业务

定义一个全局唯一的缓存

## demo1

使用静态类，在类加载时就初始化，可以保证线程安全。

但实例可能会被修改，如果需要被继承以及需要维持一些特定状态的情况下，就适合使用单例模式。

## demo2

使用了**懒汉模式(线程不安全)**，构造器设置为私有属性，不允许额外创建。

满足懒加载。

但当未加载完成时，有多线程访问时可能会造成不同线程获取到不同的实例。

## demo3

使用了**懒汉模式(线程安全)**，虽然线程安全了，但锁加在方法上，多线程访问效率低下。

## demo4

使用了**饿汉模式(线程安全)**，与使用静态类相识，但保证了线程安全。

不是懒加载。

可能导致启动时就需要消耗大量内存，如果运行时未用到，还会照常浪费。

## demo5

使用 java 并发库提供的原子类来保证并发访问的数据安全性；`AtomicInteger`、`AtomicBoolean`、`AtomicLong`、`AtomicReference`。

使用 CAS 的好处就是不需要使用传统的加锁方式保证线程安全，而是依赖于 CAS 的忙等算法，依赖于底层硬件的实现，来保证线程安全。相对于其他锁的实现没有线程的切换和阻塞也就没有了额外的开销，并且可以支持较大的并发性。

但可能会忙等，如果一直没有获取到将会处于死循环中。

## demo6

对 **demo3** 进行优化，不对整个方法进行加锁，减少了部分获取实例的耗时。

## demo7

使用枚举的方式解决单例模式，保证线程安全、自由串行化、单一实例。

> 约书亚·布洛克（英语：Joshua J. Bloch，1961年8月28日－），美国著名程序员。他为Java平台设计并实作了许多的功能，曾担任Google的首席Java架构师（Chief Java Architect）。

这种写法在功能上与共有域方法相近，但是它更简洁，无偿地提供了串行化机制，绝对防止对此实例化，即使是在面对复杂的串行化或者反射攻击的时候。虽然这中方法还没有广泛采用，但是单元素的枚举类型已经成为实现 Singleton 的最佳方法。

但这种方式存在继承场景下是不可用的。

```java
// 调用方式
@Test
public void test() {
    GlobalCache.INSTANCE.test();
}
```

## demo8

使用内部类既保证了线程安全又保证了懒加载，同时不会因为加锁的方式耗费性能。

> 这主要是因为 JVM 虚拟机可以保证多线程并发访问的正确性，也就是一个类的构造方法在多线程环境下可以被正确的加载。