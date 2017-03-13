package com.force4us.service;

import com.force4us.dao.MessageDao;

/*
 * 维护相关的业务功能	
 */
public class MaintainService {
	/*
	 * 单条删除
	 */

	public void deleteOne(String id) {
		if (id != null && !"".equals(id.trim())) {
			MessageDao dao = new MessageDao();
			dao.deleteOne(Integer.parseInt(id));
		}

	}
}
