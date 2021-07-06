package com.mintlolly.basics.patterns.builder;

/**
 * Created on 2021/4/23
 *
 * @author jiangbo
 * Description:建造者模式，用于创建过程稳定，但配置多变的对象（例如sparkSession)
 *
 * 复习一下:final 用来说明最终属性 表明一个类不能派生出子类
 * 成员方法不能被覆盖
 * 成员域的值不能改变
 * final类中的所有成员方法都会被隐式指定为final方法
 *
 * 当final修饰一个基本数据类型时，表示该基本数据类型的值一旦在初始化后便不能发生变化
 * 如果final修饰一个引用类型时，则在对其初始化之和便不能再让其指向其他对象了
 * 但该引用所指向的对象的内容是可以发生变化的
 */
public class MilkTea {
    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public boolean isPearl() {
        return pearl;
    }

    public boolean isIce() {
        return ice;
    }

    private final String type;
    private final String size;
    private final boolean pearl;
    private final boolean ice;

    /**
     * 构造方法设置为私有的，所以外部不能通过new 构建出MilkTea实例，只能通过Builder构建
     * 必须配置的属性通过Builder的构造方法传入
     * 可选的属性通过Builder的链式调用方法传入
     * 如果不配置，将使用默认配置
     * @param builder
     */
    private MilkTea(Builder builder) {
        this.type = builder.type;
        this.size = builder.size;
        this.pearl = builder.pearl;
        this.ice = builder.ice;
    }

    public static class Builder{
        private final String type;
        private String size = "中杯";
        private boolean pearl = true;
        private boolean ice = false;

        public Builder(String type) {
            this.type = type;
        }

        public Builder size(String size){
            this.size = size;
            return this;
        }
        public Builder pearl(boolean pearl){
            this.pearl = pearl;
            return this;
        }

        public Builder ice(boolean ice){
            this.ice = ice;
            return this;
        }

        public MilkTea build(){
            return new MilkTea(this);
        }
    }
}
