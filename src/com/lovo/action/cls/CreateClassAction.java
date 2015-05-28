package com.lovo.action.cls;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.action.Action;
import com.lovo.action.ActionResult;
import com.lovo.action.ResultContent;
import com.lovo.action.ResultType;
import com.lovo.biz.ClassService;
import com.lovo.biz.ServiceFactory;
import com.lovo.entity.MyClass;
import com.lovo.vo.MyClassVO;

/**
 * 创建班级的Action
 * @author 骆昊
 *
 */
public class CreateClassAction implements Action {
	private String name;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MyClassVO vo = getClassService().addNewClass(new MyClass(name));
		return new ActionResult(new ResultContent(vo), ResultType.Ajax);
	}
	
	private ClassService getClassService() {
		return (ClassService) ServiceFactory.factory(ClassService.class);
	}

}
