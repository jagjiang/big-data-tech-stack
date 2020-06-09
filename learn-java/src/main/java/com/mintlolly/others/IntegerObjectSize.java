package com.mintlolly.others;

/**
 * Create by on jiangbo 2020/6/4 14:03
 * <p>
 * Description: 一个Integer对象24字节，16字节的对象开销
 * 4字节用于保存它的int值，以及4个填充字节
 * 对象的引用一般都是一个内存地址，因此会使用8字节
 *
 */
public class IntegerObjectSize {
    private int x;
}
