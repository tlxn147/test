package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.CertificateBoard_Dto;

public class CertificateBoard_Dao extends SqlSessionDaoSupport {
	//Certificate게시판 list출력 + 페이징 +검색
		public List<CertificateBoard_Dto> getCertificateCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("displayPost",displayPost);
			data.put("postNum",postNum);
			data.put("searchType", searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectList("board.certificate_board",data);
		}
		
		//Certificate글 작성
		public int certificateCategoryWrite(CertificateBoard_Dto dto) throws Exception{
			return getSqlSession().insert("board.certificate_write",dto);
		}
		
		//Certificate게시판 조회
		public CertificateBoard_Dto certificateCategoryView(int postNo) throws Exception{
			return getSqlSession().selectOne("board.certificate_view",postNo);
		}
		
		//Certificate글 조회수 변경
	    public int certificateViewCountUpdate(int postNo) throws Exception{
	    	return getSqlSession().update("board.certificateViewCnt_update",postNo);
	    }
		
		//Certificate 글 수정
		public int certificateCategoryUpdate(CertificateBoard_Dto dto) throws Exception {
			return getSqlSession().update("board.certificate_update",dto);
		}
		
		//Certificate 글 삭제
		public int certificateCategoryDelete(int postNo) throws Exception {
			return getSqlSession().delete("board.certificate_delete",postNo);
		}
		
		//Certificate 게시물 총 갯수 + 검색
		public int certificateCategoryCount(String searchType, String keyword) throws Exception{
			
			HashMap<String,Object> data =new HashMap<>();
			
			data.put("searchType",searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectOne("board.certificate_count",data);
		}
		//Certificate게시판 답글 갯수 수정
		public int certificateCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
			
			HashMap<String,Object> data =new HashMap<>();
			data.put("postNo", postNo);
			data.put("commentsCount", commentsCount);
			
			return getSqlSession().update("board.certificate_commentsCount_update",data);
			
		}
		
		//Main페이지에 보여줄 최근 Certificate게시글 10개 조회
			public List<CertificateBoard_Dto> getMainCertificateCategory() throws Exception {
				return getSqlSession().selectList("board.certificate_mainPage");
			}
		


}
