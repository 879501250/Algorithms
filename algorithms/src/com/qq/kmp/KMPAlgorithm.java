package com.qq.kmp;

import java.util.Arrays;

/**
 * ========== KMP 算法介绍==========
 * <br>
 * 1) KMP 是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置的经典算法
 * <br>
 * 2) Knuth-Morris-Pratt 字符串查找算法，简称为 “KMP 算法”，常用于在一个文本串 S 内
 * 查找一个模式串 P 的出现位置，这个算法由 Donald Knuth、Vaughan Pratt、James H. Morris
 * 三人于 1977 年联合发表，故取这 3 人的姓氏命名此算法.
 * <br>
 * 3) KMP 方法算法就利用之前判断过信息，通过一个 next 数组，保存模式串中前后最长公共子序列的长度，
 * 每次回溯时，通过 next 数组找到，前面匹配过的位置，省去了大量的计算时间
 * <br>
 * 4) 参考资料：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABCD";
        // String str2 = "BBC";
        int[] next = kmpNext("ABCDABCD"); //[0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));
        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15 了
    }

    /**
     * kmp 搜索算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表, 是子串对应的部分匹配表
     * @return 如果是-1 就是没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
//        char[] chars1 = str1.toCharArray();
//        char[] chars2 = str2.toCharArray();
//        // 遍历源字符串
//        for(int i =0;i<str1.length()-str2.length();){
//            // 一个一个遍历子串
//            for(int j=0;j<str2.length();j++){
//                // 如果不相等，修改源字符串的指针位置，并跳出循环
//                if(chars1[i+j]!=chars2[j]){
//                    // next[j]表示子串当前字符的匹配值，若存在则跳转到该位置的下一个位置
//                    i+=j-next[j]+1;
//                    break;
//                }
//                // 当全部相等时，返回当前源字符串指针位置
//                if(j==str2.length()-1)
//                    return i;
//            }
//        }
        /**
         * 优化
         */
        //遍历源字符串
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整 j 的大小
            //KMP 算法核心点, 可以验证...
            // 一直找到源字符串某段字符与子串前缀相等，或子串当前匹配值为0
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            // 如果相等，匹配值加1
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {//找到了 // j = 3 i
                return i - j + 1;
            }
        }
        // 如果全部不相等
        return -1;
    }

    // 获取到一个字符串(子串) 的部分匹配值表
    public static int[] kmpNext(String dest) {
        // 将字符串转化为字符数组
        char[] chars = dest.toCharArray();
        // 创建一个数值数组储存字符串每个字符对应的匹配值
        // 匹配值k：表示以该字符为终点往前数k-1个字符的后缀与最前面k个字符的前缀相匹配
        //          即当前字符在前缀中的位置（下标+1）吗，若为0表示前缀中没有
        // next数组的意思就是 next[i] = len; 下标为i的字符的前缀和后缀相等的最大长度为len。
        int[] next = new int[dest.length()];
        // 第一个字符的匹配值为0
        next[0] = 0;
        // 定义匹配值k和目前下标i
        int i = 1;
        int k = 0; // k只有当当前字符与前缀最后一个字符相等时才增加
        // 开始为每一个字符赋值匹配值
//        while (i<dest.length()){
//            // 如果当前字符与前缀中k下标的字符相等，则匹配值要加1，并在匹配值表中记录，并进行下次赋值
//            // ABCAB
//            if(chars[i]==chars[k]){
//                k++;
//                next[i]=k;
//                i++;
//            }else{ // 如果匹配值不相等
//                // 如果k为0表示，当前字符前面没有与前缀相匹配，当前字符与第一个字符也不相等
//                // 使用当前字符的匹配值为0，k也不增加。（ABC）
//                if(k==0){
//                    next[i]=k;
//                    i++;
//                }else { // 表示当前字符前面与前缀相等。
//                    while (k!=0){
//                        // 因为当前的k表示上一个字符在前缀中的位置，所以k-1表示在前缀中的下标
//                        // next[k-1]获取到前缀中，与上个字符相等的字符，的匹配值
//                        // 即查看前面的字符是否还能与它的前缀相匹配
//                        // 若不为0则说明会相匹配的，则当前k为相匹配的后一个字符，再与当前字符比较
//                        // ABABCABABABC
//                        // 若为0则说明不会相匹配，前缀中找不到能匹配的，只需和第一个字符比较就能得到其匹配值
//                        // ABCABA
//                        k=next[k-1];
//                        // 如果以当前字符为后缀的字符串能和前缀中的某段前缀对应上，跳出循环，否则一直循环到k为0
//                        if(chars[i]==chars[k])
//                            break;
//                    }
//                    // 如果以当前字符为后缀的字符串能和前缀中的某段前缀对应上，为其赋值匹配值
//                    if(chars[i]==chars[k]) { // ABABCABABABC
//                        k++;
//                        next[i]=k;
//                        i++;
//                    }else { // 此时k一定为0
//                        // 因为不相等，所以k不需要增加
//                        next[i]=k;
//                        i++;
//                    }
//                }
//            }
//        }
        /**
         * 优化上述过程
         */
        while (i < dest.length()) {
            // 当 dest.charAt(i) != dest.charAt(k) ，我们需要从 next[k-1]获取新的 j
            // 直到我们发现 有 dest.charAt(i) == dest.charAt(k)成立才退出
            // 这时 kmp 算法的核心点
            while (k > 0 && dest.charAt(i) != dest.charAt(k)) {
                k = next[k - 1];
            }
            //当 dest.charAt(i) == dest.charAt(k) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(k)) {
                k++;
            }
            next[i] = k;
            i++;
        }
        return next;
    }
}
