package kr.co.web.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.web.dto.Customer_dto;

public class Customer_SignUp_dao extends SqlSessionDaoSupport {
	// 회원가입
	public int main_signup(Customer_dto dto) {
		return getSqlSession().insert("customer.main_signup", dto);
	}

	// 아이디 중복체크
	public String main_signup_userid(String customerId) {
		return getSqlSession().selectOne("customer.main_signup_user_id", customerId);
	}

	// 닉네임 중복체크
	public String main_signup_user_nickname(String customerNickname) {
		return getSqlSession().selectOne("customer.main_signup_user_nickname", customerNickname);
	}
	
	// 닉네임 중복체크
	public String main_signup_user_Email(Customer_dto customerEmail) {
		return getSqlSession().selectOne("customer.main_signup_user_email", customerEmail);
	}
}

