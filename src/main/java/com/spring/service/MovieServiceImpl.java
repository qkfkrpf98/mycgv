package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv.dao.CgvMovieDAO;
import com.mycgv.vo.CgvMovieVO;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private CgvMovieDAO movieDao;
	
	/**
	 * 영화 전체 로우수
	 */
	@Override
	public int getTotalCount() {
		return movieDao.totalCount();
	}
	
	/**
	 * 영화 전체 리스트
	 */
	@Override
	public ArrayList<CgvMovieVO> getList(int startCount, int endCount){
		return movieDao.select(startCount, endCount);
	}
	
	/**
	 * 영화 등록
	 */
	@Override
	public int getInsert(CgvMovieVO vo) {
		return movieDao.insert(vo);
	}
	
	/**
	 * 영화 등록 후 mid 가져오기
	 */
	@Override
	public String getMid() {
		return movieDao.selectMid();
	}
	
	/**
	 * 영화 파일 등록
	 */
	@Override
	public int getInsertFile(CgvMovieVO vo) {
		return movieDao.insert_file(vo);
	}
	
	/**
	 * 영화 상세보기
	 */
	@Override
	public CgvMovieVO getContent(String mid) {
		return movieDao.select(mid);
	}
	
	@Override
	public ArrayList<CgvMovieVO> selectList() {
		return movieDao.selectAll();
	}

}









