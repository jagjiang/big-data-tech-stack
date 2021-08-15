package com.mintlolly.leetcode.medium;

/**
 * Created on 2021/7/6
 *
 * @author jiangbo
 * Description:给定一个数组
 * order[i] = [CustomerName,tableNumber,foodItem]
 * 请返回该餐厅的点菜展示表
 * 第一行为标题，第一列为餐桌号“Table",后面每一列是按字母顺序排列的餐品名称
 * 接下来每一行表示每张餐桌订购的相应的餐品数量
 *
 * ex:输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DisplayTable {
}
