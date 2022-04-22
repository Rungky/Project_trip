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
//		String path = httpRequest.getQueryString();
		System.out.println("action : " + action); // null값이 들어옴
		System.out.println("path1 : " + path1);
		// 제외한 url이 들어 왔을때 동작할 코드 작성
		
		
		
			// 세션이 없는 경우 : 로그인페이지로
		// 단, ㅣ요런요런 페이지는 세션 없이 접근 가능
		// 세션 있는 경우 : 원래 의도했던 곳으로 인도
		
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

	// 필터 객체가 제거될 때 실행됨.
	public void destroy() {
	}

}
