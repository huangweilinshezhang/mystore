//package com.springboot.web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.springboot.model.User;
//import com.springboot.service.UserServervice;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Servlet implementation class LoginServlet
// */
//
//@WebServlet("/userLogin")
//public class login extends HttpServlet {
//	@Autowired
////把数据往Java容器中注入
//	private UserServervice userServervice;
//
//	public login(UserServervice userServervice) {
//		this.userServervice = userServervice;
//	}
//
//	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=utf-8");
//
//		Integer id= Integer.valueOf(request.getParameter("id"));
//		String password=request.getParameter("password");
//		System.out.println("userName"+id);
//		System.out.println("password"+password);
//
//		User user=userServervice.selectUserByIdTo(id);
//		int count=user.getUserPassword().compareTo(password);
//		System.out.println("user"+user);
//		System.out.println("count"+count);
//		if(count>0) {
//			HttpSession session =request.getSession();
////			LMONKEY_USER user=LMONKEY_USERDao.selectAdmin(userName,passWord);
//
//			session.setAttribute("user", user);
//			session.setAttribute("isLogin", "1");
//			response.sendRedirect("index");
//
//		}else {
//			PrintWriter out=response.getWriter();
//
//			out.write("<script>");
//
//			out.write("alert('�û���¼ʧ�ܣ�');");
//			out.write("location.href='index.html';");
//			out.write("</script>");
//			out.close();
//		}
//	}
//
//}
