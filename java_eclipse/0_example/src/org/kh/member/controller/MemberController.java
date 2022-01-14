package org.kh.member.controller;

import java.util.ArrayList; 

import org.kh.member.dao.MemberDao; 
import org.kh.member.model.vo.Member; 

public class MemberController { 
	
	// 컨트롤러 클래스의 역할 
	// view에서 요청한 기능을 컨트롤러가 DAO와의 연결을 하는 역할
	
	// 회원 전체출력 메소드 
	public void selectMemberAll() { 
		MemberDao dao = new MemberDao(); 
		ArrayList<Member> list = dao.selectMemberAll(); // DAO의 회원 전체 조회 메소드 호출 
		// 리턴한 list 결과 상황 
		// 1. list 가 있을 때 
		// 2. lost 가 없을 때 
		
		if (!list.isEmpty())// .isEmpty =비어있따 // list가 있을 떄 
			
		{ 
			// 결과출력 
			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n"); 
			System.out.println("아이디 비밀번호 이름 성별 나이 이메일 전화번호 주소 취미 가입일"); 
			
			//1번째 방식 
			for(Member m : list) { 
				System.out.println(m);//m만써도된다. 
				
			} 
			

			} else// list가 없을 때 
			{
				// 없다고 알려주는 코드 
				System.out.println("회원 목록이 없습니다."); 
				
			} 
		
	} 
	// 회원 아이디 검색 메소드
	public void selectMemberId(String userId) {//매개변수 낙타표기법으로 썼다. 
		//System.out.println("입력받은 문자열 : "+userId);
		MemberDao dao=new MemberDao(); 
		dao.selectMemberId(userId);//dao를 그대로 dao한테줘서 그걸 DB한테그아이디로 검색하게끔 할것이다.
		Member m = dao.selectMemberId(userId);
		
		if(m!=null) //객체가 있을때(조회가 성공하였을때) 
			
		{ 
			// 결과출력 
			
			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n"); 
			System.out.println("아이디 비밀번호 이름 성별 나이 이메일 전화번호 주소 취미 가입일"); 
			System.out.println(m);//하나일때는 굳이 m하나만 해주면 된다.
			
		} else //객체가 없을때(조회가 없어서 null이 리턴되었을때) 
			
		{ 
			System.out.println("해당되는 ID를 가진 회원이 없습니다."); 
			
		} 
		
	} 
	// 회원 이름 검색 메소드 
	
	public void selectMemberName(String userName) { 
		MemberDao dao = new MemberDao();
		dao.selectMemberName(userName); 
		ArrayList<Member> list = dao.selectMemberName(userName); 
		
		if(!list.isEmpty()) {
			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n");
			System.out.println("아이디 비밀번호 이름 성별 나이 이메일 전화번호 주소 취미 가입일"); 
			
			for(Member m:list) {
				System.out.println(m); 
				
			} 
			
		}else { 
			System.out.println("누가없는거 찾으래!");
			
		} 
		
	} 
	
	// 회원 가입 메소드 
	public void insertMember(Member m) { 
		// TODO Auto-generated method stub 
		MemberDao dao = new MemberDao(); 
		int result=dao.insertMember(m);
		
		if(result>0) {
			System.out.println("정상적으로 회원가입 되었습니다.");
			
		} 
		else { System.out.println("회원 가입에 실패 하였습니다.");
		}
		
	} 
	
	// 회원 정보 수정 메소드 
	public void updateMember(String userId, Member m1) { 
		MemberDao dao=new MemberDao();
		int result=dao.updateMember(userId,m1);
		
		if(result>0) { 
			System.out.println("정상적으로 업데이트 되었습니다."); 
			
		} else {
			System.out.println("업데이트가 실패 하였습니다."); 
			
		} 
		
	} 
	
	// 회원 탈퇴 메소드 
	public void deleteMember(String userId) {
		MemberDao dao = new MemberDao();
		int result=dao.deleteMember(userId);
		
		if(result>0) { 
			System.out.println("정상적으로 탈퇴 되었습니다."); 
			
		} else { 
			System.out.println("탈퇴가 실패 하였습니다.");
			
		} 
		
	} 
	
}

