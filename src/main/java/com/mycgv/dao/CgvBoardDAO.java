package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvBoardVO;

@Repository
public class CgvBoardDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * totalCount : ��ü �ο�� ���-mapper.board.totalcount
	 */
	public int totalCount() {
		return sqlSession.selectOne("mapper.board.totalcount");
	}
	
	/**
	 * delete : �Խñ� ����-mapper.board.delete
	 */
	public int delete(String bid) {
		return sqlSession.delete("mapper.board.delete",bid);
	}
	
	
	/**
	 * update : �Խñ� ����-mapper.board.update
	 */
	public int update(CgvBoardVO vo) {		
		return sqlSession.update("mapper.board.update",vo);
	}
	
	/**
	 * updateHits : ��ȸ�� ������Ʈ - mapper.board.updatehits
	 */
	public int updateHits(String bid) {
		return sqlSession.update("mapper.board.updatehits",bid);
	}
	
	/**
	 * select : �Խñ� �� ����-mapper.board.content
	 */
	public CgvBoardVO select(String bid) {
		return sqlSession.selectOne("mapper.board.content",bid);
	}
	
	/**
	 * select : �Խñ� ��ü ����Ʈ(����¡ó��) - mapper.board.list
	 */
	public ArrayList<CgvBoardVO> select(int startCount, int endCount){
		
		//�Ķ���͸� Map���� �����ϱ�
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("start", startCount);	
		param.put("end", endCount);
		
		List<CgvBoardVO> list = sqlSession.selectList("mapper.board.list", param);
		
		
		return (ArrayList<CgvBoardVO>)list;
	}
	
	
	/**
	 * insert : �Խñ� ��� - mapper.board.insert
	 */
	public int insert(CgvBoardVO vo) {
		return sqlSession.insert("mapper.board.insert", vo);
	}
}






