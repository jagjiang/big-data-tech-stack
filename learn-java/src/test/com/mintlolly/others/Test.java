package com.mintlolly.others;

/**
 * Created on 2022/3/7
 *
 * @author jiangbo
 * Description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(convertToBase7(-7));
    }
    public static String convertToBase7(int num) {
        if(num == 0){
            return "0";
        }
        StringBuilder sb =new StringBuilder();
        String d = "";
        if(num < 0){
            d = "-";
        }
        num = Math.abs(num);
        while(num > 0){
            sb.append(num % 7);
            num = num / 7;
        }
        sb.append(d);
        return sb.reverse().toString();
    }
}
