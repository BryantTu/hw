package com.lovo.action.cls;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.action.Action;
import com.lovo.action.ActionResult;
import com.lovo.action.ResultContent;
import com.lovo.action.ResultIndicator;
import com.lovo.action.ResultType;
import com.lovo.biz.ClassService;
import com.lovo.biz.ServiceFactory;

/**
 * É¾³ý°à¼¶µÄAction
 * @author Âæê»
 *
 */
public class DeleteClassAction implements Action {
	private int id;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag = getClassService().removeClass(id);
		return new ActionResult(new ResultContent(new ResultIndicator(flag)), ResultType.Ajax);
	}
	
	private ClassService getClassService() {
		return (ClassService) ServiceFactory.factory(ClassService.class);
	}

}
