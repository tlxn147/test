package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.PublicServantBoard_Dto;

public class PublicServantBoard_Dao extends SqlSessionDaoSupport {
	
	//PublicServant게시판 list출력 + 페이징 +검색
		public List<PublicServantBoard_Dto> getPublicServantCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("displayPost",displayPost);
			data.put("postNum",postNum);
			data.put("searchType", searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectList("board.publicServant_board",data);
		}
		
		//PublicServant글 작성
		public int publicServantCategoryWrite(PublicServantBoard_Dto dto) throws Exception{
			return getSqlSession().insert("board.publicServant_write",dto);
		}
		
		//PublicServant게시판 조회
		public PublicServantBoard_Dto publicServantCategoryView(int postNo) throws Exception{
			return getSqlSession().selectOne("board.publicServant_view",postNo);
		}
		
		//PublicServant글 조회수 변경
	    public int publicServantViewCountUpdate(int postNo) throws Exception{
	    	return getSqlSession().update("board.publicServantViewCnt_update",postNo);
	    }
		
		//PublicServant 글 수정
		public int publicServantCategoryUpdate(PublicServantBoard_Dto dto) throws Exception {
			return getSqlSession().update("board.publicServant_update",dto);
		}
		
		//PublicServant 글 삭제
		public int publicServantCategoryDelete(int postNo) throws Exception {
			return getSqlSession().delete("board.publicServant_delete",postNo);
		}
		
		//PublicServant 게시물 총 갯수 + 검색
		public int publicServantCategoryCount(String searchType, String keyword) throws Exception{
			
			HashMap<String,Object> data =new HashMap<>();
			
			data.put("searchType",searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectOne("board.publicServant_count",data);
		}
		//PublicServant게시판 답글 갯수 수정
		public int publicServantCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
			
			HashMap<String,Object> data =new HashMap<>();
			data.put("postNo", postNo);
			data.put("commentsCount", commentsCount);
			
			return getSqlSession().update("board.publicServant_commentsCount_update",data);
			
		}
		
		//Main페이지에 보여줄 최근 PublicServant게시글 10개 조회
			public List<PublicServantBoard_Dto> getMainPublicServantCategory() throws Exception {
				return getSqlSession().selectList("board.publicServant_mainPage");
			}

}
