package com.lovo.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lovo.beans.PageBean;
import com.lovo.dao.BaseDaoDbAdapter;
import com.lovo.dao.StudentDao;
import com.lovo.entity.Student;
import com.lovo.exception.DbSessionException;
import com.lovo.util.DbSessionFactory;

public class StudentDaoDbImpl 
		extends BaseDaoDbAdapter<Student, Integer>
		implements StudentDao {
	private static final String[]  colNames= { 
			"stuid", "name", "sex", "age", "cid"
	};
	private static final String[] fieldNames = { 
			"id", "name", "gender", "tel", "myClass"
	};

	@Override
	public PageBean<Student> findByClassId(int classId, int page, int size) {
		try {
			ResultSet rs = DbSessionFactory.openSession().executeQuery(
					"SELECT stuid, name, sex, age, cid FROM (SELECT T.*, ROWNUM RN FROM (SELECT * FROM T_STUDENT where cid = ? order by stuid) T WHERE ROWNUM<=?) WHERE RN>?",
					classId, page * size, (page - 1) * size);
			List<Student> list = fetchMultiEntities(rs, fieldNames, colNames);
			rs = DbSessionFactory.openSession().executeQuery(
					"select count(stuid) from t_student where cid=?",
					classId);
			return new PageBean<Student>(list, page, size,
					rs.next() ? rs.getInt(1) : 0);
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

	@Override
	public Student findById(Integer id) {
		try {
			return fetchSingleEntity(DbSessionFactory.openSession().executeQuery(
					"select * from t_student where stuid=?", id), fieldNames, colNames);
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

	@Override
	public boolean update(Student entity) {
		return DbSessionFactory.openSession().executeUpdate(
				"update t_student set name=?, sex=?, age=? where stuid=?", 
				entity.getName(), entity.isGender(), entity.getTel(), entity.getId()).getAffectedRows() == 1;
	}

	@Override
	public boolean deleteById(Integer id) {
		return DbSessionFactory.openSession().executeUpdate(
				"delete from t_student where stuid=?", id).getAffectedRows() == 1;
	}

	@Override
	public int getStudentsCountByClassId(int classId) {
		try {
			ResultSet rs = DbSessionFactory.openSession().executeQuery(
					"select count(id) from t_student where cid=?", classId);
			return rs.next()? rs.getInt(1) : 0;
		} catch (SQLException e) {
			throw new DbSessionException(e);
		}
	}

}
