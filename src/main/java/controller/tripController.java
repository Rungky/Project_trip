package controller;

import java.io.IOException;
<<<<<<< HEAD

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
	
=======
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import dao.TripDAO;
import dto.*;



@WebServlet("/trip")
public class tripController extends HttpServlet {
	TripDAO tripdao = new TripDAO();
	
	


>>>>>>> fb118253d2225728530d8c30e89b6b8917e99ab1
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("get");
	}
<<<<<<< HEAD

=======
	
>>>>>>> fb118253d2225728530d8c30e89b6b8917e99ab1
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("post");
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
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
=======

		String nextPage = "";
		String action = " ";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(request.getParameter("action") != null)
			action = request.getParameter("action");
		
		
		try {
			
			if (action.equals(" ")) {
				
				nextPage = "main.jsp";
			}  else if (action.equals("reservation.do")) {
				nextPage = "/reservation.jsp";
				
			} else if (action.equals("detail.do")) {
				int dromno = Integer.parseInt(request.getParameter("dormno"));
				DormDTO dormdto = tripdao.selectDorm(dromno);
				List<RoomDTO> roomsList = tripdao.selectRoomsList(dromno);
				request.setAttribute("dormdto", dormdto);
				request.setAttribute("roomsList", roomsList);
				
				nextPage = "/trip01/detail.jsp";
				
			} else if (action.equals("history.do")) {
>>>>>>> fb118253d2225728530d8c30e89b6b8917e99ab1
				
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
