package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.CertificateBoard_Dto;

public class CertificateBoard_Dao extends SqlSessionDaoSupport {
	//Certificate�Խ��� list��� + ����¡ +�˻�
		public List<CertificateBoard_Dto> getCertificateCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("displayPost",displayPost);
			data.put("postNum",postNum);
			data.put("searchType", searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectList("board.certificate_board",data);
		}
		
		//Certificate�� �ۼ�
		public int certificateCategoryWrite(CertificateBoard_Dto dto) throws Exception{
			return getSqlSession().insert("board.certificate_write",dto);
		}
		
		//Certificate�Խ��� ��ȸ
		public CertificateBoard_Dto certificateCategoryView(int postNo) throws Exception{
			return getSqlSession().selectOne("board.certificate_view",postNo);
		}
		
		//Certificate�� ��ȸ�� ����
	    public int certificateViewCountUpdate(int postNo) throws Exception{
	    	return getSqlSession().update("board.certificateViewCnt_update",postNo);
	    }
		
		//Certificate �� ����
		public int certificateCategoryUpdate(CertificateBoard_Dto dto) throws Exception {
			return getSqlSession().update("board.certificate_update",dto);
		}
		
		//Certificate �� ����
		public int certificateCategoryDelete(int postNo) throws Exception {
			return getSqlSession().delete("board.certificate_delete",postNo);
		}
		
		//Certificate �Խù� �� ���� + �˻�
		public int certificateCategoryCount(String searchType, String keyword) throws Exception{
			
			HashMap<String,Object> data =new HashMap<>();
			
			data.put("searchType",searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectOne("board.certificate_count",data);
		}
		//Certificate�Խ��� ��� ���� ����
		public int certificateCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
			
			HashMap<String,Object> data =new HashMap<>();
			data.put("postNo", postNo);
			data.put("commentsCount", commentsCount);
			
			return getSqlSession().update("board.certificate_commentsCount_update",data);
			
		}
		
		//Main�������� ������ �ֱ� Certificate�Խñ� 10�� ��ȸ
			public List<CertificateBoard_Dto> getMainCertificateCategory() throws Exception {
				return getSqlSession().selectList("board.certificate_mainPage");
			}
		


}