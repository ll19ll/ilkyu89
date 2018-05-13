package com.spring.client.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.client.board.vo.BoardVO;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession session;

	// �� ��� ����
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		return session.selectList("boardList", bvo);
	}

	// ��ü ���ڵ� �Ǽ� ����
	@Override
	public int boardListCnt(BoardVO bvo) {
		return (Integer) session.selectOne("boardListCnt");
	}

	// �� �� ����
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		return (BoardVO) session.selectOne("boardDetail", bvo);
	}

	// �� �Է� ����
	@Override
	public int boardInsert(BoardVO bvo) {
		return session.insert("boardInsert", bvo);
	}

	// ��й�ȣ Ȯ�� ����
	@Override
	public int pwdConfirm(BoardVO bvo) {
		return (Integer) session.selectOne("pwdConfirm", bvo);
	}

	// �� ���� ����
	@Override
	public int boardUpdate(BoardVO bvo) {
		return session.update("boardUpdate", bvo);
	}

	// �� ���� ����
	@Override
	public int boardDelete(int b_num) {
		return session.delete("boardDelete", b_num);
	}
}