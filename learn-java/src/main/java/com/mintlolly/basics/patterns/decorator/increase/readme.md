###用于添加功能的装饰模式
在接口中新增了方法：hangThings,然后在装饰器中将House类包装起来，之前House中的方法仍然调用house去执行，也就是说我们并没有修改原有的功能，只扩展了新的功能，这种模式在装饰模式中称为**半透明装饰模式**。

**半透明:**由于新的接口IStickyHookHouse拥有之前IHouse不具有的方法，所以需要使用装饰器中添加的功能，就不得不区别对待**装饰前的对象和装饰后的对象**。也就是说客户端要使用新方法，必须要知道具体的装饰类StickyHookDecorator,所以这个装饰器对客户端来说是可见的、不透明的。而被装饰着不一定是House,它可能是实现了IHouse接口的任意对象，所以被装饰者对客户端是不可见的、透明的。由于一半透明，一半不透明，所以称之为半透明装饰模式。

**半透明装饰模式中，我们无法多次装饰**

eg:
```java
IStickyHookHouse stickyHookHouse = new StickyHookDecorator(house);
IMirrorHouse houseWithStickyHookMirror = new MirrorDecorator(stickyHookHouse);
houseWithStickyHookMirror.live();
houseWithStickyHookMirror.hangThings();// 这里会报错，找不到 hangThings 方法houseWithStickyHookMirror.lookMirror();
```
第二次装饰时无法获得上一次装饰的方法。

能否让IMirrorHouse继承IStickyHookHouse以实现新增两个功能呢？

可以，但那样两个装饰类之间就有了依赖关系，那就不是装饰模式了。装饰类不应该存在依赖关系，而应该在原本的类上进行装饰这就意味着**半透明装饰模式中，我们无法多次装饰**

只要添加了新功能的装饰模式都称为**半透明装饰模式**，它们都具有不可以多次装饰的特点。仔细理解上文半透明名称的由来就知道了，“透明”指的是我们无需知道被装饰者具体的类，既增强了功能，又添加了新功能的装饰模式仍然具有半透明特性。