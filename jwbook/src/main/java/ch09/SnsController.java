package ch09;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

@MultipartConfig
public class SnsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsersDAO udao;
	private FeedsDAO fdao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		udao = new UsersDAO();
		fdao = new FeedsDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";

		if (action == null) {
			List<Feeds> list;
			list = fdao.getAll();
			request.setAttribute("feedlist", list);
			getServletContext().getRequestDispatcher("/feedlist.jsp").forward(request, response);
		} else {
			switch (action) {
			case "signup":
				view = signup(request, response);
				break;
			case "login":
				view = login(request, response);
				break;
			case "logout":
				view = logout(request, response);
				break;
			case "write":
				view = write(request, response);
				break;
			}
			getServletContext().getRequestDispatcher("/" + view).forward(request, response);
		}
	}

	public String signup(HttpServletRequest request, HttpServletResponse response) {
		Users u = new Users();

		try {
			BeanUtils.populate(u, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (udao.signup(u) != 0) {
			List<Feeds> list;
			list = fdao.getAll();
			request.setAttribute("feedlist", list);
			return "feedlist.jsp";
		} else {
			request.setAttribute("error", "회원가입에 실패하였습니다.");
			return "login.jsp";
		}
	}

	public String login(HttpServletRequest request, HttpServletResponse response) {
		Users u = new Users();
		String id = request.getParameter("id");
		String pw = request.getParameter("password");

		try {
			u = udao.login(id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u != null) {
			List<Feeds> list;
			list = fdao.getAll();
			request.setAttribute("feedlist", list);
			return "feedlist.jsp";
		} else {
			request.setAttribute("error", "로그인에 실패하였습니다.");
			return "login.jsp";
		}
	}

	public String logout(HttpServletRequest request, HttpServletResponse response) {
		return "logout.jsp";
	}

	public String write(HttpServletRequest request, HttpServletResponse response) {
		Feeds f = new Feeds();
		String id = (String) request.getSession().getAttribute("login_id");

		try {
			Part part = request.getPart("img");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
				File uploadDir = new File(getServletContext().getRealPath("/") + "img");
				if (!uploadDir.exists()) {
					uploadDir.mkdir();
				}
				part.write(uploadPath);
			}
			BeanUtils.populate(f, request.getParameterMap());
			f.setImg("img/" + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (fdao.write(f, id) != 0) {
			List<Feeds> list = null;
			request.setAttribute("feedlist", list);
			return "feedlist.jsp";
		} else {
			request.setAttribute("error", "글쓰기에 실패하였습니다.");
			return "feedList.jsp";
		}
	}

	private String getFilename(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		String[] tokens = contentDisposition.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 2, token.length() - 1);
			}
		}
		return null;
	}

}
