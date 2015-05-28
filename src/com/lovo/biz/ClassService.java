package com.lovo.biz;

import java.util.List;

import com.lovo.entity.MyClass;
import com.lovo.vo.MyClassVO;

public interface ClassService {

	public List<MyClass> getAllClasses();
	
	public boolean removeClass(int id);
	
	public MyClassVO addNewClass(MyClass myClass);
}
