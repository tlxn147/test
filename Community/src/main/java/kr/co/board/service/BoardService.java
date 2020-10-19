package kr.co.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.ItBoard_Dao;
import kr.co.board.dao.LanguageBoard_Dao;
import kr.co.board.dto.ItBoard_Dto;
import kr.co.board.dto.LanguageBoard_Dto;
import kr.co.boardReply.dao.ItBoardReply_Dao;
import kr.co.boardReply.dao.LanguageBoardReply_Dao;
import kr.co.boardReply.dto.ItBoardReply_Dto;
import kr.co.boardReply.dto.LanguageBoardReply_Dto;
import lombok.Setter;

@Service
public class BoardService {
	
	@Setter
	@Autowired
	private ItBoard_Dao itDao; 
	
	@Setter
	@Autowired
	private LanguageBoard_Dao languageDao;
	
	@Setter
	@Autowired
	private ItBoardReply_Dao itReplyDao;
	
	@Setter
	@Autowired
	private LanguageBoardReply_Dao languageReplyDao;
	
	//IT게시판 list출력 + 페이징
	public List<ItBoard_Dto> getItCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		
		return itDao.getItCategoryList(displayPost, postNum, searchType, keyword);
	}
	
	//It 게시판 글 작성
	public int itCategoryWrite(ItBoard_Dto dto) throws Exception {
		return itDao.itCategoryWrite(dto);
	}
	
	//It 게시판 글 조회
	public ItBoard_Dto itCategoryView(int postNo) throws Exception {
		return itDao.itCategoryView(postNo);
	}
	
	//IT글 조회수 변경
    public int itViewCountUpdate(int postNo) throws Exception{
    	return itDao.itViewCountUpdate(postNo);
    }
	
	//It 글 수정
	public int itCategoryUpdate(ItBoard_Dto dto) throws Exception{
		return itDao.itCategoryUpdate(dto);
	}
	
	//It 글 삭제
	public int itCategoryDelete(int postNo) throws Exception{
		return itDao.itCategoryDelete(postNo);
	}
	
	//It 게시물 총 갯수 + 검색
	public int itCategoryCount(String searchType, String keyword) throws Exception{
		return itDao.itCategoryCount(searchType, keyword);
	}
	
	//IT게시판 답글 갯수 수정
	public int itCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
		return itDao.itCommentsCountUpdate(postNo, commentsCount);
	}
	
	//Main페이지에 보여줄 최근 it게시글 10개 조회
  	public List<ItBoard_Dto> getMainItCategory() throws Exception{
  		return itDao.getMainItCategory();
  	}
	
  	
	//IT댓글 조회
    public List<ItBoardReply_Dto> itReplyList(int postNo) throws Exception{
    	return itReplyDao.itReplyList(postNo);
    }
    
    //IT댓글 작성
  	public int itReplyWrite(ItBoardReply_Dto dto) throws Exception {
  		return itReplyDao.itReplyWrite(dto);
  	}
  	
  	//IT댓글 수정
  	public int itReplyUpdate(ItBoardReply_Dto dto) throws Exception {
  		return itReplyDao.itReplyUpdate(dto);
  	}
  	
  	//IT댓글 삭제
  	public int itReplyDelete(int postNo, int commentsNo) throws Exception {
  		return itReplyDao.itReplyDelete(postNo, commentsNo);
  	}
  	
    //IT 하나의 글에 대한 답글 수  조회
  	public int itReplyCount(int postNo) throws Exception {
  		return itReplyDao.itReplyCount(postNo);
  	}
  	
    
  	
  //--------------------------------------------------------------------------------------------------------------------------
  	
  //Language게시판 list출력 + 페이징
  		public List<LanguageBoard_Dto> getLanguageCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
  			
  			
  			return languageDao.getLanguageCategoryList(displayPost, postNum, searchType, keyword);
  		}
  		
  		//Language 게시판 글 작성
  		public int languageCategoryWrite(LanguageBoard_Dto dto) throws Exception {
  			return languageDao.languageCategoryWrite(dto);
  		}
  		
  		//Language 게시판 글 조회
  		public LanguageBoard_Dto languageCategoryView(int postNo) throws Exception {
  			return languageDao.languageCategoryView(postNo);
  		}
  		
  	    //Language글 조회수 변경
  	    public int languageViewCountUpdate(int postNo) throws Exception{
  	    	return languageDao.languageViewCountUpdate(postNo);
  	    }
  		
  		//Language 글 수정
  		public int languageCategoryUpdate(LanguageBoard_Dto dto) throws Exception{
  			return languageDao.languageCategoryUpdate(dto);
  		}
  		
  		//Language 글 삭제
  		public int languageCategoryDelete(int postNo) throws Exception{
  			return languageDao.languageCategoryDelete(postNo);
  		}
  		
  		//Language 게시물 총 갯수 + 검색
  		public int languageCategoryCount(String searchType, String keyword) throws Exception{
  			return languageDao.languageCategoryCount(searchType, keyword);
  		}
  		
  		//Language게시판 답글 갯수 수정
  		public int languageCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
  			return languageDao.languageCommentsCountUpdate(postNo, commentsCount);
  		}
  		
  	    //Main페이지에 보여줄 최근 language게시글 10개 조회
  	  	public List<LanguageBoard_Dto> getMainLanguageCategory() throws Exception{
  	  		return languageDao.getMainLanguageCategory();
  	  	}
  		
  		//Language댓글 조회
  	    public List<LanguageBoardReply_Dto> languageReplyList(int postNo) throws Exception{
  	    	return languageReplyDao.languageReplyList(postNo);
  	    }
  	    
  	    //Language댓글 작성
  	  	public int languageReplyWrite(LanguageBoardReply_Dto dto) throws Exception {
  	  		return languageReplyDao.languageReplyWrite(dto);
  	  	}
  	  	
  	  	//Language댓글 수정
  	  	public int languageReplyUpdate(LanguageBoardReply_Dto dto) throws Exception {
  	  		return languageReplyDao.languageReplyUpdate(dto);
  	  	}
  	  	
  	  	//Language댓글 삭제
  	  	public int languageReplyDelete(int postNo, int commentsNo) throws Exception {
  	  		return languageReplyDao.languageReplyDelete(postNo, commentsNo);
  	  	}
  	  	
  	    //Language 하나의 글에 대한 답글 수  조회
  	  	public int languageReplyCount(int postNo) throws Exception {
  	  		return languageReplyDao.languageReplyCount(postNo);
  	  	}
  	

}
