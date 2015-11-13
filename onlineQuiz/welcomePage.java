package onlineQuiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.core.ID;

import lab2.Homework1Users;


@WebServlet("/onlineQuiz/welcomePage")
public class welcomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public welcomePage() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Welcome Page</title>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css\">");
		out.println("</head>");
		out.println("<body bgcolor=\"red\">");
		out.println("<font>");
		out.println("	<center>");
		out.println("<img src=\"prometric_logo.png\" height=\"150\" width=\"1340\">");
		out.println("<div align=\"center\">");
		out.println("	<form action=\"welcomePage\" method=\"post\">");
		out.println("Welcome to Prometric Testing Center");
		out.println("	<fieldset><legend><b><font face=\"Courier New\" size=\"+2\"color=\"blue\">Login</b></font></legend>");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.println("<div align=\"center\">");
		out.println("Category &nbsp;&nbsp; :");
		out.print("<select name=\"mydropdown\">");
		out.print("<option value=\"Admin\" id=\"admin\">Admin");
		
				String dropdown = request.getParameter("mydropdown");
				//System.out.println("$$$$$$$$$$$$$$$$$$$$$"+dropdown);*/
				if(dropdown.equals("Admin"))
				{
					request.setAttribute("isAdmin", true);
					response.sendRedirect("AddRemoveQuestions.jsp");
				}	
				
				out.print("</option>");
	
		
		out.print("<option value=\"Student\" id=\"student\">Student</option>");
		out.println("</select> ");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.println("Username &nbsp;&nbsp; :");
		out.print("<input type=\"username\" name=\"username\" ");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.println("Password &nbsp;&nbsp; :");
		out.print("<input type=\"password\" name=\"password\" ");
		out.print("<br> </br>");
		out.print("<br> </br>");
		out.print("<input type=\"checkbox\" name=\"rememberMe\" value=\"Remember Me\">Remember Me");
		out.print("<br> </br>");
		out.print("<input type=\"submit\" value=\"Login\" >");
		out.println("	</fieldset>");
		out.println("</div>");
		out.println("	</form>");
		out.println("	</center>");
		out.println("	</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
