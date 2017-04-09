package com.force4us.service;

import java.util.List;
import java.util.Random;

import com.force4us.bean.Command;
import com.force4us.bean.CommandContent;
import com.force4us.dao.CommandDao;
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
