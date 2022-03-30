package com.springboot.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Register
 */
@WebFilter("/register")
public class Register implements Filter {

	/**
	 * Default constructor.
	 */
	public Register() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		System.out.println(req);
        System.out.println(resp);

		//设置字符库
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/css;charset=utf-8");

		String userName=req.getParameter("userName");

		PrintWriter out=resp.getWriter();
		if(userName.equals("")) {
			out.write("<script>");
			out.write("alert('用户名称不能为空！');");
			out.write("location.href='reg.jsp';");
			out.write("</script>");
			out.close();
			return;
		}

		//过滤器验证码输入开始
		HttpSession session=req.getSession();
		String verycode=req.getParameter("veryCode");
		String sysCode=(String)session.getAttribute("code");
		System.out.println(verycode);
		System.out.println(sysCode);
		if(!sysCode.equals(verycode)) {
			out.write("<script>");
			out.write("alert('验证码输入错误！');");
			out.write("location.href='reg.html';");
			out.write("</script>");
			out.close();
			return;
		}
		//过滤器验证码输入结束

		// pass the request along the filter chain
		chain.doFilter(req, resp);//通过则使用这条语句，不通过则直接return可以
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

