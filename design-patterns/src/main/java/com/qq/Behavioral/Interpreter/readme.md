# 解释器模式

## 业务

开发一款控制鼠标的程序，通过发送指令实现鼠标的点击、移动操作，指令规则如下

``` bat
BEGIN                // 脚本开始
MOVE 500,600;        // 鼠标移动到坐标(500, 600)
    BEGIN LOOP 5     // 开始循环5次
        LEFT_CLICK;  // 循环体内单击左键
        DELAY 1;     // 每次延时1秒
    END;             // 循环体结束
RIGHT_DOWN;          // 按下右键
DELAY 7200;          // 延时2小时
END;                 // 脚本结束
```

## demo1

使用**解释器模式**，将指令抽象为一个接口（Expression），提供一个指令执行的方法，具体效果由子类实现，针对不同指令创建不同的实现类。

最终安装脚本顺序依次创建指令类并执行。
