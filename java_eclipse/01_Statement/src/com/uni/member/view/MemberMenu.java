package com.uni.member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.uni.member.controller.MemberController;
import com.uni.member.model.dto.Member;

public class MemberMenu {
	private static Scanner sc = new Scanner(System.in); //Scanner 생성 하기 위해 static 와 같이 해서 작성
	private MemberController mController = new MemberController(); //컨트롤러 생성
	
	public void mainMenu() {
		
		int choice; //번호 선택형 변수 생성
		
		do {// 최초한번은 실행하기위해 do ~ while 생성
			System.out.println("\n************ 회원관리프로그램 **************");
			System.out.println("1.회원 전체 조회");//select
			System.out.println("2.회원 아이디 조회");//select
			System.out.println("3.회원 이름 조회");//select
			System.out.println("4.회원 가입");//insert
			System.out.println("5.회원 정보 변경");//update
			System.out.println("6.회원 탈퇴");//delete
			System.out.println("9.프로그램 끝내기");
			System.out.println("번호선택 : ");
			
			choice = sc.nextInt();// 번호 선택후 choice 에 담은후
			
			switch(choice) { //choice 값을 switch 에 넣은후
			case 1: // choice 1번 정보 전체조회
				mController.selectAll(); // mController 에 selectAll(전체조회를하기때문에 이거로이름 정함)
				break;
			case 2: // choice 2번 정보 아이디 조회
				mController.selectOne(inputMemberId()); // mController 에 selectOne(아이디 하나 조회를하기때문에 이거로이름 정함), 
														//아이디 값을 하나 받아서 할꺼라서 inputMemberId 생성 // 정보가 inputMemberId 에 들어온다.
				break;
			case 3: // choice 3번 정보 이름 조회
				mController.selectByName(inputMemberName()); //mController 에 selectOne(이름 조회를하기때문에 이거로이름 정함),
														//  selectByName 생성 // 정보가 inputMemberName 에 들어온다.
				break;
			case 4: // choice 4번 정보 회원 가입
				mController.insertMember(inputMember()); //mController 에 insertMember(회원 가입으로 이거로이름 정함),
															//  inputMember 생성 // 정보가 inputMember 에 들어온다.
				break;
			case 5: // choice 5번 정보 회원 업데이트 
				mController.updateMember(updateMember()); //mController 에 updateMember(회원정보 업데이트 이거로이름 정함),//  updateMember 생성 // 정보가 updateMember 에 들어온다.
				break;
			case 6: // choice 6번 정보 회원 탈퇴
				mController.deleteMember(inputMemberId()); // //mController 에 deleteMember(회원 탈퇴 이거로이름 정함),
				break;
			case 9:
				System.out.println("정말로 끝내시겠습니까? (y/n)");
				if('y'== sc.next().toLowerCase().charAt(0)) {
					return; // sc.next 에 y 입력시 - toLowerCase 소문자 - char(0) 한단어 y입력시 리턴된다.
				}// n 을입력시 프로그램 종료
				break;
				
			default:
				System.out.println("번호를 잘못입력하였습니다.");
			
			}
			
		}while(true);
		
	}
	private Member updateMember() {//회원정보 업데이트 창 입력// 반한 값 은 Member
		
		Member m = new Member();// 멤버 객체 생성 후 담아 준다. 
		
		m.setUserId(inputMemberId());
		System.out.println("암호 :" );
		m.setPassword(sc.next());
		
		System.out.println("이메일 :" );
		m.setEmail(sc.next());
		
		System.out.println("전화번호(-빼고 입력) :" );
		m.setPhone(sc.next());
		
		System.out.println("주소 :" );
		sc.nextLine();
		m.setAddress(sc.nextLine());

	return m;
	}
	private Member inputMember() {//회원가입 정보 입력 창 // 반환형 타입 Member으로
		
		Member m = new Member();// 멤버 객체 생성 후 담아 준다.
		
		System.out.println("새로운회원정보를 입력하세요 >>");
		
		System.out.println("아이디 :");
		m.setUserId(sc.next());
		
		System.out.println("암호 :");
		m.setPassword(sc.next());
		
		System.out.println("이름 :");
		m.setUserName(sc.next());
		
		System.out.println("나이 :");
		m.setAge(sc.nextInt());
		
		System.out.println("성별(M/F) :");
		m.setGender(sc.next().toUpperCase()); // toUpperCase() 대문자로 입력하는경우 사용
		
		System.out.println("이메일 :");
		m.setEmail(sc.next());
		
		System.out.println("전화번호(-를빼고) :");
		m.setPhone(sc.next());
		
		System.out.println("주소 :");
		sc.nextLine();
		m.setAddress(sc.nextLine());
		
		System.out.println("취미(,로 공백없이 입력) :");
		m.setHobby(sc.next());
		
		return m;
	}
	private String inputMemberName() { //이름으로 조회할 정보 입력 창
		
		System.out.println("조회할 회원 이름 입력 :");
		return sc.next(); //입력 받은 정보 inputMemberName()에 넘겨준다. 
	}
	private String inputMemberId() {//ID 하나 입력
		
		System.out.println("아이디입력 : "); // 하나의 아이디로 조회할 정보 입력 창
		return sc.next(); //입력 받은 정보 inputMemberId()에 넘겨준다. 
	}
	// 리턴되 리스트 출력용 메소드
	public void displayMemberList(List<Member> list) {// 매개변수를 list 로 받으면 다 받아 줄 수 있다.
		System.out.println("\n 조회된 전체 회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("-------------------------------------------------------------");
		
		for(Member m:list) {
			System.out.println(m);//이미 Member에서 순서대로 리턴 해있기때문에 m 을 넣으면 된다.
		}
		
		
	}
	
	//실패에 대한 에러메세지 출력용 메소드
	public void displayError(String message) { //message 가 넘어 온다.
		System.out.println("서비스 요청 처리 실패 :" + message); // 왜 실패했는지 message..
		
	}
	// 아이디로 조회된 회원 한명에 대한 정보 출력하는 메소드
	public void displayMemberList(Member m) {
		System.out.println("\n 조회된  회원정보는 다음과 같습니다.");
		System.out.println("\n아이디\t이름\t성별\t나이\t이메일\t전화번호\t주소\t취미\t가입일");
		System.out.println("-------------------------------------------------------------");
		System.out.println(m);
		
		}
	//성공 메세지 출력
	public void displaySuccess(String message) {
		System.out.println("서비스 요청 결과 :" + message); // 결과가 회원 가입 성공으로 출력
	}
		
	}

