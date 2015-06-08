/**
 * ApplicationUtil.java
 * com.vko.controllers.util
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/

package com.xiaohao.elasticsearch.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ConfigurableWebApplicationContext;
/**
 * Created by Administrator on 15-6-4.
 */
public class ApplicationUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public static void setApplication(ApplicationContext context) {
		ApplicationUtil.context = context;
	}

	public static <T> T getBean(Class<T> cls) {
		return context.getBean(cls);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> cls) {
		return context.getBean(name, cls);
	}

	public static boolean containsBean(String name) {
		return context.containsBean(name);
	}

	public static ConfigurableWebApplicationContext getContext() {
		return (ConfigurableWebApplicationContext) context;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}
