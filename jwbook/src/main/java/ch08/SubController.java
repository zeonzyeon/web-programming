package ch08;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SubController {
	void process(HttpServletRequest request, HttpServletResponse response);
}
