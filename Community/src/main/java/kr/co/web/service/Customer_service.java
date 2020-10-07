package kr.co.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.web.dao.Customer_Login_dao;
import kr.co.web.dao.Customer_SignUp_dao;
import kr.co.web.dto.Customer_dto;
import lombok.Setter;

@Service
public class Customer_service {
	@Setter
	@Autowired
	Customer_SignUp_dao md;
	@Setter
	@Autowired
	Customer_Login_dao ld;
	
	// 회원가입
	public int main_signup(Customer_dto dto) {
		String email_num = dto.getCustomerEmail_1() + "@" + dto.getCustomerEmail_2();
		dto.setCustomerEmail(email_num);
		return md.main_signup(dto);
	}

	// 회원가입 아이디 중복체크
	public String main_signup_userid(String customerId) {
		return md.main_signup_userid(customerId);
	}

	// 회원가입 닉네임 중복체크
	public String main_signup_user_nickname(String customerNickname) {
		return md.main_signup_user_nickname(customerNickname);
	}

	// 회원가입 이메일 중복체크
	public String main_signup_user_Email(Customer_dto dto) {
		String email_num = dto.getCustomerEmail_1() + "@" + dto.getCustomerEmail_2();
		dto.setCustomerEmail(email_num);
		return md.main_signup_user_Email(dto);
	}
	
	// 로그인 아이디 검사
	public String main_login_user_id(String customerId) {
		return ld.main_login_user_id(customerId);
	}
	
	// 로그인 비밀번호 검사 및 로그인 정보
	public Customer_dto main_login_user_pw(String customerId) {
		return ld.main_login_user_pw(customerId);
	}
}
