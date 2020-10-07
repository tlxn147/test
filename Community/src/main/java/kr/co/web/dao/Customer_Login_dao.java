package kr.co.web.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;


import kr.co.web.dto.Customer_dto;

public class Customer_Login_dao extends SqlSessionDaoSupport {
	// 회원가입
	public int main_signup(Customer_dto dto) {
		return getSqlSession().insert("customer.main_signup", dto);
	}

	// 아이디 검사
	public String main_login_user_id(String customerId) {
		return getSqlSession().selectOne("customer.main_login_user_id", customerId);
	}

	// 비밀번호 검사 및 로그인 정보
	public Customer_dto main_login_user(String customerId) {
		return getSqlSession().selectOne("customer.main_login_user", customerId);
	}
}