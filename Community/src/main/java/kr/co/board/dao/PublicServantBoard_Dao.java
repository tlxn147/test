package kr.co.board.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.board.dto.PublicServantBoard_Dto;

public class PublicServantBoard_Dao extends SqlSessionDaoSupport {
	
	//PublicServant�Խ��� list��� + ����¡ +�˻�
		public List<PublicServantBoard_Dto> getPublicServantCategoryList(int displayPost, int postNum, String searchType, String keyword) throws Exception {
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			
			data.put("displayPost",displayPost);
			data.put("postNum",postNum);
			data.put("searchType", searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectList("board.publicServant_board",data);
		}
		
		//PublicServant�� �ۼ�
		public int publicServantCategoryWrite(PublicServantBoard_Dto dto) throws Exception{
			return getSqlSession().insert("board.publicServant_write",dto);
		}
		
		//PublicServant�Խ��� ��ȸ
		public PublicServantBoard_Dto publicServantCategoryView(int postNo) throws Exception{
			return getSqlSession().selectOne("board.publicServant_view",postNo);
		}
		
		//PublicServant�� ��ȸ�� ����
	    public int publicServantViewCountUpdate(int postNo) throws Exception{
	    	return getSqlSession().update("board.publicServantViewCnt_update",postNo);
	    }
		
		//PublicServant �� ����
		public int publicServantCategoryUpdate(PublicServantBoard_Dto dto) throws Exception {
			return getSqlSession().update("board.publicServant_update",dto);
		}
		
		//PublicServant �� ����
		public int publicServantCategoryDelete(int postNo) throws Exception {
			return getSqlSession().delete("board.publicServant_delete",postNo);
		}
		
		//PublicServant �Խù� �� ���� + �˻�
		public int publicServantCategoryCount(String searchType, String keyword) throws Exception{
			
			HashMap<String,Object> data =new HashMap<>();
			
			data.put("searchType",searchType);
			data.put("keyword", keyword);
			
			return getSqlSession().selectOne("board.publicServant_count",data);
		}
		//PublicServant�Խ��� ��� ���� ����
		public int publicServantCommentsCountUpdate(int postNo, int commentsCount) throws Exception {
			
			HashMap<String,Object> data =new HashMap<>();
			data.put("postNo", postNo);
			data.put("commentsCount", commentsCount);
			
			return getSqlSession().update("board.publicServant_commentsCount_update",data);
			
		}
		
		//Main�������� ������ �ֱ� PublicServant�Խñ� 10�� ��ȸ
			public List<PublicServantBoard_Dto> getMainPublicServantCategory() throws Exception {
				return getSqlSession().selectList("board.publicServant_mainPage");
			}

}