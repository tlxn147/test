package kr.co.board.controller;


import java.util.Date;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.board.dto.CertificateBoard_Dto;
import kr.co.board.dto.EtcBoard_Dto;
import kr.co.board.dto.ItBoard_Dto;
import kr.co.board.dto.LanguageBoard_Dto;
import kr.co.board.dto.PublicServantBoard_Dto;
import kr.co.board.service.BoardService;
import kr.co.board.utils.Page;
import kr.co.boardReply.dto.CertificateBoardReply_Dto;
import kr.co.boardReply.dto.EtcBoardReply_Dto;
import kr.co.boardReply.dto.ItBoardReply_Dto;
import kr.co.boardReply.dto.LanguageBoardReply_Dto;
import kr.co.boardReply.dto.PublicServantBoardReply_Dto;
import lombok.Setter;

@Controller
public class BoardController {
	@Setter
	@Autowired
	BoardService service;	
	
	//ITCategory 게시판 +페이징
	@RequestMapping(value="/itCategory", method=RequestMethod.GET) 
	public String getItCategoryList(
		Model model, @RequestParam(defaultValue="1") int num,
		@RequestParam(value="searchType", required= false, defaultValue = "postTitle") String searchType,
		@RequestParam(value="keyword", required = false, defaultValue="") String keyword
		) throws Exception {
		Page page = new Page();
		
		page.setNum(num);
		page.setCount(service.itCategoryCount(searchType,keyword));  //게시물 총갯수 + 검색기능 
		page.setSearchType(searchType); //검색 타입 
		page.setKeyword(keyword); //검색어
		
        List<ItBoard_Dto> list = service.getItCategoryList(page.getDisplayPost(),page.getPostNum(), searchType, keyword);
		model.addAttribute("itList", list);
		model.addAttribute("page",page);
		model.addAttribute("select", num);
								
		return "/board/list/it-list";
	}
	
	//it글쓰기 페이지로 이동
	@RequestMapping(value="/itCategoryWrite", method = RequestMethod.GET) 
	public String getItCategoryWrite(HttpSession session) throws Exception{
		if(session.getAttribute("login") == null) {
			return "/error/login_error";
		}
		return "/board/write/it-write";
	}
	
	//it글쓰기
	@RequestMapping(value="/itCategoryWrite", method = RequestMethod.POST) 
	public String itCategoryWrite(ItBoard_Dto dto) throws Exception{
				
		Date currentTime =new Date();
		dto.setPostDate(currentTime);
				
		service.itCategoryWrite(dto);
		return "redirect:/board/itCategory";
	}
	
	//it 글 조회 페이지 이동
	@RequestMapping(value="/itCategoryView",method = RequestMethod.GET)
	public String itCategoryView(@RequestParam("postNo") int postNo, Model model) throws Exception{
		service.itViewCountUpdate(postNo); //글 조회 수 추가되는 메소드
		ItBoard_Dto dto = service.itCategoryView(postNo);
		model.addAttribute("view", dto);
		//해당 글 댓글 조회
		List<ItBoardReply_Dto> reply = null;
		reply = service.itReplyList(postNo);
		model.addAttribute("comment",reply);
		return "/board/view/it-view";
	}
	
	//it 글 수정 페이지 이동
	@RequestMapping(value="/itCategoryUpdate",method = RequestMethod.GET)
	public String getItCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
		ItBoard_Dto dto = service.itCategoryView(postNo);
		model.addAttribute("view", dto);
		return "/board/update/it-update";
	}
	
	//it 글 수정
	@RequestMapping(value="/itCategoryUpdate",method = RequestMethod.POST)
	public String itCategoryUpdate(ItBoard_Dto dto) throws Exception{
		Date currentTime =new Date();
		dto.setPostDate(currentTime);
		service.itCategoryUpdate(dto);
		return "redirect:/board/itCategoryView?postNo="+dto.getPostNo();
	}
	
	//it 글 삭제
	@RequestMapping(value="/itCategoryDelete", method = RequestMethod.GET)
	public String itCategoryDelete(@RequestParam("postNo") int postNo) throws Exception {
		service.itCategoryDelete(postNo);
		return "redirect:/board/itCategory";
	}
	
	//it글 댓글 달기
	@RequestMapping(value="/itCommentReply", method = RequestMethod.POST )
	public String itReplyWrite(ItBoardReply_Dto dto) throws Exception{
		
		Date currentTime =new Date();
		dto.setPostDate(currentTime);	
		service.itReplyWrite(dto);
		int postNo = dto.getPostNo();
		int commentsCount = service.itReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
		service.itCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
		return "redirect:/itCategoryView?postNo="+dto.getPostNo();
	}
	
	//it글 댓글 수정
    @RequestMapping(value="/itCommentUpdate", method=RequestMethod.POST)
    public String languageReplyUpdate(ItBoardReply_Dto dto) throws Exception {
    	Date currentTime = new Date();
    	dto.setPostDate(currentTime);
    	
    	service.itReplyUpdate(dto);
    	 
    	 return "redirect:/itCategoryView?postNo="+dto.getPostNo(); 
    }
	
	//it글 댓글 삭제
	@RequestMapping(value="/itCommentDelete", method= RequestMethod.GET)
	public String itReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
		service.itReplyDelete(postNo, commentsNo);
        int commentsCount = service.itReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
		service.itCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
		return "redirect:/itCategoryView?postNo="+postNo;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	//languageCategory 게시판 +페이징
		@RequestMapping(value="/languageCategory", method=RequestMethod.GET) 
		public String getLanguageCategoryList(Model model, @RequestParam(defaultValue="1") int num,
				@RequestParam(value="searchType", required= false, defaultValue = "postTitle") String searchType,
				@RequestParam(value="keyword", required = false, defaultValue="") String keyword) throws Exception {
			Page page = new Page();
			page.setNum(num);
			page.setCount(service.languageCategoryCount(searchType,keyword));  //게시물 총갯수 + 검색기능 
			page.setSearchType(searchType); //검색 타입 
			page.setKeyword(keyword); //검색어
	        List<LanguageBoard_Dto> list = service.getLanguageCategoryList(page.getDisplayPost(),page.getPostNum(), searchType, keyword);
			model.addAttribute("languageList", list);
			model.addAttribute("page",page);
			model.addAttribute("select", num);
									
			return "board/list/language-list";
		}
		
		//language글쓰기 페이지로 이동
		@RequestMapping(value="/languageCategoryWrite", method = RequestMethod.GET) 
		public String getLanguageCategoryWrite(HttpSession session) throws Exception{
			if(session.getAttribute("login") == null) { 
				return "/error/login_error";
			}
			return "board/write/language-write";
		}
		
		//language글쓰기
		@RequestMapping(value="/languageCategoryWrite", method = RequestMethod.POST) 
		public String languageCategoryWrite(LanguageBoard_Dto dto) throws Exception{
					
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
					
			service.languageCategoryWrite(dto);
			return "redirect:/board/languageCategory";
		}
		
		//language 글 조회 페이지 이동
		@RequestMapping(value="/languageCategoryView",method = RequestMethod.GET)
		public String languageCategoryView(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			service.languageViewCountUpdate(postNo); //글 조회 수 추가되는 메소드
			
			LanguageBoard_Dto dto = service.languageCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			//해당 글 댓글 조회
			List<LanguageBoardReply_Dto> reply = null;
			reply = service.languageReplyList(postNo);
			
			model.addAttribute("comment",reply);
			
			return "board/view/language-view";
		}
		
		//language 글 수정 페이지 이동
		@RequestMapping(value="/languageCategoryUpdate",method = RequestMethod.GET)
		public String getLanguageCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			LanguageBoard_Dto dto = service.languageCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			return "board/update/language-update";
		}
		
		//language 글 수정
		@RequestMapping(value="/languageCategoryUpdate",method = RequestMethod.POST)
		public String languageCategoryUpdate(LanguageBoard_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
			service.languageCategoryUpdate(dto);
			
			return "redirect:/board/languageCategoryView?postNo="+dto.getPostNo();
		}
		
		//language 글 삭제
		@RequestMapping(value="/languageCategoryDelete", method = RequestMethod.GET)
		public String languageCategoryDelete(@RequestParam("postNo") int postNo) throws Exception {
			
			service.languageCategoryDelete(postNo);
			
			return "redirect:/board/languageCategory";
		}
		
		//language글 댓글 달기
		@RequestMapping(value="/languageCommentReply", method = RequestMethod.POST )
		public String languageReplyWrite(LanguageBoardReply_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
		    				
			service.languageReplyWrite(dto);
			
			int postNo = dto.getPostNo();
			int commentsCount = service.languageReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			
			service.languageCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			return "redirect:/board/languageCategoryView?postNo="+dto.getPostNo();
		}
		
		//language글 댓글 수정
        @RequestMapping(value="/languageCommentUpdate", method=RequestMethod.POST)
        public String languageReplyUpdate(LanguageBoardReply_Dto dto) throws Exception {
        	Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
        	service.languageReplyUpdate(dto);
        	 
        	 return "redirect:/board/languageCategoryView?postNo="+dto.getPostNo(); 
        }
		
		
		//language글 댓글 삭제
		@RequestMapping(value="/languageCommentDelete", method= RequestMethod.GET)
		public String languageReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
			
			service.languageReplyDelete(postNo, commentsNo);
			
	        int commentsCount = service.languageReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			service.languageCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			
			return "redirect:/board/languageCategoryView?postNo="+postNo;
		}
		/*---------------------------------------------------------------------------------------------*/
		
		//PublicServantCategory 게시판 +페이징
		@RequestMapping(value="/publicServantCategory", method=RequestMethod.GET) 
		public String getPublicServantCategoryList(Model model, @RequestParam(defaultValue="1") int num,
				@RequestParam(value="searchType", required= false, defaultValue = "postTitle") String searchType,
				@RequestParam(value="keyword", required = false, defaultValue="") String keyword) throws Exception {
			Page page = new Page();
			page.setNum(num);
			page.setCount(service.publicServantCategoryCount(searchType,keyword));  //게시물 총갯수 + 검색기능 
			page.setSearchType(searchType); //검색 타입 
			page.setKeyword(keyword); //검색어
	        List<PublicServantBoard_Dto> list = service.getPublicServantCategoryList(page.getDisplayPost(),page.getPostNum(), searchType, keyword);
			model.addAttribute("publicServantList", list);
			model.addAttribute("page",page);
			model.addAttribute("select", num);
									
			return "board/list/publicServant-list";
		}
		
		//PublicServant글쓰기 페이지로 이동
		@RequestMapping(value="/publicServantCategoryWrite", method = RequestMethod.GET) 
		public String getPublicServantCategoryWrite(HttpSession session) throws Exception{
			if(session.getAttribute("login") == null) { 
				return "/error/login_error";
			}
			return "board/write/publicServant-write";
		}
		
		//PublicServant글쓰기
		@RequestMapping(value="/publicServantCategoryWrite", method = RequestMethod.POST) 
		public String publicServantCategoryWrite(PublicServantBoard_Dto dto) throws Exception{
					
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
					
			service.publicServantCategoryWrite(dto);
			return "redirect:/board/publicServantCategory";
		}
		
		//PublicServant 글 조회 페이지 이동
		@RequestMapping(value="/publicServantCategoryView",method = RequestMethod.GET)
		public String publicServantCategoryView(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			service.publicServantViewCountUpdate(postNo); //글 조회 수 추가되는 메소드
			
			PublicServantBoard_Dto dto = service.publicServantCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			//해당 글 댓글 조회
			List<PublicServantBoardReply_Dto> reply = null;
			reply = service.publicServantReplyList(postNo);
			
			model.addAttribute("comment",reply);
			
			return "board/view/publicServant-view";
		}
		
		//PublicServant 글 수정 페이지 이동
		@RequestMapping(value="/publicServantCategoryUpdate",method = RequestMethod.GET)
		public String getPublicServantCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			PublicServantBoard_Dto dto = service.publicServantCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			return "board/update/publicServant-update";
		}
		
		//PublicServant 글 수정
		@RequestMapping(value="/publicServantCategoryUpdate",method = RequestMethod.POST)
		public String publicServantCategoryUpdate(PublicServantBoard_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
			service.publicServantCategoryUpdate(dto);
			
			return "redirect:/board/publicServantCategoryView?postNo="+dto.getPostNo();
		}
		
		//PublicServant 글 삭제
		@RequestMapping(value="/publicServantCategoryDelete", method = RequestMethod.GET)
		public String publicServantCategoryDelete(@RequestParam("postNo") int postNo) throws Exception {
			
			service.publicServantCategoryDelete(postNo);
			
			return "redirect:/board/publicServantCategory";
		}
		
		//PublicServant글 댓글 달기
		@RequestMapping(value="/publicServantCommentReply", method = RequestMethod.POST )
		public String publicServantReplyWrite(PublicServantBoardReply_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
		    				
			service.publicServantReplyWrite(dto);
			
			int postNo = dto.getPostNo();
			int commentsCount = service.publicServantReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			
			service.publicServantCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			return "redirect:/board/publicServantCategoryView?postNo="+dto.getPostNo();
		}
		
		//PublicServant글 댓글 수정
        @RequestMapping(value="/publicServantCommentUpdate", method=RequestMethod.POST)
        public String publicServantReplyUpdate(PublicServantBoardReply_Dto dto) throws Exception {
        	Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
        	service.publicServantReplyUpdate(dto);
        	 
        	 return "redirect:/board/publicServantCategoryView?postNo="+dto.getPostNo(); 
        }
		
		
		//PublicServant글 댓글 삭제
		@RequestMapping(value="/publicServantCommentDelete", method= RequestMethod.GET)
		public String publicServantReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
			
			service.publicServantReplyDelete(postNo, commentsNo);
			
	        int commentsCount = service.publicServantReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			service.publicServantCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			
			return "redirect:/board/publicServantCategoryView?postNo="+postNo;
		}

		/*---------------------------------------------------------------------------------------------*/
		
		//CertificateCategory 게시판 +페이징
		@RequestMapping(value="/certificateCategory", method=RequestMethod.GET) 
		public String getCertificateCategoryList(Model model, @RequestParam(defaultValue="1") int num,
				@RequestParam(value="searchType", required= false, defaultValue = "postTitle") String searchType,
				@RequestParam(value="keyword", required = false, defaultValue="") String keyword) throws Exception {
			Page page = new Page();
			page.setNum(num);
			page.setCount(service.certificateCategoryCount(searchType,keyword));  //게시물 총갯수 + 검색기능 
			page.setSearchType(searchType); //검색 타입 
			page.setKeyword(keyword); //검색어
	        List<CertificateBoard_Dto> list = service.getCertificateCategoryList(page.getDisplayPost(),page.getPostNum(), searchType, keyword);
			model.addAttribute("certificateList", list);
			model.addAttribute("page",page);
			model.addAttribute("select", num);
									
			return "board/list/certificate-list";
		}
		
		//Certificate글쓰기 페이지로 이동
		@RequestMapping(value="/certificateCategoryWrite", method = RequestMethod.GET) 
		public String getCertificateCategoryWrite(HttpSession session) throws Exception{
			if(session.getAttribute("login") == null) { 
				return "/error/login_error";
			}
			return "board/write/certificate-write";
		}
		
		//Certificate글쓰기
		@RequestMapping(value="/certificateCategoryWrite", method = RequestMethod.POST) 
		public String certificateCategoryWrite(CertificateBoard_Dto dto) throws Exception{
					
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
					
			service.certificateCategoryWrite(dto);
			return "redirect:/board/certificateCategory";
		}
		
		//Certificate 글 조회 페이지 이동
		@RequestMapping(value="/certificateCategoryView",method = RequestMethod.GET)
		public String certificateCategoryView(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			service.certificateViewCountUpdate(postNo); //글 조회 수 추가되는 메소드
			
			CertificateBoard_Dto dto = service.certificateCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			//해당 글 댓글 조회
			List<CertificateBoardReply_Dto> reply = null;
			reply = service.certificateReplyList(postNo);
			
			model.addAttribute("comment",reply);
			
			return "board/view/certificate-view";
		}
		
		//Certificate 글 수정 페이지 이동
		@RequestMapping(value="/certificateCategoryUpdate",method = RequestMethod.GET)
		public String getCertificateCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			CertificateBoard_Dto dto = service.certificateCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			return "board/update/certificate-update";
		}
		
		//Certificate 글 수정
		@RequestMapping(value="/certificateCategoryUpdate",method = RequestMethod.POST)
		public String certificateCategoryUpdate(CertificateBoard_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
			service.certificateCategoryUpdate(dto);
			
			return "redirect:/board/certificateCategoryView?postNo="+dto.getPostNo();
		}
		
		//Certificate 글 삭제
		@RequestMapping(value="/certificateCategoryDelete", method = RequestMethod.GET)
		public String certificateCategoryDelete(@RequestParam("postNo") int postNo) throws Exception {
			
			service.certificateCategoryDelete(postNo);
			
			return "redirect:/board/certificateCategory";
		}
		
		//Certificate글 댓글 달기
		@RequestMapping(value="/certificateCommentReply", method = RequestMethod.POST )
		public String certificateReplyWrite(CertificateBoardReply_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
		    				
			service.certificateReplyWrite(dto);
			
			int postNo = dto.getPostNo();
			int commentsCount = service.certificateReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			
			service.certificateCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			return "redirect:/board/certificateCategoryView?postNo="+dto.getPostNo();
		}
		
		//Certificate글 댓글 수정
        @RequestMapping(value="/certificateCommentUpdate", method=RequestMethod.POST)
        public String certificateReplyUpdate(CertificateBoardReply_Dto dto) throws Exception {
        	Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
        	service.certificateReplyUpdate(dto);
        	 
        	 return "redirect:/board/certificateCategoryView?postNo="+dto.getPostNo(); 
        }
		
		
		//Certificate글 댓글 삭제
		@RequestMapping(value="/certificateCommentDelete", method= RequestMethod.GET)
		public String certificateReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
			
			service.certificateReplyDelete(postNo, commentsNo);
			
	        int commentsCount = service.certificateReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			service.certificateCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			
			return "redirect:/board/certificateCategoryView?postNo="+postNo;
		}
		
		/*---------------------------------------------------------------------------------------------*/
		
		//EtcCategory 게시판 +페이징
		@RequestMapping(value="/etcCategory", method=RequestMethod.GET) 
		public String getEtcCategoryList(Model model, @RequestParam(defaultValue="1") int num,
				@RequestParam(value="searchType", required= false, defaultValue = "postTitle") String searchType,
				@RequestParam(value="keyword", required = false, defaultValue="") String keyword) throws Exception {
			Page page = new Page();
			page.setNum(num);
			page.setCount(service.etcCategoryCount(searchType,keyword));  //게시물 총갯수 + 검색기능 
			page.setSearchType(searchType); //검색 타입 
			page.setKeyword(keyword); //검색어
	        List<EtcBoard_Dto> list = service.getEtcCategoryList(page.getDisplayPost(),page.getPostNum(), searchType, keyword);
			model.addAttribute("etcList", list);
			model.addAttribute("page",page);
			model.addAttribute("select", num);
									
			return "board/list/etc-list";
		}
		
		//Etc글쓰기 페이지로 이동
		@RequestMapping(value="/etcCategoryWrite", method = RequestMethod.GET) 
		public String getEtcCategoryWrite(HttpSession session) throws Exception{
			if(session.getAttribute("login") == null) { 
				return "/error/login_error";
			}
			return "board/write/etc-write";
		}
		
		//Etc글쓰기
		@RequestMapping(value="/etcCategoryWrite", method = RequestMethod.POST) 
		public String etcCategoryWrite(EtcBoard_Dto dto) throws Exception{
					
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
					
			service.etcCategoryWrite(dto);
			return "redirect:/board/etcCategory";
		}
		
		//Etc 글 조회 페이지 이동
		@RequestMapping(value="/etcCategoryView",method = RequestMethod.GET)
		public String etcCategoryView(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			service.etcViewCountUpdate(postNo); //글 조회 수 추가되는 메소드
			
			EtcBoard_Dto dto = service.etcCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			//해당 글 댓글 조회
			List<EtcBoardReply_Dto> reply = null;
			reply = service.etcReplyList(postNo);
			
			model.addAttribute("comment",reply);
			
			return "board/view/etc-view";
		}
		
		//Etc 글 수정 페이지 이동
		@RequestMapping(value="/etcCategoryUpdate",method = RequestMethod.GET)
		public String getEtcCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
			
			EtcBoard_Dto dto = service.etcCategoryView(postNo);
			
			model.addAttribute("view", dto);
			
			return "board/update/etc-update";
		}
		
		//Etc 글 수정
		@RequestMapping(value="/etcCategoryUpdate",method = RequestMethod.POST)
		public String etcCategoryUpdate(EtcBoard_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
			service.etcCategoryUpdate(dto);
			
			return "redirect:/board/etcCategoryView?postNo="+dto.getPostNo();
		}
		
		//Etc 글 삭제
		@RequestMapping(value="/etcCategoryDelete", method = RequestMethod.GET)
		public String etcCategoryDelete(@RequestParam("postNo") int postNo) throws Exception {
			
			service.etcCategoryDelete(postNo);
			
			return "redirect:/board/etcCategory";
		}
		
		//Etc글 댓글 달기
		@RequestMapping(value="/etcCommentReply", method = RequestMethod.POST )
		public String etcReplyWrite(EtcBoardReply_Dto dto) throws Exception{
			Date currentTime =new Date();
			dto.setPostDate(currentTime);
		    				
			service.etcReplyWrite(dto);
			
			int postNo = dto.getPostNo();
			int commentsCount = service.etcReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			
			service.etcCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			return "redirect:/board/etcCategoryView?postNo="+dto.getPostNo();
		}
		
		//Etc글 댓글 수정
        @RequestMapping(value="/etcCommentUpdate", method=RequestMethod.POST)
        public String etcReplyUpdate(EtcBoardReply_Dto dto) throws Exception {
        	Date currentTime =new Date();
			dto.setPostDate(currentTime);
			
        	service.etcReplyUpdate(dto);
        	 
        	 return "redirect:/board/etcCategoryView?postNo="+dto.getPostNo(); 
        }
		
		
		//Etc글 댓글 삭제
		@RequestMapping(value="/etcCommentDelete", method= RequestMethod.GET)
		public String etcReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
			
			service.etcReplyDelete(postNo, commentsNo);
			
	        int commentsCount = service.etcReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
			service.etcCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
			
			
			return "redirect:/board/etcCategoryView?postNo="+postNo;
		}			
}
