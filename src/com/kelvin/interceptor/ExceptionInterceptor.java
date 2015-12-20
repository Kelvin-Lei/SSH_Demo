package com.kelvin.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * 异常处理拦截器
 *
 */
public class ExceptionInterceptor implements Interceptor {

	public void destroy() {}
	
	public void init() {}
	
	public String intercept(ActionInvocation ai) throws Exception {
		try {
			return ai.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			//处理异常，报错时转向统一的错误页面
			return "error";
		}
	}
}
