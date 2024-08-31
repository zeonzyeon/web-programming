package ch05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckboxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] use1 = request.getParameterValues("fruit");
		String res = "<html>";
		for (int i=0; i<use1.length;i++) {
			res +="<h3>Selected fruit : " + use1[i] + "</h3>";
		}
		res += "</html>";
		response.getWriter().print(res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
