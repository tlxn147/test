package kr.co.board.dao;

import java.util.HashMap;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.LanguageBoard_Dto;

public class LanguageBoard_Dao extends SqlSessionDaoSupport   {

	//Language�Խ��� list��� + ����¡ +�˻�
	public List<LanguageBoard_Dto> getLanguageCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("displayPost",displayPost);
		data.put("postNum",postNum);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectList("board.language_board",data);
	}
	
	//Language�� �ۼ�
	public int languageCategoryWrite(LanguageBoard_Dto dto) throws Exception{
		return getSqlSession().insert("board.language_write",dto);
	}
	
	//Language�Խ��� ��ȸ
	public LanguageBoard_Dto languageCategoryView(int postNo) throws Exception{
		return getSqlSession().selectOne("board.language_view",postNo);
	}
	
	//Language�� ��ȸ�� ����
    public int languageViewCountUpdate(int postNo) throws Exception{
    	return getSqlSession().update("board.languageViewCnt_update",postNo);
    }
	
	//Language �� ����
	public int languageCategoryUpdate(LanguageBoard_Dto dto) throws Exception {
		return getSqlSession().update("board.language_update",dto);
	}
	
	//Language �� ����
	public int languageCategoryDelete(int postNo) throws Exception {
		return getSqlSession().delete("board.language_delete",postNo);
	}
	
	//Language �Խù� �� ���� + �˻�
	public int languageCategoryCount(String searchType, String keyword) throws Exception{
		
		HashMap<String,Object> data =new HashMap<>();
		
		data.put("searchType",searchType);
		data.put("keyword", keyword);
		
		return getSqlSession().selectOne("board.language_count",data);
	}
	//Language�Խ��� ��� ���� ����
	public int languageCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
		
		HashMap<String,Object> data =new HashMap<>();
		data.put("postNo", postNo);
		data.put("commentsCount", commentsCount);
		
		return getSqlSession().update("board.language_commentsCount_update",data);
		
	}
	

}