package ch08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalcController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CalcController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Calculator c = new Calculator();
		c.setN1(Integer.parseInt(request.getParameter("n1")));
		c.setN2(Integer.parseInt(request.getParameter("n2")));
		c.setOp(request.getParameter("op"));
		long result = c.calc();
		
		request.setAttribute("result", result);
		getServletContext().getRequestDispatcher("/calcResult.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
/*
 * 컨트롤러 구현 (서블릿 생성)
 * 
 * 컨트롤러의 기능인 1.클라이언트 요청처리 2.입력값 핸들링 3.뷰 이동
 * 이 모두 들어가 있음
 */