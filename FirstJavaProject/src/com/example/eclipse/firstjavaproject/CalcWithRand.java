package com.example.eclipse.firstjavaproject;

public class CalcWithRand {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int num1 = (int) (Math.random() * 10);
		int num2 = (int) (Math.random() * 10);
		Addition add = new Addition();
		int ans = add.plus(num1, num2);
		System.out.println("答えは" + ans);
	}

}
