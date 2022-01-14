package org.kh.member.model.vo; 

import java.sql.Date; 

public class Member { 
	
	//ID, PW 
	private String memberId;
	private String memberPwd; 
	private String memberName;
	private char gender; 
	private int age; 
	private String email; 
	private String phone; 
	private String address; 
	private String hobby; 
	private Date enrollDate; // java.sql.Date import 
	
	public Member(String memberId, String memberPwd, String memberName, char gender, int age, String email, 
				String phone, String address, String hobby, Date enrollDate) { 
		
		super(); 
		this.memberId = memberId; 
		this.memberPwd = memberPwd; 
		this.memberName = memberName; 
		this.gender = gender; 
		this.age = age; 
		this.email = email; 
		this.phone = phone;
		this.address = address; 
		this.hobby = hobby;
		this.enrollDate = enrollDate;
		
	} 
	
	public Member() { 
		super(); 
		// TODO Auto-generated constructor stub 
		
	}
	
	//이렇게 해줄경우 컨트롤러에서 toString을 호출하게끔 할수 있다. 
	@Override 
	public String toString() { 
		
		return 
				
				this.getMemberId() + " " + 
				this.getMemberPwd() + " " +
				this.getMemberName() + " "+
				this.getGender() + " " + 
				this.getAge() + " " + 
				this.getEmail() + " " +
				this.getPhone()+ " " + 
				this.getAddress() + " " + 
				this.getHobby() + " " + 
				this.getEnrollDate();
		
	} 
	
	//alt+shift+s
	
	public String getMemberId() { 
		return memberId; 
		
	} 
	
	public void setMemberId(String memberId) {
		this.memberId = memberId; 
		
	} 
	public String getMemberPwd() {
		return memberPwd;
		
	} public void setMemberPwd(String memberPwd) { 
		this.memberPwd = memberPwd; 
		
	} public String getMemberName() { 
		return memberName; 
		
	} public void setMemberName(String memberName) { 
		this.memberName = memberName; 
		
	} public char getGender() {
		return gender;
		
	} public void setGender(char gender) { 
		this.gender = gender;
		
	} public int getAge() {
		return age;
		
	} public void setAge(int age) {
		this.age = age;
		
	} public String getEmail() { 
		return email;
		
	} public void setEmail(String email) {
		this.email = email;
		
	} public String getPhone() {
		return phone; 
		
	} public void setPhone(String phone) {
		this.phone = phone; 
		
	} public String getAddress() { 
		return address;
		
	} public void setAddress(String address) {
		this.address = address;
		
	} public String getHobby() { 
		return hobby;
		
	} public void setHobby(String hobby) { 
		this.hobby = hobby;
		
	} public Date getEnrollDate() {
		return enrollDate;
		
	} public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate; 
		} 
	
}