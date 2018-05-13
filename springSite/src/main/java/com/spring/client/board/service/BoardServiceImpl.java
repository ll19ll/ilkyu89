package com.spring.client.board.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.client.board.dao.BoardDao;
import com.spring.client.board.vo.BoardVO;
import com.spring.client.reply.dao.ReplyDao;
import com.spring.client.reply.vo.ReplyVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	Logger logger = Logger.getLogger(BoardServiceImpl.class);

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private ReplyDao replyDao;

	// �� ��� ����
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> myList = null;
		// ���Ŀ� ���� �⺻�� ����
		if (bvo.getOrder_by() == null)
			bvo.setOrder_by("b_num");
		if (bvo.getOrder_sc() == null)
			bvo.setOrder_sc("DESC");
		myList = boardDao.boardList(bvo);
		return myList;
	}

	// ��ü ���ڵ� �� ����
	@Override
	public int boardListCnt(BoardVO bvo) {
		return boardDao.boardListCnt(bvo);
	}

	// �� �Է� ����
	@Override
	public int boardInsert(BoardVO bvo) {
		int result = 0;
		try {
			result = boardDao.boardInsert(bvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// �� �� ����
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		BoardVO detail = null;
		detail = boardDao.boardDetail(bvo);
		return detail;
	}

	// ��й�ȣ Ȯ�� ����
	@Override
	public int pwdConfirm(BoardVO bvo) {
		int result = 0;
		result = boardDao.pwdConfirm(bvo);
		return result;
	}

	// �� ���� ����
	@Override
	public int boardUpdate(BoardVO bvo) {
		int result = 0;
		try {
			result = boardDao.boardUpdate(bvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	// �� ���� ����
	// �ش� �Խù��� ����� �����ϸ� ��ü ��� ���� �� �Խù� ����
	@Override
	public int boardDelete(int b_num) {
		int result = 0;
		try {
			List<ReplyVO> list = replyDao.replyList(b_num);
			if (!list.isEmpty()) {
				result = replyDao.replyChoiceDelete(b_num);
			}
			result = boardDao.boardDelete(b_num);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

}
