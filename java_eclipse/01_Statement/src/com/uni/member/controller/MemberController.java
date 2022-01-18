package com.uni.member.controller;

import java.util.ArrayList;
import java.util.List;

import com.uni.member.model.dao.MemberDao;
import com.uni.member.model.dto.Member;
import com.uni.member.view.MemberMenu;

public class MemberController {// view 와 dao 를 연결해주는 객체
	
	// case 1: 번 전체 조회
	public void selectAll() { 
		MemberMenu menu = new MemberMenu(); // 메뉴로..
		ArrayList<Member> list = new MemberDao().selectAll();//Dao 에 있는 selectAll 호출
		
		//먼저 Array 리스트로 Member 타입으로 담는다. 
		//selectAll 호출 멤버 타입으로 다 받아서 ArrayList 를 받아온다. 
		
		if(!list.isEmpty()) {//객체가 있을때
			menu.displayMemberList(list);// displayMemberList 메소드를 만들어서 () 안에있는 list를 view로 보내준다.
		}else {//비었을때
			menu.displayError("해당하는 데이터가 없습니다.");// displayError를 만들어서 던져준다.
		}
	}
	// case 2: 번 아이디로 조회
	public void selectOne(String memberId) { //멤버 아이디를 받아왔다. //하나만 갖고 와서 조회하기 때문에 ArrayList가 필요하지 않는다.
		MemberMenu menu = new MemberMenu(); // 메뉴로..
		Member m = new MemberDao().selectOne(memberId); // selectOne(member)를 MemberDao 에 넘겨서 조회를 한다.
		
		if(m != null) {//객체가 null 이아닌경우
			menu.displayMemberList(m); 
		}else {// null 인경우 
			menu.displayError(memberId + "에 해당하는 데이터가 없습니다."); // member(작성한 정보 id) + "에 해당하는 데이터가 없습니다."
		}
	}
	// case 3: 번 이름으로 조회
	public void selectByName(String memberName) {
		MemberMenu menu = new MemberMenu(); // 메뉴로.. // view 생성
		List<Member> list = new MemberDao().selectByName(memberName); // 여러 몇의 이름을 조회 할 수 있기때문에 list 생성
		
		if(!list.isEmpty()) {//리스트가 Empty 아닌경우
			menu.displayMemberList(list); 
		}else {//리스트가 Empty 인 경우
			menu.displayError(memberName + "에 해당하는 데이터가 없습니다."); // member(작성한 이름 정보) + "에 해당하는 데이터가 없습니다."
	}

}
	// choice 4번 정보 회원 가입
	public void insertMember(Member m) {
		int result = new MemberDao().inserMember(m); // 쿼리가 재대로 수행되면 쿼리문에 insert,update,delete 문은 쿼리문에 성공 갯수가 리턴이 된다. 그래서 insert 가 몇개가 성공이 됬는지 리터이 되기때문에 반화해서 넘겨준다.		
		
		if(result >0) { //result 값이 만약 0 보다 크면 성공한 경우
			new MemberMenu().displaySuccess("회원가입 성공"); //성공했을 대 띄어 줄 displaySuccess 생성  
		}else // 실패한 경우
			new MemberMenu().displayError("화원가입 실패");// 실패 했을때 띄어 줄 displayError 생성  
	}
	// choice 5번 회원 정보 변경
	public void updateMember(Member m) {//매개 변수를 m 으로 받는다.
		int result = new MemberDao().updateMember(m); // 쿼리가 재대로 수행되면 쿼리문에 insert,update,delete 문은 쿼리문에 성공 갯수가 리턴이 된다. 그래서 insert 가 몇개가 성공이 됬는지 리터이 되기때문에 반화해서 넘겨준다.		
		
		if(result >0) { //result 값이 만약 0 보다 크면 성공한 경우
			new MemberMenu().displaySuccess("회원수정성공"); //성공했을 대 띄어 줄 displaySuccess 생성  
		}else { // 실패한 경우
			new MemberMenu().displayError("화원수정실패");// 실패 했을때 띄어 줄 displayError 생성  
		
	}
	
	}
	// choice 6번 회원 탈퇴
	public void deleteMember(String userId) { 
		int result = new MemberDao().deleteMember(userId);
		if (result >0) { //result 값이 만약 0 보다 크면 성공한 경우
			new MemberMenu().displaySuccess("회원탈퇴완료"); //성공했을 대 띄어 줄 displaySuccess 생성 
		}else {// 실패한 경우
			new MemberMenu().displayError("화원탈퇴실패");// 실패 했을때 띄어 줄 displayError 생성 
		}
		
	}
}
