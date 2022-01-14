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

		System.out.println("\n******** 회원 관리 프로그램 ******** ");

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

		case 1:mCon.selectMemberAll(); 
		break;

		case 2:
			System.out.print("검색할 ID를 입력 : ");
			mCon.selectMemberId(sc.next()); 
			break;
		
		case 3:
			System.out.print("검색할 NAME을 입력 : ");
			mCon.selectMemberName(sc.next());
			break;
		
		case 4:
			Member m=joinMember();//입력받는 전용메소드
			mCon.insertMember(m);//Member m=joinMember();이거랑 같다.
			break;

		case 5:
			//해당 회원 정보 조회
			System.out.print("수정할 ID 입력 : ");
			String userId=sc.next();
			boolean result=mCon.searchUserId(userId);

			if(result) {//true가 리턴되었다면 회원이 있다는 의미가 되므로 수정 작업이 진행
				//비밀번호, 이메일, 폰번호, 주소, 취미

				Member m2 = modifyMember();
				mCon.updateMember(userId,m2);

			}
			break;

		case 6:
			System.out.print("삭제할 ID를 입력 : ");//삭제할경우 기준을 ID로 잡았다.
			mCon.deleteMember(sc.next());
			break;

		}

		}while(choice!=0);//한번은 무조건 실행될 것이기 때문에 do while로 짰다.

		System.out.println("저희 프로그램을 이용해주셔서 감사합니다.");

	}

	

	//회원 정보 수정 입력 메소드

	public Member modifyMember() {

		

		Member m= new Member();

		System.out.println("\n ******** 수정될 회원 정보 입력 ********\n");
		
		System.out.print("변경될 비밀번호 입력:");
		m.setMemberPwd(sc.next());
		
		System.out.print("변경될 이메일 입력:");
		m.setEmail(sc.next());
		
		System.out.print("변경될 폰번호 입력(-를제외):");
		m.setPhone(sc.next());
		
		System.out.print("변경될 주소 입력:");
		m.setAddress(sc.next());
		
		System.out.print("변경될 취미 입력(,로구분):");
		m.setHobby(sc.next());
		return m;

	

	}

	

	

	

	

	//회원 가입 정보 입력 메소드

		public Member joinMember() {

			System.out.println("******** 회원 가입 정보 입력 ********");
			
			System.out.print("아이디 입력 : ");
			String userId=sc.next();
			
			System.out.print("비밀번호 입력 : ");
			String userPwd = sc.next();
			
			System.out.print("이름 입력 : ");
			String userName=sc.next();
			
			System.out.print("성별 입력 (M/f) : ");
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
