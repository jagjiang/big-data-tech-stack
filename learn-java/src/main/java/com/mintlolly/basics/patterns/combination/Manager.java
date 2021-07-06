package com.mintlolly.basics.patterns.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:管理者继承抽象类
 */
public class Manager extends Component{
    //管理的组件
    private List<Component> components = new ArrayList<>();

    Manager(String position, String job) {
        super(position, job);
    }

    @Override
    void addComponent(Component component) {
        components.add(component);
    }

    @Override
    void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    void check() {
        work();
        for(Component component:components){
            component.check();
        }
    }
}
