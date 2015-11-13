package onlineQuiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveAnswer
 */
@WebServlet("/onlineQuiz/Quiz")
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			
		/*	String ans = request.getParameter("option");
			String userAns = null;
			if (ans.equals("a")) {
				userAns = "true";
			} else {
				userAns = "false";
			}*/
			String ans = request.getParameter("answer");
			//String userAns = null;
			/*if (ans.equals("a")) {
				userAns = "true";
			} else {
				userAns = "false";
			}*/
			
			ArrayList<String> userAnsList = new ArrayList<String>();
			/*if (userAnsList.size() <= 5) {
				userAnsList.add(userAns);
				response.sendRedirect("Quiz.jsp");
			} else {
				response.sendRedirect("Result.jsp");
			}*/
			
			while (userAnsList.size() <= 5) {
				userAnsList.add(ans);
				response.sendRedirect("Quiz.jsp");
			} 
				response.sendRedirect("Result.jsp");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
