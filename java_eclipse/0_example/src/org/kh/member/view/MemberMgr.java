package org.kh.member.view;

import java.util.Scanner;

import org.kh.member.controller.MemberController;
import org.kh.member.model.vo.Member;

//회원 관리 메뉴를 보여주는 View Class
public class MemberMgr {
	private Scanner sc = new Scanner(System.in);
	
	private MemberController mCon = new MemberController();//컨트롤러 객체
	
	// 메인 메뉴 메소드
	public void mainMenu() {
		int choice; 
		do {
		System.out.println("\n= = = = = = = = = = 회원 관리 프로그램 = = = = = = ");
		System.out.println("1. 회원 정보 전체 조회");
		System.out.println("2. 회원 아이디 조회(1명)");
		System.out.println("3. 회원 이름으로 검색(다수)");
		System.out.println("4. 회원 가입");
		System.out.println("5. 회원 정보 변경");
		System.out.println("6. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 : ");
		choice = sc.nextInt(); //선택값 choice 변수에 저장

		switch(choice) {
		case 1:mCon.selectMemberAll(); break;
		case 2:
			System.out.print("검색할 ID를 입력 : ");
			mCon.selectMemberId(sc.next()); break;
		case 3:
			System.out.print("검색할 NAME을 입력 : ");
			mCon.selectMemberName(sc.next());
			break;
		case 4:
			Member m=joinMember();//입력받는 전용메소드
			mCon.insertMember(m);//Member m=joinMember();이거랑 같다.
			break;
		case 5:
			System.out.print("변경할 ID를 입력 : ");
			String userId=sc.next();
			Member m1=updateMember();//Member m1=joinMember(); 해도 상관은 없다.
			mCon.updateMember(userId,m1);
			break;
		case 6:
			System.out.print("삭제할 ID를 입력 : ");//삭제할경우 기준을 ID로 잡았다.
			mCon.deleteMember(sc.next());
			break;
		}
		}while(choice!=0);//한번은 무조건 실행될 것이기 때문에 do while로 짰다.
		System.out.println("저희 프로그램을 이용해주셔서 감사합니다. 호구님!");
	}
	
	//회원 가입 정보 입력 메소드
		public Member joinMember() {
			System.out.println("- - - - - -회원 가입 정보 입력 ----");
			System.out.print("아이디 입력 : ");
			String userId=sc.next();
			System.out.print("비밀번호 입력 : ");
			String userPwd = sc.next();
			System.out.print("이름 입력 : ");
			String userName=sc.next();
			System.out.print("성별 입력 : ");
			char gender = sc.next().charAt(0);
			System.out.print("나이 입력 : ");
			int age = sc.nextInt();
			System.out.print("이메일 입력 : ");
			String email = sc.next();
			System.out.print("폰번호 입력(-빼고 입력) : ");
			String phone=sc.next();
			System.out.print("주소 입력 : ");
			sc.nextLine();
			String address = sc.nextLine();
			System.out.print("취미 입력(,로 구분 지어서 입력) : ");
			String hobby = sc.next();
			
			Member m = new Member();
			m.setMemberId(userId);
			m.setMemberPwd(userPwd);
			m.setMemberName(userName);
			m.setGender(gender);
			m.setAge(age);
			m.setEmail(email);
			m.setPhone(phone);
			m.setAddress(address);
			m.setHobby(hobby);
			return m;
		}
		
		
		//회원 가입 정보 입력 메소드랑 똑같은데 이름만 바꾼 것이다.
		public Member updateMember() {
			System.out.println("- - - - - -회원 업데이트 입력 ----");
			System.out.print("아이디 입력 : ");
			String userId=sc.next();
			System.out.print("비밀번호 입력 : ");
			String userPwd = sc.next();
			System.out.print("이름 입력 : ");
			String userName=sc.next();
			System.out.print("성별 입력 : ");
			char gender = sc.next().charAt(0);
			System.out.print("나이 입력 : ");
			int age = sc.nextInt();
			System.out.print("이메일 입력 : ");
			String email = sc.next();
			System.out.print("폰번호 입력(-빼고 입력) : ");
			String phone=sc.next();
			System.out.print("주소 입력 : ");
			sc.nextLine();
			String address = sc.nextLine();
			System.out.print("취미 입력(,로 구분 지어서 입력) : ");
			String hobby = sc.next();
			
			Member m = new Member();
			m.setMemberId(userId);
			m.setMemberPwd(userPwd);
			m.setMemberName(userName);
			m.setGender(gender);
			m.setAge(age);
			m.setEmail(email);
			m.setPhone(phone);
			m.setAddress(address);
			m.setHobby(hobby);
			return m;
		}
		
} 
