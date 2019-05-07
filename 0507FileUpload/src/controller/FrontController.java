package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import serviceimpl.ServiceImpl;

@WebServlet("*.do")

@MultipartConfig(
		location = "/Users/a503_18/Documents")


//Controller에는 Service가 주입되어야 합니다.
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Service service;
	
    public FrontController() {
        super();
        service =ServiceImpl.getInstance(); 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//공통된 URL 부분을 제거 
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length() + 1);
		//요청 방식 가져오기
		String method = request.getMethod();
		//포워딩 할 때 사용할 RequestDispatcher 변수 선언
		RequestDispatcher dispatcher = null;
		if(command.toLowerCase().equals("fileupload.do") && method.toUpperCase().equals("GET")) {
			// views/fileupload.jsp 파일로 포워딩
			dispatcher = request.getRequestDispatcher("views/fileupload.jsp");
			dispatcher.forward(request, response);
		}
		else if(command.toLowerCase().equals("fileupload.do") && method.toUpperCase().equals("POST")) {
			//서비스 작업을 수행
			Map<String,Object> map = service.fileUpload(request);
			//서비스 작업에 성공하면
			if(map != null) {
				request.getSession().setAttribute("result",map);
				response.sendRedirect("result.do");
			}
			//서비스 작업에 실패하면
			else {
				response.sendRedirect("fileupload.do");
			}
		}else if(command.toLowerCase().equals("result.do")) {
			dispatcher = request.getRequestDispatcher("views/result.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
