package kr.co.board.controller;

import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.board.dto.ItBoard_Dto;
import kr.co.board.service.BoardService;
import kr.co.board.utils.Page;
import kr.co.boardReply.dto.ItBoardReply_Dto;
import lombok.Setter;

@Controller
@RequestMapping("board")
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
								
		return "board/list/it-list";
	}
	
	//it글쓰기 페이지로 이동
	@RequestMapping(value="/itCategoryWrite", method = RequestMethod.GET) 
	public String getItCategoryWrite() throws Exception{
		return "board/write/it-write";
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
		
		ItBoard_Dto dto = service.itCategoryView(postNo);
		
		model.addAttribute("view", dto);
		
		//해당 글 댓글 조회
		List<ItBoardReply_Dto> reply = null;
		reply = service.itReplyList(postNo);
		
		model.addAttribute("comment",reply);
		
		return "board/view/it-view";
	}
	
	//it 글 수정 페이지 이동
	@RequestMapping(value="/itCategoryUpdate",method = RequestMethod.GET)
	public String getItCategoryModify(@RequestParam("postNo") int postNo, Model model) throws Exception{
		
		ItBoard_Dto dto = service.itCategoryView(postNo);
		
		model.addAttribute("view", dto);
		
		return "board/update/it-update";
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
		
		return "redirect:/board/itCategoryView?postNo="+dto.getPostNo();
	}
	
	//it글 댓글 삭제
	@RequestMapping(value="/itCommentDelete", method= RequestMethod.GET)
	public String itReplyDelete(@RequestParam("postNo") int postNo, @RequestParam("commentsNo") int commentsNo) throws Exception {
		
		service.itReplyDelete(postNo, commentsNo);
		
        int commentsCount = service.itReplyCount(postNo);//댓글 테이블 몇개인지 알아내는 기능
		service.itCommentsCountUpdate(postNo, commentsCount); //댓글 갯수 수정
		
		
		return "redirect:/board/itCategoryView?postNo="+postNo;
	}

}
