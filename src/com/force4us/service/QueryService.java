package com.force4us.service;

import java.util.List;

import com.force4us.bean.Message;
import com.force4us.dao.MessageDao;
import com.force4us.util.Iconst;

public class QueryService {
	/*
	 * 根据指令查询自动回复的内容
	 * 
	 * @param commnd指令
	 * 
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command) {
		MessageDao messageDao = new MessageDao();
		List<Message> messageList;
		if (Iconst.HELP_COMMAND.equals(command)) {
			messageList = messageDao.queryMessageList(null, null);
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < messageList.size(); i++) {
				if (i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + messageList.get(i).getCommand() + "]可以查看" + messageList.get(i).getDescription());
			}
			return result.toString();
		}
		messageList = messageDao.queryMessageList(command, null);
		if (messageList.size() > 0) {
			return messageList.get(0).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}

}
