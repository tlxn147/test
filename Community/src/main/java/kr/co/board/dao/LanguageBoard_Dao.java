package kr.co.board.dao;

import java.util.HashMap;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import kr.co.board.dto.LanguageBoard_Dto;

public class LanguageBoard_Dao extends SqlSessionDaoSupport   {

	//Language게시판 list출력 + 페이징 +검색
	public List<LanguageBoard_Dto> getLanguageCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectList("board.language_board",data);
	}
	
	//Language글 작성
	public int languageCategoryWrite(LanguageBoard_Dto dto) throws Exception{
		return getSqlSession().insert("board.language_write",dto);
	}
	
	//Language게시판 조회
	public LanguageBoard_Dto languageCategoryView(int postNo) throws Exception{
		return getSqlSession().selectOne("board.language_view",postNo);
	}
	
	//Language글 조회수 변경
    public int languageViewCountUpdate(int postNo) throws Exception{
    	return getSqlSession().update("board.languageViewCnt_update",postNo);
    }
	
	//Language 글 수정
	public int languageCategoryUpdate(LanguageBoard_Dto dto) throws Exception {
		return getSqlSession().update("board.language_update",dto);
	}
	
	//Language 글 삭제
	public int languageCategoryDelete(int postNo) throws Exception {
		return getSqlSession().delete("board.language_delete",postNo);
	}
	
	//Language 게시물 총 갯수 + 검색
	public int languageCategoryCount(String searchType, String keyword) throws Exception{
		
		HashMap<String,Object> data =new HashMap<>();
		
		data.put("searchType",searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectOne("board.language_count",data);
	}
	//Language게시판 답글 갯수 수정
	public int languageCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
		
		HashMap<String,Object> data =new HashMap<>();
		data.put("postNo", postNo);
		data.put("commentsCount", commentsCount);
		
		return getSqlSession().update("board.language_commentsCount_update",data);
		
	}
	
	//Main페이지에 보여줄 최근 language게시글 10개 조회
		public List<LanguageBoard_Dto> getMainLanguageCategory() throws Exception {
			return getSqlSession().selectList("board.language_mainPage");
		}
	

}
