package kr.co.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.board.dao.ItBoard_Dao;
import kr.co.board.dto.ItBoard_Dto;
import kr.co.boardReply.dao.ItBoardReply_Dao;
import kr.co.boardReply.dto.ItBoardReply_Dto;
import lombok.Setter;

@Service
public class BoardService {
	
	@Setter
	@Autowired
	private ItBoard_Dao dao;
	
	@Setter
	@Autowired
	private ItBoardReply_Dao replyDao;
	
	//IT게시판 list출력 + 페이징
	public List<ItBoard_Dto> getItCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		
		return dao.getItCategoryList(displayPost, postNum, searchType, keyword);
	}
	
	//It 게시판 글 작성
	public int itCategoryWrite(ItBoard_Dto dto) throws Exception {
		return dao.itCategoryWrite(dto);
	}
	
	//It 게시판 글 조회
	public ItBoard_Dto itCategoryView(int postNo) throws Exception {
		return dao.itCategoryView(postNo);
	}
	
	//It 글 수정
	public int itCategoryUpdate(ItBoard_Dto dto) throws Exception{
		return dao.itCategoryUpdate(dto);
	}
	
	//It 글 삭제
	public int itCategoryDelete(int postNo) throws Exception{
		return dao.itCategoryDelete(postNo);
	}
	
	//It 게시물 총 갯수 + 검색
	public int itCategoryCount(String searchType, String keyword) throws Exception{
		return dao.itCategoryCount(searchType, keyword);
	}
	
	//IT게시판 답글 갯수 수정
	public int itCommentsCountUpdate(int postNo, int commentsCount) throws Exception{
		return dao.itCommentsCountUpdate(postNo, commentsCount);
	}
	
	//IT댓글 조회
    public List<ItBoardReply_Dto> itReplyList(int postNo) throws Exception{
    	return replyDao.itReplyList(postNo);
    }
    
    //IT댓글 작성
  	public int itReplyWrite(ItBoardReply_Dto dto) throws Exception {
  		return replyDao.itReplyWrite(dto);
  	}
  	
  	//IT댓글 수정
  	public int itReplyUpdate(ItBoardReply_Dto dto) throws Exception {
  		return replyDao.itReplyUpdate(dto);
  	}
  	
  	//IT댓글 삭제
  	public int itReplyDelete(int postNo, int commentsNo) throws Exception {
  		return replyDao.itReplyDelete(postNo, commentsNo);
  	}
  	
    //IT 하나의 글에 대한 답글 수  조회
  	public int itReplyCount(int postNo) throws Exception {
  		return replyDao.itReplyCount(postNo);
  	}

}
