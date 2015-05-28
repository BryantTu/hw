package com.lovo.action.stu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.action.Action;
import com.lovo.action.ActionResult;
import com.lovo.action.ResultContent;
import com.lovo.action.ResultIndicator;
import com.lovo.action.ResultType;
import com.lovo.biz.ServiceFactory;
import com.lovo.biz.StudentService;
import com.lovo.entity.Student;

public class DeleteAction implements Action {
	private Student stu;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean flag = getStudentService().removeStudentById(stu.getId());
		return new ActionResult(new ResultContent(new ResultIndicator(flag)), ResultType.Ajax);
	}
	
	private StudentService getStudentService() {
		return (StudentService) ServiceFactory.factory(StudentService.class);
	}

}
