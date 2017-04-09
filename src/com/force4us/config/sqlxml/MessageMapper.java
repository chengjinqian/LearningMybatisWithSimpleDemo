package com.force4us.config.sqlxml;

import java.util.List;

import com.force4us.bean.Message;

public interface MessageMapper {
	List<Message> queryMessageList(Message m);
}
