package com.qq.test;

import com.qq.utils.JarScanner;

import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

/**
 * 算法测试类 //TODO <br><br>
 *
 * <p>
 * Class detailed description, effection, usage,etc. //TODO
 * </p >
 * <br>
 * author: qiqin 2023/5/29 11:07
 */
public class Test {

    public static void main(String[] args) throws Exception {
        
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashCode() {
        return val;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

