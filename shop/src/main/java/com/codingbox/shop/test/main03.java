package com.codingbox.shop.test;

public class Main03 {
    public static void main(String[] args) {
        // none param
        test();
        // param one
        test("a");
        // param two
        test("a","b");
        // array test
        test(new String[] {"A","B","C","D","E","F","G","H","I","J"});
        // none param
        test2(1);
        // param one
        test2(2,"a");
        // param two
        test2(3,"a","b");
        // array test
        test2(4,new String[] {"A","B","C","D","E","F","G","H","I","J"});
    }
    public static void test(String... param) {
        System.out.println("param test");
        String[] array = param;
        for(String str : param) {
            System.out.println("str : " + str);
        }
    }
    // 다른 파라미터와 가변인자를 같이 사용하는 경우에는
    // 가변 인자를 제일 뒤에 위치 시켜야 한다.
    public static void test2(int num ,String... param) {
        System.out.println("param2 test");
        System.out.println("num : "+num);
        String[] array = param;
        for(String str : param) {
            System.out.println("str : " + str);
        }
    }
}
