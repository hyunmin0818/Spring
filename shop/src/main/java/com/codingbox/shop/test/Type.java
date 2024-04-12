package com.codingbox.shop.test;

public enum Type {
	Walking("워킹화"),
	Running("러닝화"),
	Tracking("트래킹화"),
	Hiking("등산화");
	
	final private String name;
	private Type(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
