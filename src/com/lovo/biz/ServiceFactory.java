package com.lovo.biz;

import java.util.HashMap;
import java.util.Map;

import com.lovo.util.CommonUtil;

/**
 * 创建业务逻辑代理对象的工厂 (登记式单例模式)
 * @author 骆昊
 *
 */
public class ServiceFactory {
	private static final String DEFAULT_IMPL_PACKAGE_NAME = "impl";
	
	private static Map<Class<?>, Object> map = new HashMap<>();

	/**
	 * 工厂方法
	 * @param type 业务逻辑对象的类型
	 * @return 业务逻辑对象的代理对象
	 */
	public static synchronized Object factory(Class<?> type) {
		if(map.containsKey(type)) {
			return map.get(type);
		}
		else {
			try {
				Object serviceObj = Class.forName(
						type.getPackage().getName() + "." + DEFAULT_IMPL_PACKAGE_NAME + "." 
						+ type.getSimpleName() + CommonUtil.capitalize(DEFAULT_IMPL_PACKAGE_NAME)).newInstance();
				map.put(type, ServiceProxy.getProxyInstance(serviceObj));
				return serviceObj;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
}
