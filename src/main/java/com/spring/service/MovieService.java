package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvMovieVO;

public interface MovieService {
	int getTotalCount();				//��ȭ ��ü �ο��
	ArrayList<CgvMovieVO> getList(int startCount, int endCount);  	//��ȭ ��ü ����Ʈ
	int getInsert(CgvMovieVO vo);		//��ȭ ���
	String getMid();					//��ȭ ��� �� mid ��������
	int getInsertFile(CgvMovieVO vo);	//��ȭ ���
	CgvMovieVO getContent(String mid);  //��ȭ �󼼺���
	ArrayList<CgvMovieVO> selectList();            //������������ �Ѹ� ��ȭ����Ʈ
}
