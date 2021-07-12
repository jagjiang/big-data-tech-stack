##包含建造者模式以及原型模式
###建造者模式
现在的建造者模式主要用来通过链式调用生产不同的配置。
将MilkTea的构造方法设置为私有的，所以外部不能通过new来构建MilkTea实例，只能通过Builder构建。
对于必须配置的属性，通过Builder的构造方法传入，可选的属性通过Builder的链式调用方法传入，如果不配置，将使用默认配置

使用建造者模式的好处是不用担心忘了指定某个配置，保证了构建过程是稳定的。在OkHttp、Retrofit等著名框架的源码中都使用了建造者模式

###原型模式
用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
Java中Object的clone方法就属于原型模式。
注意：java的赋值只是传递地址。
举例：```MilkTea youMilkTea = milkTeaOfJay;```并不会对一份一样的奶茶。
如果有1000人要一样的奶茶，就需要new 1000次，并为每一个新的对象赋值，造成大量的重复。
如果临时决定加个冰，那么所有的也要跟着改。
大批量修改无疑是非常丑陋的做法，所以需要clone方法。

MilkTea中新增clone方法，在这里要注意建造者模式已经私有化了，所以并不可行
```java
public class MilkTea{
    public String type;
    public boolean ice;
    public MilkTea clone(){
        MilkTea milkTea = new MilkTea();
        milkTea.type = this.type;
        milkTea.ice = this.ice;
        return milkTea;
    }
}
//java自带继承Cloneable
public class MilkTea implements Cloneable{
    public String type;
    public boolean ice;

    @Override
    protected MilkTea clone() throws CloneNotSupprotedException{
        return (MilkTea) super.clone();
    }
}
```
