package com.mycgv.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv.vo.CgvMovieVO;

@Repository
public class CgvMovieDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="mapper.movie";
	
	/**
	 * select : ��ȭ �� ����
	 */
	public CgvMovieVO select(String mid) {
		return sqlSession.selectOne(namespace+".content", mid);
	}
	
	
	/**
	 * select : ��ü ��ȭ ����Ʈ
	 */
	public ArrayList<CgvMovieVO> select(int startCount, int endCount){
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<CgvMovieVO> list = sqlSession.selectList(namespace+".list", param);
		
		return (ArrayList<CgvMovieVO>)list;
	}
	
	
	/**
	 * totalCount : ��ü �ο�� ���
	 */
	public int totalCount() {
		return sqlSession.selectOne(namespace+".totalcount");
	}
	
	/**
	 * insert_file : ��ȭ�̹��� ��� ó��
	 */
	public int insert_file(CgvMovieVO vo) {
		return sqlSession.insert(namespace+".insertfile",vo);
	}
	
	
	/**
	 * selectMid : mid ��������
	 */
	public String selectMid() {
		return sqlSession.selectOne(namespace+".mid");
	}
	
	/**
	 * insert : ��ȭ��� ���
	 */
	public int insert(CgvMovieVO vo) {
		return sqlSession.insert(namespace+".insert", vo);
	}
	
	public ArrayList<CgvMovieVO> selectAll() {
		List<CgvMovieVO> list = sqlSession.selectList(namespace+".selectAll");
		return (ArrayList<CgvMovieVO>)list;
	}
}











