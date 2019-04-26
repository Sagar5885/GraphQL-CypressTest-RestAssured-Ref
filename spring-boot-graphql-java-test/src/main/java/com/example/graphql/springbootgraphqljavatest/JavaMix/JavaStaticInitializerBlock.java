package com.example.graphql.springbootgraphqljavatest.JavaMix;

import java.util.Scanner;

public class JavaStaticInitializerBlock {
    static Scanner sc = new Scanner(System.in);

    static int B = sc.nextInt();
    static int H = sc.nextInt();

    static boolean flag = (B>0 && H>0) ? true : false;

//    public static void main(String[] args){
//        if(B>0 && H>0){
//            int area=B*H;
//            System.out.print(area);
//        }else {
//            System.out.println("java.lang.Exception: Breadth and height must be positive");
//        }
//    }

    public static void main(String[] args){
        if(flag){
            int area=B*H;
            System.out.print(area);
            Integer.parseInt("100");

        }
    }
}
