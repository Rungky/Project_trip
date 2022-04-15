package controller;

import java.io.IOException;

import dao.TripDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class testController
 */
@WebServlet("/trip.do")
public class tripController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("post");
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TripDAO tripdao = new TripDAO();
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String action = request.getParameter("action");
		
		try {
			
			if (action.equals("/main.do")) {
				
			} else if (action.equals("/detail.do")) {
				
				nextPage = "/trip01/detail.jsp";
				
			} else if (action.equals("/history.do")) {
				
				nextPage = "/trip01/history.jsp";
				
			} else {
				
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
	}

}
