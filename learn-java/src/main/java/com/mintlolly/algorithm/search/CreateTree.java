package com.mintlolly.algorithm.search;

/**
 * Created on 2022/2/21
 *
 * @author jiangbo
 * Description:
 *               1
 *          2(c)    5(f)
 *       3(b)     6(d)  7(e)
 *     4(a)
 */
public class CreateTree {
    public Node createTree(){
        Node a = new Node(4,null,null);
        Node b = new Node(3,a,null);
        Node c = new Node(2,b,null);
        Node d = new Node(6,null,null);
        Node e = new Node(7,null,null);
        Node f = new Node(5,d,e);
        return new Node(1,c,f);
    }
}
