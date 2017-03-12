package com.force4us.service;

import java.util.List;

import com.force4us.bean.Message;
import com.force4us.dao.MessageDao;

/**
 * 
 * 列表相关业务功能
 *
 */
public class ListService {
	public List<Message> queryMessageList(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
}
