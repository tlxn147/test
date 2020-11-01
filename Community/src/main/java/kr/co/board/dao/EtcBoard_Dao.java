package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.EtcBoard_Dto;

public class EtcBoard_Dao extends SqlSessionDaoSupport {
	//Etc게시판 list출력 + 페이징 +검색
			public List<EtcBoard_Dto> getEtcCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
				
				HashMap<String, Object> data = new HashMap<String, Object>();
				
				data.put("displayPost",displayPost);
				data.put("postNum",postNum);
				data.put("searchType", searchType);
				data.put("keyword", keyword);
				
				return getSqlSession().selectList("board.etc_board",data);
			}
			
			//Etc글 작성
			public int etcCategoryWrite(EtcBoard_Dto dto) throws Exception{
				return getSqlSession().insert("board.etc_write",dto);
			}
			
			//Etc게시판 조회
			public EtcBoard_Dto etcCategoryView(int postNo) throws Exception{
				return getSqlSession().selectOne("board.etc_view",postNo);
			}
			
			//Etc글 조회수 변경
		    public int etcViewCountUpdate(int postNo) throws Exception{
		    	return getSqlSession().update("board.etcViewCnt_update",postNo);
		    }
			
			//Etc 글 수정
			public int etcCategoryUpdate(EtcBoard_Dto dto) throws Exception {
				return getSqlSession().update("board.etc_update",dto);
			}
			
			//Etc 글 삭제
			public int etcCategoryDelete(int postNo) throws Exception {
				return getSqlSession().delete("board.etc_delete",postNo);
			}
			
			//Etc 게시물 총 갯수 + 검색
			public int etcCategoryCount(String searchType, String keyword) throws Exception{
				
				HashMap<String,Object> data =new HashMap<>();
				
				data.put("searchType",searchType);
				data.put("keyword", keyword);
				
				return getSqlSession().selectOne("board.etc_count",data);
			}
			//Etc게시판 답글 갯수 수정
			public int etcCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
				
				HashMap<String,Object> data =new HashMap<>();
				data.put("postNo", postNo);
				data.put("commentsCount", commentsCount);
				
				return getSqlSession().update("board.etc_commentsCount_update",data);
				
			}
			
			//Main페이지에 보여줄 최근 Etc게시글 10개 조회
				public List<EtcBoard_Dto> getMainEtcCategory() throws Exception {
					return getSqlSession().selectList("board.etc_mainPage");
				}

}
