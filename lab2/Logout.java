package lab2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab2/Logout")
public class Logout extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public Logout() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().getAttribute("Lab2User");
		response.sendRedirect("LoginServlet");
	}

}
