package com.uni.member.model.exception;

public class MemberException extends Exception {// Exception 만들어서 사용
	public MemberException() {//기본 생성자 추가
		
	}

	public MemberException(String message) {// String message 을 받아서 처리하는 Exception 
		super(message);
		
	}
	
}
