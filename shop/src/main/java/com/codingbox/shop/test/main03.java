package com.codingbox.shop.test;

public class Main03 {

	public static void main(String[] args) {
		// 파라미터가 없는
		test();
		
		// 파라미터가 한개
		test("A");
		
		// 파라미터가 두개
		test("A","B");
		
		// 배열로 전달
		test(new String[] {"A","B","C"});
		
		test(5, "A","B");
		
		test(5);
	}
	
	public static void test(String... param) {
		System.out.println("---param 실행---");
		String[] array = param;
		for(String str : param) {
			System.out.println( "str : " + str);
		}
	}
	
	// 다른 파라미터와 가변인자를 같이 사용하는 경우에는
	// 가변인자를 제일 뒤에 위치 시켜야 한다.
	public static void test(int num, String... param) {
		System.out.println("---param2 실행---");
		System.out.println("num : " + num);
		String[] array = param;
		for(String str : param) {
			System.out.println( "str : " + str);
		}
	}

}







