package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/trip/*")
public class loginFilter extends HttpFilter implements Filter {

	// 필터가 최초 사용될때 한번만 사용됨
	public void init(FilterConfig fConfig) throws ServletException {

	}

	// 클라이언트가 요청하면 실행되는 부분
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("doFilter 호출함");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		
		String action = request.getParameter("action");
		String path1 = httpRequest.getRequestURI();
		System.out.println("action : " + action);
		System.out.println("path1 : " + path1);
		
		  if (action == null 
				  || action.equals("login.do") 
				  || action.equals("loginForm.do") 
				  || action.equals("joinForm.do") 
				  || action.equals("join.do")
				  || action.equals("detail.do")
				  || action.equals("reservation.do")
				  || action.equals("noReservation.do")
				  || action.equals("signup.do")
				  || action.equals("main.do")) {
			  chain.doFilter(request, response);
			  
		  } else{
			  if (session.getAttribute("id") != null) {
				  chain.doFilter(request, response);
			  } else if(session.getAttribute("id") == null) {
				  httpResponse.sendRedirect(path1+"?action="+"loginForm.do");
			  }
		  }
	}

	public void destroy() {
	}

}
