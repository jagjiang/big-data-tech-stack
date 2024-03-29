#组合模式
组合模式和桥接模式的组合完全不一样。组合模式用于**整体与部分的结构**，当整体与部分有相似的结构，在操作时可以被一致对待时，就可以使用组合模式。
eg:文件夹与子文件夹

组合模式中的安全方式与透明方式

Employee类虽然继承了父类的addComponent和removeComponent方法，但是仅仅提供了一个空实现，
因为Employee类是不支持添加和移除组件的，这样违背了接口隔离原则

这种方式在组合模式中被称为透明方式。

透明方式：在Component中声明所有管理子对象的方法包括add、remove等，这样继承自Component的子类都具备了
add remove方法。对于外界来说是透明的，他们具备完全一致的接口
```java
Component boss = new Manager();
Component HR  = new Employee();
```
安全方式：解决方法是从Component类中add和remove方法移到Manager子类中单独实现

安全方式遵循了接口隔离原则，但由于不够透明，Manager和Employee类不具有相同的接口，在客户端中
无法将Manager和Employee统一声明为Component类了，必须要区别对待，使用上不方便
```java
Manager boss = new Manager();
Employee HR  = new Employee();
```
安全方式和透明方式各有好处，在使用组合模式时，需要根据实际情况决定。但大多数使用组合模式的场景都是采用透明方式，
虽然有点不安全，但是客户端无需做任何判断来区分时叶子节点还是枝节点