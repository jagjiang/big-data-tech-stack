##适配器模式
将一个接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能够一起工作
适配器的模式适用于**有相关性但不兼容的结构**，原接口通过一个中间件转换后才能适用于目标接口，这个转换过程就是适配，这个中间件称之为适配器。

适配器模式并不推荐多用。未雨绸缪好过亡羊补牢，如果能事先预防接口不同的问题，不匹配问题就不会发生。
只有遇到源接口无法改变时，才应该考虑使用适配器。