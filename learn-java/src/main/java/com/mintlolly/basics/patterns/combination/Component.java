package com.mintlolly.basics.patterns.combination;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:组合模式最主要的功能就是让用户可以一致对待整体和部分结构
 * 将两者作为一个相同的组件，所以先新建一个抽象的组件类：
 */
public abstract class Component {
    //职位
    private String position;
    //工作内容
    private String job;

    Component(String position,String job){
        this.position = position;
        this.job = job;
    }
    //做自己的本职工作
    public void work(){
        System.out.println("我是"+position+",我正在"+job);
    }
    abstract void addComponent(Component component);
    abstract void removeComponent(Component component);
    abstract void check();
}
