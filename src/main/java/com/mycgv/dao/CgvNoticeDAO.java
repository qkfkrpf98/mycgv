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
	 * totalCount : 전체 로우수 출력-mapper.notice.totalcount
	 */
	public int totalCount() {
		return sqlSession.selectOne(namespace + ".totalcount");
	}
	
	/**
	 * delete : 공지사항 삭제 -mapper.notice.delete
	 */
	public int delete(String nid) {
		return sqlSession.delete(namespace+".delete",nid);
	}
	
	
	/**
	 * update : 공지사항 업데이트 -mapper.notice.update
	 */
	public int update(CgvNoticeVO vo) {
		return sqlSession.update(namespace+".update", vo);
	}
	
	/**
	 * updateHits : 조회수 업데이트 - mapper.notice.updatehits
	 */
	public void updateHits(String nid) {
		sqlSession.update(namespace+".updatehits",nid);		
	}
	
	/**
	 * select : 공지사항 상세보기-mapper.notice.content
	 */
	public CgvNoticeVO select(String nid) {
		return sqlSession.selectOne(namespace+".content",nid);
	}
	
	
	/**
	 * select : 전체 공지사항 리스트
	 */
	public ArrayList<CgvNoticeVO> select(int startCount, int endCount){
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvNoticeVO> list = sqlSession.selectList(namespace+".list", param);
		
		return (ArrayList<CgvNoticeVO>)list;
	}
	
	
	/**
	 * insert : 공지사항 등록--mapper.notice.insert
	 */
	public int insert(CgvNoticeVO vo) {
		return sqlSession.insert(namespace+".insert", vo);
	}
}





