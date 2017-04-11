package com.force4us.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.force4us.bean.Command;
import com.force4us.bean.CommandContent;
import com.force4us.bean.Message;
import com.force4us.dao.CommandDao;
import com.force4us.dao.MessageDao;
import com.force4us.entity.Page;
import com.force4us.util.Iconst;

public class QueryService {

	public List<Message> queryMessageList(String command, String description, Page page) {
		// 组织消息对象
		System.out.println("------------------->进入queryMessageList");
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		// 根据条件查询条数
		System.out.println("-------------------->开始计算条数");
		int totalNumber = messageDao.count(message);
		System.out.println("--------------------->" + totalNumber);
		// 组织分页查询参数
		page.setTotalNumber(totalNumber);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		// 分页查询并返回结果
		return messageDao.queryMessageList(parameter);
	}

	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(String command, String description, Page page) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		// 组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		parameter.put("message", message);
		parameter.put("page", page);
		MessageDao messageDao = new MessageDao();
		// 分页查询并返回结果
		return messageDao.queryMessageListByPage(parameter);
	}

	/*
	 * 根据指令查询自动回复的内容
	 * 
	 * @param commnd指令
	 * 
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		// 帮助
		if (Iconst.HELP_COMMAND.equals(command)) {
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < commandList.size(); i++) {
				if (i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}

		commandList = commandDao.queryCommandList(command, null);
		if (commandList.size() > 0) {
			List<CommandContent> contents = commandList.get(0).getContentList();
			int i = new Random().nextInt(contents.size());
			return contents.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}

}
