package com.mintlolly.basics.patterns.combination;

/**
 * Created on 2021/4/26
 *
 * @author jiangbo
 * Description:
 */
public class Employee extends Component{

    Employee(String position, String job) {
        super(position, job);
    }

    @Override
    void addComponent(Component component) {
        System.out.println("职员没有管理权限");
    }

    @Override
    void removeComponent(Component component) {
        System.out.println("职员没有管理权限");
    }

    @Override
    void check() {
        work();
    }
}
