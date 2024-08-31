package ch08;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController implements SubController {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {
// TODO Auto-generated method stub
		try {
			response.getWriter().append("Login : ").append(request.getParameter("username"));
		} catch (IOException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
