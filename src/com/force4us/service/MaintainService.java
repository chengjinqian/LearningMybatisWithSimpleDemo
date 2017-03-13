package com.force4us.service;

import java.util.ArrayList;
import java.util.List;

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

	/*
	 * 批量删除
	 */
	public void deleteBatch(String[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		MessageDao dao = new MessageDao();
		for (String id : ids) {
			idList.add(Integer.parseInt(id));
		}
		dao.deleteBatch(idList);

	}
}
