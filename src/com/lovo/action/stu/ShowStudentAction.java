package com.lovo.action.stu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lovo.action.Action;
import com.lovo.action.ActionResult;
import com.lovo.action.ResultContent;
import com.lovo.action.ResultType;
import com.lovo.beans.PageBean;
import com.lovo.biz.ServiceFactory;
import com.lovo.biz.StudentService;
import com.lovo.entity.Student;

/**
 * 查看班级学生的Action
 * @author 骆昊
 *
 */
public class ShowStudentAction implements Action {
	private int page;
	private int classId;
	
	@Override
	public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PageBean<Student> pageBean = getStudentService().getStudentsByPage(classId, page, 5);
		return new ActionResult(new ResultContent(pageBean), ResultType.Ajax);
	}
	
	private StudentService getStudentService() {
		return (StudentService) ServiceFactory.factory(StudentService.class);
	}

}
