package ch09;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import javax.sql.rowset.serial.SerialBlob;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDAO dao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new StudentDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";

		if (action == null) {
			StudentDAO dao = new StudentDAO();
			byte[] imageBytes = dao.getImageByUsername(request.getParameter("username"));
			response.setContentType("image/jpeg");
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(imageBytes);
			outputStream.close();
		} else {
			switch (action) {
			case "login":
				view = login(request, response);
				break;
			case "signup":
				view = signup(request, response);
				break;
			}
			getServletContext().getRequestDispatcher("/" + view).forward(request, response);
		}

	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		try {
			s = dao.login(name, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (s != null) {
			try {
				request.setAttribute("info", dao.getInfo(name));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "Login_success.jsp";
		} else
			return "Login_failure.jsp";
	}

	public String signup(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();

		try {
			Part filePart = request.getPart("imageFile");

			if (filePart != null) {
				InputStream inputStream = filePart.getInputStream();

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageData = outputStream.toByteArray();

				Blob photo = new SerialBlob(imageData);
				s.setPhoto(photo);

				outputStream.close();
				inputStream.close();
			}
			s.setUsername(request.getParameter("username"));
			s.setPassword(request.getParameter("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (dao.signup(s) != 0) {
			request.setAttribute("info", s);
			return "Signup_success.jsp";
		} else {
			return "Signup_failure.jsp";
		}
	}

}
