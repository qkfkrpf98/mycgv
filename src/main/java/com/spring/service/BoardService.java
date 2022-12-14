package com.spring.service;

import java.util.ArrayList;

import com.mycgv.vo.CgvBoardVO;

public interface BoardService {
	int getTotalCount();	//�Խñ� ��ü��
	ArrayList<CgvBoardVO> getList(int startCount, int endCount);	//�Խñ� ��ü ����Ʈ
	int getWriteResult(CgvBoardVO vo);		//�Խñ� ����
	CgvBoardVO getContent(String bid);		//�Խñ� �󼼺���
	void getUpdateHits(String bid);			//�Խñ� ��ȸ�� ������Ʈ
	int getUpdate(CgvBoardVO vo);				//�Խñ� ���� ó��
	int getDelete(String bid);				//�Խñ� ���� ó��
}
