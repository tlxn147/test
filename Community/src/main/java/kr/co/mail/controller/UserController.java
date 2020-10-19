package kr.co.mail.controller;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.web.service.Customer_service;

@Controller
public class UserController {
	@Autowired
	Customer_service ms;

	@RequestMapping("email_Authentication")
	public String main(String email_sub, Model m) throws Exception {
		m.addAttribute("email", email_sub);
		return "/service/email_Authentication/email_Authentication_main";
	}

	@Inject
	JavaMailSender mailSender;

	@RequestMapping("email_Authentication_check")
	@ResponseBody
	public int mailSending(HttpServletRequest request) throws IOException {
		Random r = new Random();
		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분
		String setfrom = "kjm.test.sts@gmail.com";
		String tomail = request.getParameter("e_mail"); // 받는 사람 이메일
		String title = "회원가입 인증 이메일 입니다."; // 제목
		String content = System.getProperty("line.separator") + "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다" +
						 System.getProperty("line.separator") +
						 System.getProperty("line.separator") + "인증번호는 " + dice + " 입니다. ";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
		return dice;
	}
}
