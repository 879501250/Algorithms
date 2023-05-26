package com.qq.stack;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class a {
    public static void main(String[] args) {
        Thread time = new Thread() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("HH:mm:ss");
                Date date;
                while (true) {
                    date = new Date();
                    System.out.println(sdf.format(date));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        time.start();
    }
}
