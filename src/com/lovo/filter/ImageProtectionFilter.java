package com.lovo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 防图片非法引用和盗链接的过滤器
 * @author 骆昊
 *
 */
@WebFilter(
		urlPatterns = { "*.jpg", "*.gif", "*.png" }, 
		initParams = { @WebInitParam(name = "MyURL", value = "http://localhost:8080/hw") }
)
public class ImageProtectionFilter implements Filter {
	private String myURL;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		myURL = config.getInitParameter("MyURL");
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hsRequest = (HttpServletRequest) req;
		
		String referer = hsRequest.getHeader("referer");
		
		if(referer != null) {
			if(referer.toLowerCase().startsWith(myURL.toLowerCase())) {
				chain.doFilter(req, resp);
			}
			else {
				((HttpServletResponse)resp).sendError(403);
			}
		}
		else {
			throw new ServletException();
		}
	}
	
	@Override
	public void destroy() {
	}
}
