package kr.co.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

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

import kr.co.board.dto.CertificateBoard_Dto;
import kr.co.board.dto.EtcBoard_Dto;
import kr.co.board.dto.ItBoard_Dto;
import kr.co.board.dto.LanguageBoard_Dto;
import kr.co.board.dto.PublicServantBoard_Dto;
import kr.co.board.service.BoardService;
import kr.co.board.utils.CalculateTime;
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
    @Setter
    @Autowired
    private BoardService bs;
	
	// 메인화면 이동
	@RequestMapping("main")
	public String main(Model model) throws Exception {
		CalculateTime ct = new CalculateTime();
		
		List<ItBoard_Dto> itPost = bs.getMainItCategory();
		for(ItBoard_Dto dto : itPost) {
			Date date = dto.getPostDate();
			String postDate = ct.calculateTime(date);
			dto.setPostDateStr(postDate);
		}
		List<LanguageBoard_Dto> languagePost = bs.getMainLanguageCategory(); 
		for(LanguageBoard_Dto dto : languagePost) {
			Date date = dto.getPostDate();
			String postDate = ct.calculateTime(date);
			dto.setPostDateStr(postDate);
		}
		
		List<PublicServantBoard_Dto> publicServantPost = bs.getMainPublicServantCategory(); 
		for(PublicServantBoard_Dto dto : publicServantPost) {
			Date date = dto.getPostDate();
			String postDate = ct.calculateTime(date);
			dto.setPostDateStr(postDate);
		}
		
		List<CertificateBoard_Dto> certificatePost = bs.getMainCertificateCategory(); 
		for(CertificateBoard_Dto dto : certificatePost) {
			Date date = dto.getPostDate();
			String postDate = ct.calculateTime(date);
			dto.setPostDateStr(postDate);
		}
		
		List<EtcBoard_Dto> etcPost = bs.getMainEtcCategory(); 
		for(EtcBoard_Dto dto : etcPost) {
			Date date = dto.getPostDate();
			String postDate = ct.calculateTime(date);
			dto.setPostDateStr(postDate);
		}
		
		model.addAttribute("itPost", itPost);
		model.addAttribute("languagePost", languagePost);
		model.addAttribute("publicServantPost", publicServantPost);
		model.addAttribute("certificatePost",certificatePost);
		model.addAttribute("etcPost",etcPost);
		
		return "main";
	}
	// 로그인
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView memberLoginForm(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 생성된 URL을 'naverAuthUrl'에 저장
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        // 생성된 URL을 'kakaoUrl'에 저장
        String kakaoUrl = KakaoController.getAuthorizationUrl(session);
		// naverAuthUrl 저장된 URL을 'naver_url'라는 KEY 값에 담아서 모델로 전송
		mav.addObject("naver_url", naverAuthUrl);
		// kakaoUrl 저장된 URL을 'kakao_url'라는 KEY 값에 담아서 모델로 전송
		mav.addObject("kakao_url", kakaoUrl); 
		mav.setViewName("service/login");
		return mav;
	}
/* 회원가입 */
	// 암호화 메소드
	private String encrypt(String m) {
		// Jasypt 설정으로 DB 정보 암호화
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("somePassword");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		return encryptor.encrypt(m);
	}
	// 복호화 메소드
	private String decrypt(String m) {
		// Jasypt 설정으로 DB 정보 암호화
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("somePassword");
		encryptor.setAlgorithm("PBEWithMD5AndDES");
		return encryptor.decrypt(m);
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
		// Jasypt 설정으로 DB 정보 암호화
		String encStr = encrypt(dto.getCustomerPW()); // 비밀번호 암호화
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
/* 로그인 및 로그아웃 */
	//로그인
	@RequestMapping(value="user_login_chk")
	public String main_login_user(HttpSession session,
			String customerId, String customerPW) throws Exception {
		if(session.getAttribute("login") !=null) {
	 	      session.invalidate();
	    } // 로그인 세션 연결 해제
		try {
			String user_id = ms.main_login_user_id(customerId); // 입력한 id로 db 검색
			Customer_dto list = ms.main_login_user(user_id); // 입력한 아이디로 db list 저장
			String decStr = decrypt(list.getCustomerPW()); // id와 pw가 매칭되면 비밀번호 복호화
			System.out.println(decStr);
			if(decStr.equals(customerPW)) {
				// 아이디와 비밀번호 모두 맞았을 시
				session.setAttribute("login", list);
				return "redirect:/main";
			} else {
				// 아이디나 비밀번호가 틀렸을 시
		        session.setAttribute("fail", user_id);
				return "service/login";
			}
		} catch (Exception e) {
			session.setAttribute("fail", "null"); // fail이라는 세션으로 null을 보낸다.
		}
		return "service/login";
	}
	// 카카오 로그인
	@RequestMapping(value = "kakaologin", method = { RequestMethod.GET, RequestMethod.POST })
	public String kakaoLogin(@RequestParam("code") String code, Customer_dto list,
			HttpServletResponse response, HttpSession session) throws Exception {
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
	    } // 로그인 세션 연결 해제
		JsonNode node = KakaoController.getAccessToken(code); // accessToken에 사용자의 로그인한 모든 정보가 들어있다.
		JsonNode accessToken = node.get("access_token"); // 사용자의 정보
		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken);
		JsonNode properties = userInfo.path("properties");
		list.setCustomerEmail("카카오 로그인"); // 카카오 로그인은 이메일을 못가져옵니다.
		list.setCustomerId(properties.path("nickname").asText()); // 카카오 로그인 이름 가져온다.
		session.setAttribute("login", list); 
		return "redirect:/main";
	}
	// 네이버 로그인
    @RequestMapping(value = "naverlogin", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model m, @RequestParam String code, Customer_dto list,
    		@RequestParam String state, HttpSession session) throws IOException {
    	if(session.getAttribute("login") !=null) {
			session.removeAttribute("login");
	    } // 로그인 세션 연결 해제
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
        list.setCustomerId((String) response.get("name")); // 네이버 로그인 이름 가져옴
        list.setCustomerEmail("네이버 로그인"); // 네이버 로그인 이메일 가져온다.
        session.setAttribute("login", list);
        m.addAttribute("result", apiResult);
        return "redirect:/main";
    }
	// 로그아웃
 	@RequestMapping(value="logout", method = RequestMethod.GET)
 	   public String logout(HttpSession session) throws Exception{
 	      session.invalidate();
 	      return "redirect:/main";
 	}
 	// 상세정보
 	@RequestMapping(value="user-details", method = RequestMethod.GET)
    public String main_user_details(Model m, HttpSession session) {
 		if(session.getAttribute("login") == null) {
 			return "/error/login_error";
	    } else {
	    	String id = ((Customer_dto) session.getAttribute("login")).getCustomerId();
	 		Customer_dto list = ms.main_login_user(id);
	 		session.setAttribute("login", list);
			return "service/details";
	    }
 	}
 	// 비밀번호 변경 화면
 	@RequestMapping(value="user-pw-chan", method = RequestMethod.GET)
    public String user_pw_chan(Model m, HttpSession session) {
 		if(session.getAttribute("login") == null) {
 			return "/error/login_error";
	    } else {
			return "service/user_pw_chan";
	    }
 	}
 	// 비밀번호 변경
  	@RequestMapping(value="user_pw_chan_update",  method = { RequestMethod.GET, RequestMethod.POST })
    public String user_pw_chan_update(HttpSession session, String current_pw, String new_pw, HttpServletResponse response_equals) {
  		if(session.getAttribute("login") == null) {
  			return "/error/login_error";
 	    } else {
			try {
				// 현재 비밀번호 입력
				Customer_dto list = ms.main_login_user(((Customer_dto) session.getAttribute("login")).getCustomerId());
				String current_pw_db = decrypt(list.getCustomerPW()); // db 암호화된 비밀번호 복호화 후 가져옴
				boolean bool = current_pw_db.equals(current_pw);
				response_equals.setContentType("text/html; charset=UTF-8");
				if(bool == true) {
					String encryptPW = encrypt(new_pw); // 비밀번호 암호화
				 	ms.user_pw_chan(list.getCustomerId(), encryptPW);
				 	PrintWriter out_equals = response_equals.getWriter();
					out_equals.println("<script>"
									 + "alert('비밀번호가 변경되었습니다.');"
									 + "</script>");
					out_equals.flush();
					// 비밀번호가 변경 되었을 경우
			        session.setAttribute("pw_chan", bool);
			 		return "service/details";
				}
				if(bool == false) {
					PrintWriter out_equals = response_equals.getWriter();
					out_equals.println("<script>"
									 + "alert('비밀번호를 확인해주세요.');"
									 + "history.back();"
									 + "</script>");
					out_equals.flush();
			        session.setAttribute("pw_chan", bool);
					return "service/user_pw_chan";
				}
			} catch (Exception e) {
				// 비밀번호가 틀렸을 경우
		        session.setAttribute("pw_chan", false);
				return "service/user_pw_chan";
			}
			return "service/user_pw_chan";
 	    }
  	}
  	
  	@RequestMapping(value="find_id", method = RequestMethod.GET)
  	public String find_id() {
  		return "service/find-id";
  	}
  	
  	@RequestMapping(value="find_pw", method = RequestMethod.GET)
  	public String find_pw() {
  		return "service/find-pw";
  	}
}