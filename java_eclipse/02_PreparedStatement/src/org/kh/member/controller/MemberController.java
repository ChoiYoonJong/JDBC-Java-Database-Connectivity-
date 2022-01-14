package org.kh.member.controller;

import java.security.Provider.Service;
import java.sql.Connection;
import java.util.ArrayList;

import org.kh.member.dao.MemberDao;
import org.kh.member.model.service.MemberService;
import org.kh.member.model.vo.Member;

public class MemberController {

	// 컨트롤러 클래스의 역할
	// view에서 요청한 기능을 컨트롤러가 DAO와의 연결을 하는 역할

	// -> Controller Service Dao 로 해서 코드 짜보겠다.

	// 회원 전체출력 메소드 -> 오늘 수정하는것  -> 바꾼것
	public void selectMemberAll() {
		MemberService service = new MemberService();
		ArrayList<Member> list = service.selectMemberAll();

		if(!list.isEmpty()) {
			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n");
			System.out.println("아이디     비밀번호  이름   성별   나이   이메일   전화번호   주소   취미   가입일");
			for(Member m : list) {
				System.out.println(m);
			}
		}else {
			System.out.println("회원이 등록되지 않았습니다.");
		}

	}

	// 회원 아이디 검색 메소드
	public void selectMemberId(String userId) {//매개변수 낙타표기법으로 썼다.
		//System.out.println("입력받은 문자열 : "+userId);
		
		MemberService service = new MemberService();
		Member m = service.selectMemberId(userId);

		
		
		
		if(m!=null) //객체가 있을때(조회가 성공하였을때)
		{
			// 결과출력

			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n");
			System.out.println("아이디     비밀번호  이름   성별   나이   이메일   전화번호   주소   취미   가입일");
			System.out.println(m);//하나일때는 굳이 m하나만 해주면 된다.
		}
		else //객체가 없을때(조회가 없어서 null이 리턴되었을때)
		{
			System.out.println("해당되는 ID를 가진 회원이 존재하지 않습니다.");
		}




	}

	// 회원 이름 검색 메소드
	public void selectMemberName(String userName) {
		
		MemberService service = new MemberService();
		ArrayList<Member> list = service.selectMemberName(userName); 
		//ArrayList<Member> list = new MemberService().selectMemberName(userName);
		
		if(!list.isEmpty()) {
			System.out.println("\n----------------- 전체 회원 정보 조회 -----------------\n");
			System.out.println("아이디     비밀번호  이름   성별   나이   이메일   전화번호   주소   취미   가입일");

			for(Member m:list) {
				System.out.println(m);
			}
		}else {
			System.out.println("현재 가입된정보를 가진 사람의 이름이 존재하지 않습니다.");
		}
	}

	// 회원 가입 메소드 -> 바꾼것
	public void insertMember(Member m) {
		/*MemberService service = new MemberService();
		service.insertMember(m);
		 */
		//체이닝 기법
		int result=new MemberService().insertMember(m);
		if(result>0) {
			System.out.println("회원 가입이 성공하였습니다.");
		}
		else {
			System.out.println("회원 가입이 실패하였습니다.");
		}
	}

	// 회원 정보 수정 메소드
	public void updateMember(String userId,Member m2) {
		MemberService service = new MemberService();
		//Connection conn=null;
		int result=service.updateMember(userId, m2);
		if(result>0) {
			System.out.println("정보 수정이 정상 처리 되었습니다.");
		}else {
			System.out.println("회원 정보 수정이 실패 하였습니다.(관리자에게문의)");
		}
	}

	// 회원 탈퇴 메소드
	public void deleteMember(String userId) {
		
		MemberService service = new MemberService();
		
		int result = service.deleteMember(userId);
		if(result>0) {
			System.out.println("정상적으로 탈퇴 되었습니다.");
		}
		else {
			System.out.println("탈퇴가 실패 하였습니다.");
		}

	}

	public boolean searchUserId(String userId) {
		// TODO Auto-generated method stub
		//System.out.println("입력받은 문자열 : "+userId);
		
		MemberService service = new MemberService();
		//dao.selectMemberId(userId);//dao를 그대로 dao한테줘서 그걸 DB한테그아이디로 검색하게끔 할것이다.
		//Member m = dao.selectUserId(userId);
		Member m = service.selectMemberId(userId);

		if(m!=null) //객체가 있을때(조회가 성공하였을때)
		{
			// 결과출력

			System.out.println("회원 조회가 완료되었습니다.");
			System.out.println("\n----------------- ["+userId+"]회원정보 -----------------\n");
			System.out.println("아이디     비밀번호  이름   성별   나이   이메일   전화번호   주소   취미   가입일");
			System.out.println(m);//하나일때는 굳이 m하나만 해주면 된다.
			return true;


		}
		else //객체가 없을때(조회가 없어서 null이 리턴되었을때)
		{
			System.out.println("해당되는 ID를 가진 회원이 없습니다.");
			return false;
		}

	}



}
