package com.lovo.dao;


import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lovo.util.ReflectionUtil;

/**
 * ͨ����ݷ��ʶ���(BaseDao)�ӿڵĻ�����ݿ��ȱʡ������
 * @author ���
 *
 * @param <E> ʵ������
 * @param <K> ʵ���־�ֶε�����
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoDbAdapter<E, K> extends BaseDaoAdapter<E, K> {
	private Class<?> entityClass;
	
	public BaseDaoDbAdapter() {
		ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<?>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * ͨ���α��ȡ����ʵ�����
	 * @param rs �α�
	 * @return ʵ����� 
	 */
	protected E fetchSingleEntity(ResultSet rs) throws SQLException {
		E entity = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		if(rs.next()) {
			try {
				entity = (E) entityClass.getConstructor().newInstance();
				String[] fieldNames = ReflectionUtil.getFieldNames(entity);
				for(int i = 0; i < fieldNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(fieldNames[i]));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return entity;
	}
	
	/**
	 * ͨ���α��ȡ����ʵ�����
	 * @param rs �α�
	 * @param fieldNames ʵ���ֶε�����
	 * @param colNames ��ݿ��к�ʵ���ֶζ�Ӧ���е�����
	 * @return ʵ����� 
	 */
	protected E fetchSingleEntity(ResultSet rs, String[] fieldNames, String[] colNames) 
			throws SQLException {
		E entity = null;
		ResultSetMetaData rsmd = rs.getMetaData();
		if(rs.next()) {
			try {
				entity = (E) entityClass.getConstructor().newInstance();
				for(int i = 0; i < colNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], rs.getObject(colNames[i]));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return entity;
	}
	
	/**
	 * ͨ���α��ȡ���ʵ�����
	 * @param rs �α�
	 * @return ʵ�������б�����
	 */
	protected List<E> fetchMultiEntities(ResultSet rs) throws SQLException {
		List<E> list = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
			try {
				E entity = (E) entityClass.getConstructor().newInstance();
				String[] fieldNames = ReflectionUtil.getFieldNames(entity);
				for(int i = 0; i < fieldNames.length && i < rsmd.getColumnCount(); i++) {
					
					ReflectionUtil.setValue(entity, fieldNames[i], convertBigInt(rs.getObject(fieldNames[i])));
				}
				list.add(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list.size() == 0 ? Collections.EMPTY_LIST : list;
	}
	
	/**
	 * ͨ���α��ȡ���ʵ�����
	 * @param rs �α�
	 * @param fieldNames ʵ���ֶε�����
	 * @param colNames ��ݿ��к�ʵ���ֶζ�Ӧ���е�����
	 * @return ʵ�������б�����
	 */
	protected List<E> fetchMultiEntities(ResultSet rs, String[] fieldNames, String[] colNames) 
			throws SQLException {
		List<E> list = new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()) {
			try {
				E entity = (E) entityClass.getConstructor().newInstance();
				for(int i = 0; i < colNames.length && i < rsmd.getColumnCount(); i++) {
					ReflectionUtil.setValue(entity, fieldNames[i], convertBigInt(rs.getObject(colNames[i])));
				}
				list.add(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return list.size() == 0 ? Collections.EMPTY_LIST : list;
	}
	
	private Object convertBigInt(Object data) throws SQLException
	{
		Object result = data;
		if(data instanceof java.math.BigDecimal)
		{
			result = ((java.math.BigDecimal)data).intValue();
		}
		return result;
	}
}
