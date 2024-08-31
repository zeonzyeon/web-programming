package ch08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class urlController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public urlController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if ("delete".equals(action)) {
			response.getWriter().append("Served at: ").append(action);
		} else if ("update".equals(action)) {
			response.getWriter().append("Served at: ").append(action);
		} else {
			response.getWriter().append("Served at: ").append(action);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
