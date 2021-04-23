package com.mintlolly.basics.container;

/**
 * Create by on jiangbo 2020/5/27 16:13
 * <p>
 * Description:
 */
public class AnyTest {
    public static void main(String[] args) {
//        System.out.println((char)('a'+4));
//        System.out.println(Math.sqrt(6));
//        int a = -2147483648;
//        System.out.println(a);
        System.out.println(isLongPressedName("saeedi", "ssaaeediixxxiii"));

        System.out.println(isPalindrome(121421));

    }

    public static int[] returnArray() {
        return new int[]{1, 3};
    }

    public static boolean isLongPressedName(String name, String typed) {
        char[] names = name.toCharArray();
        char[] typeds = typed.toCharArray();
        int num = 0;
        char e = ',';
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1 && num == typeds.length) {
                return false;
            }
            for (int j = num; j < typeds.length; j++) {
                num += 1;
                if (names[i] == typeds[j]) {
                    e = names[i];
                    if (i != names.length - 1) {
                        break;
                    }
                } else if (j == typeds.length - 1 && names[i] != typeds[j]) {
                    return false;
                } else if (e != typeds[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }else {
            char[] chars = String.valueOf(x).toCharArray();
            int i = 0;
            int j = chars.length -1;
            do{
                if(chars[i] == chars[j]){
                    i++;
                    j--;
                    continue;
                }else {
                    return false;
                }

            }while (i <= j);
            return true;
        }
    }
}
