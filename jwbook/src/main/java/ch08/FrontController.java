package ch08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriParts = uri.split("/");
		String action = uriParts[uriParts.length - 1]; // 마지막 요소가 액션

		SubController controller = null;

		if (action.equals("create.do")) {
			controller = new CreateController();
		} else if (action.equals("login.do")) {
			controller = new LoginController();
		} else {
			// 예외 처리
		}
		if (controller != null)
			controller.process(request, response);

		/*
		 * if (action.equals("create.do")) {
		 * response.getWriter().append("Create : ").append(request.getParameter(
		 * "username")); } else if (action.equals("login.do")) {
		 * response.getWriter().append("Login : ").append(request.getParameter(
		 * "username")); } else { // 예외 처리 }
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
