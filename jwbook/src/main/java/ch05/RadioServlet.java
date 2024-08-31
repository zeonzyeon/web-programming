package ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RadioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RadioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String selectedFruit = request.getParameter("fruit");
		String res = "<html><h3>Selected Fruit : " + selectedFruit + "</h3></html>";
		response.getWriter().print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
