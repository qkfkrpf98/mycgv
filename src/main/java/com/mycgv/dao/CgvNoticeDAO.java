package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvNoticeVO;

@Repository
public class CgvNoticeDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace = "mapper.notice";
	
	/**
	 * totalCount : ��ü �ο�� ���-mapper.notice.totalcount
	 */
	public int totalCount() {
		return sqlSession.selectOne(namespace + ".totalcount");
	}
	
	/**
	 * delete : �������� ���� -mapper.notice.delete
	 */
	public int delete(String nid) {
		return sqlSession.delete(namespace+".delete",nid);
	}
	
	
	/**
	 * update : �������� ������Ʈ -mapper.notice.update
	 */
	public int update(CgvNoticeVO vo) {
		return sqlSession.update(namespace+".update", vo);
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ - mapper.notice.updatehits
	 */
	public void updateHits(String nid) {
		sqlSession.update(namespace+".updatehits",nid);		
	}
	
	/**
	 * select : �������� �󼼺���-mapper.notice.content
	 */
	public CgvNoticeVO select(String nid) {
		return sqlSession.selectOne(namespace+".content",nid);
	}
	
	
	/**
	 * select : ��ü �������� ����Ʈ
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvNoticeVO> list = sqlSession.selectList(namespace+".list", param);
		
		return (ArrayList<CgvNoticeVO>)list;
	}
	
	
	/**
	 * insert : �������� ���--mapper.notice.insert
	 */
	public int insert(CgvNoticeVO vo) {
		return sqlSession.insert(namespace+".insert", vo);
	}
}





