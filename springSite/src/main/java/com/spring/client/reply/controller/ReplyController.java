package com.spring.client.reply.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.client.reply.service.ReplyService;
import com.spring.client.reply.vo.ReplyVO;

/**************************************************************
 * ���� : @RestController (@Controller + @ResponesBody) ������ Ư���� JSP�� ���� �並 ����� ����
 * ���� ������ �ƴ� REST ����� ������ ó���� ���ؼ� ����ϴ�(������ ��ü�� ��ȯ) ������̼��̴�.
 **************************************************************/

@RestController
@RequestMapping(value = "/replies")
public class ReplyController {
	Logger logger = Logger.getLogger(ReplyController.class);

	@Autowired
	private ReplyService replyService;

	/**************************************************************
	 * ��� �۸�� �����ϱ� * @return List<ReplyVO> ���� : @PathVariable�� URI�� ��ο��� ���ϴ� �����͸�
	 * �����ϴ� �뵵�� ������̼�. ���� ��û URL : http://localhost:8080/replies/all/�Խ��Ǳ۹�ȣ.do ���� ��û
	 * URL : http://localhost:8080/replies/replyList.do?b_num=�Խ��Ǳ۹�ȣ
	 **************************************************************/
	@RequestMapping(value = "/all/{b_num}.do", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("b_num") Integer b_num) {
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(replyService.replyList(b_num), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��� �� ���� ����
	@RequestMapping(value = "/replyInsert")
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo) {
		logger.info("replyInsert ȣ�� ����");
		ResponseEntity<String> entity = null;
		int result;
		try {
			result = replyService.replyInsert(rvo);
			if (result == 1) {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// ��� ��й�ȣ Ȯ��
	@RequestMapping(value = "/pwdConfirm.do")
	public ResponseEntity<Integer> pwdConfirm(@ModelAttribute ReplyVO rvo) {
		logger.info("pwdConfirm ȣ�� ����");
		ResponseEntity<Integer> entity = null;
		int result = 0;
		try {
			result = replyService.pwdConfirm(rvo);
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * ��� ���� ���� ���� : REST ��Ŀ��� UPDATE �۾��� PUT,PATCH����� �̿��ؼ� ó��. ��ü �����͸� �����ϴ� ��쿡��
	 * PUT�� �̿��ϰ�, �Ϻ��� �����͸� �����ϴ� ��쿡�� PATCH�� �̿�.
	 */
	@RequestMapping(value = "/{r_num}.do", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> replyUpdate(@PathVariable("r_num") Integer r_num, @RequestBody ReplyVO rvo) {
		logger.info("replyUpdate ȣ�� ����");
		ResponseEntity<String> entity = null;
		try {
			rvo.setR_num(r_num);
			replyService.replyUpdate(rvo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * ��� ���� ���� @return ���� : REST ��Ŀ��� DELETE �۾��� DELETE����� �̿��ؼ� ó��.
	 */
	@RequestMapping(value = "/{r_num}.do", method = RequestMethod.DELETE)
	public ResponseEntity<String> replyDelete(@PathVariable("r_num") Integer r_num) {
		logger.info("replyDelete ȣ�� ����");
		ResponseEntity<String> entity = null;
		try {
			replyService.replyDelete(r_num);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
