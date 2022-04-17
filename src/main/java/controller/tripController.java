package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.TripDAO;
import dto.*;



@WebServlet("/trip")
public class tripController extends HttpServlet {
	TripDAO tripdao = new TripDAO();
	
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("get");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
		System.out.println("post");
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
				int dormno = Integer.parseInt(request.getParameter("dormno"));
				DormDTO dormdto = tripdao.selectDorm(dormno);
				List<RoomDTO> roomsList = tripdao.selectRoomsList(dormno);
				List<ReviewDTO> reviewsList = tripdao.selectReviewsList(dormno);
				request.setAttribute("dormdto", dormdto);
				request.setAttribute("roomsList", roomsList);
				request.setAttribute("reviewsList", reviewsList);
				
				nextPage = "/trip01/detail.jsp";
				
			} else if (action.equals("history.do")) {
				
				nextPage = "/trip01/history.jsp";
				
			} else if (action.equals("upload.do")) {
				
				
				String picture = "";
				String encoding = "utf-8"; 
				
				File currentDirPath = new File("C:\\Users\\jin58\\eclipse-workspace\\project\\src\\main\\webapp\\image\\reivew");
				DiskFileItemFactory factory = new DiskFileItemFactory();  
				factory.setRepository(currentDirPath); 
				factory.setSizeThreshold(1024 * 1024); 

				ServletFileUpload upload = new ServletFileUpload(factory); 
				try {
					List items = upload.parseRequest(request);  
					for (int i = 0; i < items.size(); i++) {
						FileItem fileItem = (FileItem) items.get(i); 

						if (fileItem.isFormField()) {
							// =을 중심으로 들어온 값의 타입이 텍스트면 파라미터 키와 value를 적어달라
							System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
						} else { //FormField가 아닐떄
							System.out.println("param:" + fileItem.getFieldName());
							System.out.println("file name:" + fileItem.getName()); //업로드한 파일명
							System.out.println("file size:" + fileItem.getSize() + "bytes"); //파일의 사이즈 

							if (fileItem.getSize() > 0) { //사이즈가 있을떄, 깨진 파일에 관한 이슈가 있음.
								int idx = fileItem.getName().lastIndexOf("\\"); //파일이 있는 경로에 마지막 슬래쉬를 찾아라
								if (idx == -1) {
									idx = fileItem.getName().lastIndexOf("/");
								}
								String fileName = fileItem.getName().substring(idx + 1); // '/'이후에 파일명이 있기때문에 +1을 해줌
								long timestamp = System.currentTimeMillis();  //파일명 앞쪽에 시간을 작성해주기, 시간을 작성해주므로써 파일명이 겹치지 않게 됨.
								fileName = timestamp + "_" + fileName;
								picture = fileName;
								File uploadFile = new File(currentDirPath + "\\" + fileName);//내가 파일 저장할 위치를 넘겨줌.
								fileItem.write(uploadFile); //경로를 지정해주면 파일 업로드를 허락해준다.
							} 
						} 
					} 
					String title = request.getParameter("reviewtitle");
					String contents = request.getParameter("reviewcontents");
					double score = Double.parseDouble(request.getParameter("reviewscore"));
					long now = System.currentTimeMillis();
					Date date = new Date(now);
					int reservNo = Integer.parseInt(request.getParameter("reservno"));
					String memberId = request.getParameter("memberid");
					
					tripdao.insertReview(title, contents, score, date, picture, reservNo, memberId);
					
					nextPage = "/trip/detail.do";
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				

				
			}else {
				
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
	}

}
