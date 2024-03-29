# 结构型模式

## Adapter

### 概述

**适配器模式**的主要作用就是把原本不兼容的接口，通过适配修改做到统一，即使不兼容的两个类可以一起工作。

适配器类实现用户目标接口，在该接口的实现类中调用被适配者，实现了接口转接的效果；使用的时候，通过创建适配器类，即可间接调用被适配者方法 ;

> 适配器不只是可以适配接口往往还可以适配一些属性信息。

分类：

- **类适配器：**继承被适配者 , 通过 super 访问被适配者方法 ;
- **对象适配器（推荐）：**在适配器中维护一个被适配者成员变量，通过成员变量访问被适配者方法 ;

例如：将两脚插座的电器插到三脚插座上。

### 对比

#### 外观模式

##### 相同点

都是对现有类进行封装

##### 行为分析

- **外观模式：**外观模式定义了新接口，处理多个子系统之间的交互
- **适配器模式 :** 适配器模式复用原有接口，只是简单的转接了一下，使两个现存接口（现有类和目标类）协同工作 ;

##### 适配力度分析

- **外观模式：**适配力度很大，需要开发整个子系统之间的交互流程
- **适配器模式：**修改很少的内容，只是进行简单的接口转接交互，一般不实现具体的功能 ;

### 使用场景

- 功能正确但接口不匹配：对于之前开发好的类，该类的操作和返回值都是正确的，但是其定义的方法接口无法调用，此时使用适配器模式，使该类与用户的接口匹配，让用户使用适配器的接口，从而间接调用该类 ;
- 适配器模式使用阶段：软件设计**开发阶段一般不使用适配器模式**，在软件维护时，出现操作和返回值类似，但是函数接口不同，为了适配第三方系统的接口，使用适配器模式 ;

### 优点

- 复用且不修改类：在不改变现有类的基础上，提高类的复用性、透明性，并让现有类与目标类接口匹配
- 降低耦合：目标类（用户调用的接口所在类）和现有类（被适配者）解除耦合，降低了系统的耦合性，易于扩展维护
- 符合开闭原则：用户调用适配器接口，只与适配器类进行交互，如果需要修改扩展，只需要修改适配器类即可，目标类和现有类各自都是相互独立的，互不影响 ;

### 缺点

- 增加复杂性：编写适配器类时，需要考虑全面，包括被适配者和目标类，系统复杂性会增加
- 降低可读性：系统代码可读性降低（阅读代码时，调用系统接口，如果调用的是适配器接口，还要到处找调用的是哪个现有类的实际接口），可维护性降低 ;

## Bridge

### 概述

**桥接模式**的主要作用就是通过将抽象部分与实现部分分离，进行解耦从而实现独立变化，并把多种可匹配的使用进行组合。

> 核心实现是组合而非继承，即在 A 类中添加 B 类接口，通过构造函数传递 B 类的实现，这个 B 类就是设计的**桥**。

例如：开发跨平台的视频播放器，平台有 Android、iOS、Windows、Linux、Mac，播放器支持的格式有 MP4、AVI、RMVB、FLV 格式，可将播放器格式抽象为接口内置于平台类中，需要使用哪种格式传入就行。

### 对比

#### 组合模式

- **组合模式**更强调部分与整体间的组合
- **桥接模式**更强调平行级别上，不同类的组合

#### 适配器模式

- **共同点：**都是让两个类配合工作
- **不同点：**二者目的不同
  - **适配器模式**是改变已有接口，让二者之间相互配合，目的是配合
  - **桥接模式**分离抽象和具体的实现，目的是分离

### 使用场景

- 当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
- 当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
- 当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。

### 优点

- 可以取代多层继承方案，多层继承方案违背了**单一职责原则**，复用性较差，且类的个数非常多，桥接模式是比多层继承方案更好的解决方法，使用组合关系解耦了抽象和实现之间的继承关系，使抽象和实现可以各自沿着自己的维度进行扩展，并随意进行组合，能获得多维度的组合对象，极大减少了子类的个数
- 提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统，符合**开闭原则**

### 缺点

- 需要正确地识别出系统中两个独立变化的维度
- 增加系统的理解与设计难度
- 使用范围有一定局限性

## Composite

### 概述

**组合模式**是一种将对象组合成树状的层次结构的模式，用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性。

树中的节点和叶子对象都是某一接口或类的子类，从而忽略了个体之间的差异，顶级接口中的部分方法因为叶子节点不需要（添加删除等方法只有节点要用到），可以将该方法写出一个空实现，这样叶子节点继承时就不会被迫实现不需要的方法了。

例如：文件系统中的目录结构，有文件夹也有具体文件。

### 对比

#### 访问者模式

两个模式经常结合起来使用，使用**访问者模式**，访问**组合模式**中的递归结构

### 使用场景

- 部分与整体的层次关系表示为树形结构
- 部分与整体的对象，是能让客户端能统一对待、不需区分的对象

### 优点

- 对服务端来说，以树形结构清晰定义了整体和部分的层次关系，只需要知道自己的父节点就可以自由添加子节点。
- 对客户端来说，可以忽略整体和部分的差异，不需关心是单个对象还是组合对象，简化了逻辑。
- 更容易在组合体内加入新的对象，客户端不会因为加入了新的对象而更改源代码，满足**开闭原则**

### 缺点

- 设计较复杂，客户端需要花更多时间理清类之间的层次关系；
- 不容易限制容器中的构件；
- 不容易用继承的方法来增加构件的新功能；

## Decorator

### 概述

**装饰者模式**的核心就是在不改原有类的基础上动态地给类新增功能，被装饰的类通过构造器传入。与继承、AOP切面相似，但使用装饰器模式可以避免继承导致的子类过多，也可以避免AOP带来的复杂性。

装饰者和被装饰者之间必须是一样的类型，也就是要有共同的超类，是为了实现类型的匹配。因为当装饰者和被装饰者是同一个类型时，装饰者就可以替代被装饰者了。根据装饰者模式的理念，在任何时候实现新的装饰者增加新的行为，而如果是用继承，每当需要增加新的行为时，就要修改原程序了。

例如：咖啡加糖或加奶

### 对比

#### 代理模式

- **注重点：** 装饰者模式注重动态扩展功能；代理模式注重对对象访问的控制 , 代理类对用户隐藏具体细节
- **实例对象的使用：**装饰者模式将原始对象作为参数传递给装饰者类构造器；代理模式中在代理类中创建实例对象

#### 适配器模式

- **装饰者模式：**装饰者和被装饰者实现相同的接口，或装饰者是被装饰者的子类
- **适配器模式：**适配器和被适配的类有不同的接口，可能有部分接口重合

### 使用场景

- 为一个类扩展功能，可以动态添加或撤销额外的职责

### 优点

- 相较于继承能在完成功能逻辑的扩展下，而不影响原本的类
- 可以按需在运行时添加和删除扩展的逻辑
- 对装饰类进行各种排列组合，可实现不同的扩展功能
- 被装饰的类，和装饰类相互独立，互不干扰，符合**开闭原则**

### 缺点

- 需要编写更多的代码，生成更多的类，程序的复杂性增加了

## Facade

### 概述

**外观模式**也叫门面模式，主要解决的是降低调用方的使用接口的复杂逻辑组合。提供一个统一接口，用于访问子系统中的一群接口。让子系统更容易使用，隐藏内部逻辑。

例如：电脑开机不用挨个启动内部组件，通过开机键自动帮我们启动所有组件。

### 对比

#### 中介者模式

- **外观模式：**外观模式关注外部用户与子系统之间的交互

- **中介者模式：**中介者模式关注子系统内部之间的交互

#### 单例模式

外观类一般会被定义成单例类

#### 抽象工厂模式

外观类中，使用抽象工厂模式获取子系统的实例对象，子系统内部可以屏蔽外观类

### 使用场景

- 子系统复杂，且多个子系统中的接口配合才能完成一个功能

### 优点

- 只与外观对象进行交互，不与复杂的子系统直接进行交互，降低了系统间的依赖，使耦合关系更低，子系统内部的模块更容易扩展和维护
- 简化复杂系统的调用过程，无需对子系统进行深入了解，即可完成调用 
- 将提供给外部的功能定义在外观类中，能将系统内部的细节隐藏起来

### 缺点

- 系统内部扩展子系统时，容易产生风险
- 扩展子系统时，不符合**开闭原则**

## Flyweight

### 概述

**享元模式**主要在于共享通用对象，减少内存的使用，提升系统的访问效率。核心是**对象池**，使用对象时，先从对象池中获取对象，如果对象池中没有，创建一个，放入对象池，然后再从对象池中获取（只能从对象池中拿对象，不能自己创建）

例如：Java 的 String 类型，内部的字符串缓存池就是一个享元工厂

### 对比

#### 代理模式

代理模式需要代理某个类，生成该类需要花费较多的资源和时间，可以使用享元模式提高处理速度

#### 单例模式

容器单例模式 , 复用对象

### 应用场景

- 某个系统的底层开发 , 对性能要求比较高
- 实例中对象数量庞大，且可复用

### 优点

- 减少创建对象的数量，从而减少内存的占用，提高性能
- 创建对象时需要占用一定的开销，如 new 操作，可能构造函数中有访问文件、数据库、网络等操作，可以避免这些开销

### 缺点

- 在类中为了追求性能，一般使用的是 HashMap、ArrayList 等数据，这些数据结构都是线程不安全的；使用 HashTable、 Vector 线程安全了，但是性能会下降很多；折中使用 ConcurrentHashMap 等 concurrent 包下的集合
- 提高了系统的复杂度，需要分离出外部状态和内部状态，而且外部状态具有固有化的性质，不应该随着内部状态的变化而变化，否则会造成系统的混乱

## Proxy

### 概述

**代理模式**主要解决的问题是为某些资源的访问、对象的类的易用操作上提供方便使用的代理服务。即提供一种代理以控制对某个对象的访问。

代理对象在客户端和目标对象之间起到中介的作用。

例如：

- 要买辆二手车，但还要做质量检测等一系列的车辆过户流程，于是找中介，我只要付钱就行，其他工作由中介完成

#### 分类

##### 静态代理

在代码中使用指定的代理，显示的定义了一个业务实现类代理，在代理类中，对同名的业务方法进行包装，用户通过调用代理类中被包装过的业务逻辑方法来调用被包装对象的业务方法，同时对目标对象的业务方法进行增强

##### 动态代理

由 JDK 提供，只能对实现的接口的类进行动态代理 , 不能代理具体的实现类。通过接口中的方法名，在动态生成的代理类中，调用业务实现类的同名方法。

JDK 动态代理用到的代理类是在程序调用到代理对象时，由 Java 虚拟机创建，Java 虚拟机根据传入的业务实现类对象以及方法名，动态地创建代理类 Class 文件，当该 Class 文件被字节码引擎执行，通过该代理类对象进行目标方法的调用。

**动态代理无法代理类 , 只可以代理接口。**

##### CGLib 代理

**可以针对类实现进行代理。**

如果要代理一个类，CGLib 会生成一个被代理类的子类，通过继承该类并覆盖其中的方法。如果该类是 final 的，则无法被继承，如果类中的方法是 final 的，该方法无法被重写，因此**使用 CGLib 代理要特别注意 final 修饰符**

### 对比

#### 装饰者模式

- **装饰者模式**是为目标对象添加行为，被装饰者通过构造器传入，
- **代理模式**目的是进行控制访问，代理模式注重通过设置代理对象的方式，增强目标对象，一般是增强目标对象的某些行为，目标对象由内部创建。

#### 适配器模式

- **适配器模式**主要改变所考虑对象的接口
- **代理模式**不能改变 理类的接口

### 应用场景

- 客户端只与代理类进行交互，不清楚目标对象的具体细节，能够保护目标对象。
- 在目标对象的基础上，对目标对象的功能进行增强

### 优点

- 能将代理对象与真实被调用的目标对象分离，保护目标对象
- 可以在目标对象基础上，添加新的功能

### 缺点

- 类的个数增加
- 增加代理对象，处理速度变慢