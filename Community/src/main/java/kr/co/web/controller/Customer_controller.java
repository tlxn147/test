package kr.co.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kr.co.kakao.KakaoController;
import kr.co.naver.NaverLoginBO;
import kr.co.web.dto.Customer_dto;
import kr.co.web.service.Customer_service;
import lombok.Setter;

@Controller
public class Customer_controller {
	@Setter
	@Autowired
	private Customer_service ms;
	@Setter
	@Autowired
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;
	
	// 메인화면 이동
	@RequestMapping("main")
	public String main() throws Exception {
		return "main";
	}
	
	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView memberLoginForm(HttpSession session) {
		ModelAndView mav = new ModelAndView();
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session); // 네이버 로그인 세션
		String kakaoUrl = KakaoController.getAuthorizationUrl(session); // 카카오 로그인 세션
		mav.addObject("kakao_url", kakaoUrl); // 카카
		mav.addObject("naver_url", naverAuthUrl);
		mav.setViewName("service/login");
		return mav;
	}
	
	// 관심사 list로 사용할 메소드
	private void referenceData(Model m) {
		String[] interest = { "게임", "웹툰", "연예", "기타" };
		m.addAttribute("signup_interest", interest);
	}
	
	// 회원가입 이동
	@RequestMapping("signup")
	public String signup(Model m) throws Exception {
		referenceData(m);
		return "service/signup";
	}
	
	// 회원가입 insert
	@RequestMapping(value = "success_signup", method = RequestMethod.POST)
	public String success_signup(Customer_dto dto) throws Exception {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); // Jasypt 암복호화 사용
		encryptor.setPassword("somePassword");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		String str = dto.getCustomerPW();
		String encStr = encryptor.encrypt(str); // 비밀번호 암호화
		dto.setCustomerPW(encStr);
		ms.main_signup(dto);
		return "main";
	}
		
	// 아이디 중복확인
	@RequestMapping("user_id_check")
	@ResponseBody
	public String main_signup_userid(String customerId) throws Exception {
		return ms.main_signup_userid(customerId);
	}

	// 닉네임 중복확인
	@RequestMapping("user_nickname_check")
	@ResponseBody
	public String main_signup_user_nickname(String customerNickname) throws Exception {
		return ms.main_signup_user_nickname(customerNickname);
	}
	
	// 이메일 중복확인
	@RequestMapping("user_email_check")
	@ResponseBody
	public String main_signup_user_Email(Customer_dto customerEmail) throws Exception {
		return ms.main_signup_user_Email(customerEmail);
	}
	
	//로그인
	@RequestMapping(value="user_login_chk")
	public String main_login_user_id(HttpSession session, String customerId, String customerPW) throws Exception {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor(); // Jasypt 암복호화 사용
		if(session.getAttribute("login") !=null) {
			session.removeAttribute("login");
	    }
		String user_id = ms.main_login_user_id(customerId);
		Customer_dto list = ms.main_login_user_pw(user_id);
		encryptor.setPassword("somePassword");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		String decStr = encryptor.decrypt(list.getCustomerPW()); // 비밀번호 복호화
		try {
			if(decStr.equals(customerPW)) {
				session.setAttribute("customerId", user_id);
				session.setAttribute("customerEmail", list.getCustomerEmail());
				session.setAttribute("customergender", list.getCustomerGender());
				return "main";
			} else {
		        session.setAttribute("fail", user_id);
				return "service/login";
			}
		} catch (Exception e) {
			session.setAttribute("fail", "null");
		}
		return "service/login";
	}
	
	// 로그아웃
	@RequestMapping(value="logout", method = RequestMethod.GET)
	   public String logout(HttpSession session) throws Exception{
	      session.invalidate();
	      return "redirect:main";
	}

	// 카카오 로그인
	@RequestMapping(value = "kakaologin", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
	public String kakaoLogin(@RequestParam("code") String code,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		JsonNode node = KakaoController.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있음
		JsonNode accessToken = node.get("access_token"); // 사용자의 정보
		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken);
		String kname = null;
		JsonNode properties = userInfo.path("properties");
		kname = properties.path("nickname").asText(); // 카카오 로그인 이름 가져옴
		session.setAttribute("customerId", kname); 
		return "redirect:main";
	}
	
	// 네이버 로그인
    @RequestMapping(value = "naverlogin", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model m, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws IOException {
        OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
        	obj = parser.parse(apiResult);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        JSONObject jsonobj = (JSONObject) obj;
        JSONObject response = (JSONObject) jsonobj.get("response");
        String nname = (String) response.get("name"); // 네이버 로그인 이름 가져옴
        String nemail = (String) response.get("email");  // 네이버 로그인 이메일 가져옴
        session.setAttribute("customerId", nname); 
        session.setAttribute("customerEmail", nemail);
        m.addAttribute("result", apiResult);
        return "redirect:main";
    }
}