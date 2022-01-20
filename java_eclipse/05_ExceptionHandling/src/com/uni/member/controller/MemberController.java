package com.uni.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDAO;
import com.uni.member.model.dto.Member;
import com.uni.member.model.exception.MemberException;
import com.uni.member.model.service.MemberService;
import com.uni.member.view.MemberMenu;

public class MemberController {// view 와 dao 를 연결해주는 객체
	
	// case 1: 번 전체 조회
	public void selectAll() { 
		MemberMenu menu = new MemberMenu(); // 메뉴로..
		ArrayList<Member> list;
		try {
			list = new MemberService().selectAll();
		
		
			//먼저 Array 리스트로 Member 타입으로 담는다. 
			//selectAll 호출 멤버 타입으로 다 받아서 ArrayList 를 받아온다. 
		
			if(!list.isEmpty()) {//객체가 있을때
				menu.displayMemberList(list);// displayMemberList 메소드를 만들어서 () 안에있는 list를 view로 보내준다.
			}else {
				menu.displayNoData();// System.out.println("조회된 데이터가 없습니다.");
			}
		
		} catch (MemberException e) {
			System.out.println("회원전체 조회 실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
		}
	}
	// case 2: 번 아이디로 조회
	public void selectOne(String memberId) { //멤버 아이디를 받아왔다. //하나만 갖고 와서 조회하기 때문에 ArrayList가 필요하지 않는다.
		MemberMenu menu = new MemberMenu(); // 메뉴로..
		Member m;
		try {
			m = new MemberService().selectOne(memberId);// selectOne(member)를 MemberDAO 에 넘겨서 조회를 한다.
		
			
			if(m != null) {//객체가 null 이아닌경우
				menu.displayMemberList(m); 
			}else {
				menu.displayNoData();// System.out.println("조회된 데이터가 없습니다.");
			}
		
		} catch (MemberException e) {
			menu.displayError("회원조회실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
		} 
	}
	// case 3: 번 이름으로 조회
	public void selectByName(String memberName) {
		MemberMenu menu = new MemberMenu(); // 메뉴로.. // view 생성
		List<Member> list;
		try {
			list = new MemberService().selectByName(memberName); // 여러 몇의 이름을 조회 할 수 있기때문에 list 생성
			if(!list.isEmpty()) {//리스트가 Empty 아닌경우
				menu.displayMemberList(list); 
			}else {//리스트가 Empty 인 경우
				menu.displayNoData();// System.out.println("조회된 데이터가 없습니다.");
			}
		} catch (MemberException e) {// Exception 으로 작성해도된다. 그이외에 발생하는 오류도 잡아준다.
			menu.displayError("회원검색실패, 관리자에게 문의하세요");
			System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
		} 
	}


	// choice 4번 정보 회원 가입
	// 쿼리가 재대로 수행되면 쿼리문에 insert,update,delete 문은 쿼리문에 성공 갯수가 리턴이 된다. 
	//그래서 insert 가 몇개가 성공이 됬는지 리터이 되기때문에 반화해서 넘겨준다.		
	public void insertMember(Member m) {
		
		int result;
		try {
			result = new MemberService().inserMember(m);
			 
			
			if(result >0) { //result 값이 만약 0 보다 크면 성공한 경우
				new MemberMenu().displaySuccess("회원가입 성공"); //성공했을 대 띄어 줄 displaySuccess 생성  
			}
		}catch (MemberException e) {
			e.printStackTrace();
			new MemberMenu().displayError("화원가입 실패");
			System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
	}

	}
	// choice 5번 회원 정보 변경
	public void updateMember(Member m) {//매개 변수를 m 으로 받는다.
		int result;
		try {
			result = new MemberService().updateMember(m);
			 // 쿼리가 재대로 수행되면 쿼리문에 insert,update,delete 문은 쿼리문에 성공 갯수가 리턴이 된다. 그래서 insert 가 몇개가 성공이 됬는지 리터이 되기때문에 반화해서 넘겨준다.		
			
			if(result >0) { //result 값이 만약 0 보다 크면 성공한 경우
				new MemberMenu().displaySuccess("회원수정성공"); //성공했을 대 띄어 줄 displaySuccess 생성  
			}
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new MemberMenu().displayError("화원수정 실패");
			System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
		}
	
	}
	// choice 6번 회원 탈퇴
	public void deleteMember(String userId) { 
		int result;
		try {
			result = new MemberService().deleteMember(userId);
		
			if (result >0) { //result 값이 만약 0 보다 크면 성공한 경우
				new MemberMenu().displaySuccess("회원탈퇴완료"); //성공했을 대 띄어 줄 displaySuccess 생성 
			}
	} catch (MemberException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		new MemberMenu().displayError("화원삭제실패");
		System.out.println(e.getMessage());//MemberException 담겨온 message 밑에 나온다.
	}
	
		
	}//종료
	public void exitProgram() {
		new MemberService().exitProgram();
		
	}
	// choice 7번 탈퇴 회원 조회
	public void selectAllDeleteMember() {
		MemberMenu menu = new MemberMenu(); // 메뉴로.. // view 생성
		List<Member> list;
		try {
			list = new MemberService().selectAllDeleteMember();
			// 탈퇴 전부 조회
			
			if(!list.isEmpty()) {//리스트가 Empty 아닌경우
				menu.displayMemberList(list); 
			}else {//리스트가 Empty 인 경우
				menu.displayNoData();// System.out.println("조회된 데이터가 없습니다.");
		}
		} catch (MemberException e) {
			menu.displayError("탈퇴회원조회실해, 관리자에게 문의하세요");
			e.printStackTrace();
		}

		
	}
}