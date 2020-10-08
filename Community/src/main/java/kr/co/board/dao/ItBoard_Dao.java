package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.ItBoard_Dto;

public class ItBoard_Dao extends SqlSessionDaoSupport   {

	//IT게시판 list출력 + 페이징 +검색
	public List<ItBoard_Dto> getItCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectList("board.it_board",data);
	}
	
	//IT글 작성
	public int itCategoryWrite(ItBoard_Dto dto) throws Exception{
		return getSqlSession().insert("board.it_write",dto);
	}
	
	//IT게시판 조회
	public ItBoard_Dto itCategoryView(int postNo) throws Exception{
		return getSqlSession().selectOne("board.it_view",postNo);
	}
	
	//IT 글 수정
	public int itCategoryUpdate(ItBoard_Dto dto) throws Exception {
		return getSqlSession().update("board.it_update",dto);
	}
	
	//IT 글 삭제
	public int itCategoryDelete(int postNo) throws Exception {
		return getSqlSession().delete("board.it_delete",postNo);
	}
	
	//It 게시물 총 갯수 + 검색
	public int itCategoryCount(String searchType, String keyword) throws Exception{
		
		HashMap<String,Object> data =new HashMap<>();
		
		data.put("searchType",searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectOne("board.it_count",data);
	}
	//IT게시판 답글 갯수 수정
	public int itCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
		
		HashMap<String,Object> data =new HashMap<>();
		data.put("postNo", postNo);
		data.put("commentsCount", commentsCount);
		
		return getSqlSession().update("board.it_commentsCount_update",data);
		
	}
	

}
