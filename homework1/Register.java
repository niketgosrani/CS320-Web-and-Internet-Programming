package homework1;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.*;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;


@WebServlet("/homework1/Register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		boolean submitted=false;
		// Add a few posts for testing
		ArrayList<Homework1User> Homework1Users = new ArrayList<Homework1User>();

		// Create an empty list of Homework1Users Posts
		if (context.getAttribute("Homework1User") == null) {
			context.setAttribute("Homework1User", Homework1Users);
		}
		if (context.getAttribute("submitted") == null) {
			context.setAttribute("submitted", submitted);
		}
		// Error Hashtable
		Hashtable errors = new Hashtable();

		if (context.getAttribute("Errors") == null) {
			context.setAttribute("Errors", errors);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		Hashtable errors = (Hashtable) this.getServletContext().getAttribute(
				"Errors");
		// Write HTML
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println(".error { color: red; }");
		out.println(".success { color: green; }");
		out.println("</style>");
		out.println("		<title>Register</title>");
		out.println("	</head>");
		out.println("	<body bgcolor=\"black\">");
		out.println("<font color=\"white\">");
		out.println("	<center>");
		out.println("	<form action=\"Register\" method=\"post\">");
		out.println("	<fieldset><legend><b><font face=\"Courier New\" size=\"+2\"color=\"blue\">Registration</b></font></legend>");
		out.println("	<table>");
		out.println("<tr>");
		out.println("<td>First Name &nbsp;&nbsp; :");
		if (errors.containsKey("firstName")) {
			out.print("<div class = \"error\" > Error: "
					+ errors.get("firstName").toString() + "</div>");
		}
		out.print("<br> </br></td>");
		out.print("<td> <input type=\"text\" name=\"firstname\" ");
        if(request.getParameter("firstname") != null)
        {
            out.print(" value= " + request.getParameter("firstname"));

        }
        out.print("><br> </br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Last Name &nbsp;&nbsp; :");
		if (errors.containsKey("lastName")) {
			out.print("<div class = \"error\" > Error: "
					+ errors.get("lastName").toString() + "</div>");
		}
		out.print("<br> </br></td>");
		out.print("<td> <input type=\"text\" name=\"lastname\" ");
        if(request.getParameter("lastname") != null)
        {
            out.print(" value= " + request.getParameter("lastname"));

        }
        out.print("><br> </br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Email ID &nbsp;&nbsp; :");
		if (errors.containsKey("email")) {
			out.print("<div class = \"error\" > Error: "
					+ errors.get("email").toString() + "</div>");
		}
		out.print("<br> </br></td>");
		out.print("<td> <input type=\"text\" name=\"emailid\" ");
        if(request.getParameter("emailid") != null)
        {
            out.print(" value= " + request.getParameter("emailid"));

        }
        out.print("><br> </br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Password &nbsp;&nbsp; :");
		if (errors.containsKey("password")) {
			out.print("<div class = \"error\" > Error: "
					+ errors.get("password").toString() + "</div>");
		}
		out.print("<br> </br></td>");
		out.print("<td> <input type=\"password\" name=\"password\" ");
        if(request.getParameter("password") != null)
        {
            out.print(" value= " + request.getParameter("password"));

        }
        out.print("><br> </br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Re-Enter Password &nbsp;&nbsp; :");
		if (errors.containsKey("repassword")) {
			out.print("<div class=\"error\"> Error: "
					+ errors.get("repassword").toString() + "</div>");
		}
		out.print("<br></br></td>");
		out.print("<td> <input type=\"password\" name=\"repassword\" ");
        if(request.getParameter("repassword") != null)
        {
            out.print(" value=" + request.getParameter("repassword") );

        }
        out.print("><br></br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Zipcode &nbsp;&nbsp; :");
		if (errors.containsKey("zipcode")) {
			out.print("<div class = \"error\" > Error: "
					+ errors.get("zipcode").toString() + "</div>");
		}
		out.print("<br> </br></td>");
		out.print("<td> <input type=\"text\" name=\"zipcode\" ");
        if(request.getParameter("zipcode") != null)
        {
            out.print(" value= " + request.getParameter("zipcode"));

        }
        out.print("><br> </br> </td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td><input type=\"submit\" value=\"submit\">");
		out.println("<br> </br> </td>");
		out.println("</tr>");
		out.println("");
		out.println("	</table>");
		out.println("	</fieldset>");
		out.println("	</form>");
		out.println("	</center>");
		out.println("	</body>");
		out.println("</html>");
		out.close();
		
	
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//boolean correctcontent;
		//correctcontent = true;
		Hashtable errors = new Hashtable();

		String firstname = request.getParameter("firstname");
		if (firstname.equals("")) {
			errors.put("firstName", "Please enter your first name");
			firstname = "";
			//correctcontent = false;
		}

		String lastname = request.getParameter("lastname");
		if (lastname.equals("")) {
			errors.put("lastName", "Please enter your last name");
			lastname = "";
			//correctcontent = false;
		}

		String emailid = request.getParameter("emailid");
		if (emailid.equals("") || (emailid.indexOf('@') == -1)) {
			errors.put("email", "Please enter a valid email address");
			emailid = "";
			//correctcontent = false;
		}

		
		String password = request.getParameter("password");
		boolean passinvalid=true;
		if (!password.equals(""))
		{
			Pattern p1 = Pattern.compile("(?=.*?[0-9])(?=.*?[#?!@$%^&*-])");
			//Pattern p2 = Pattern.compile("[A-Z]&&[a-z]");
	        Matcher m = p1.matcher(password);
	        if(m.find())
	        {
	            Matcher m1 = p1.matcher(password);
	            if(m1.find())
	            {
	               passinvalid=false;
	            }
		}
		}
	

		if(passinvalid)
		{
			errors.put("password", "Please enter a valid password");
			password = "";
			//correctcontent = false;
		}


		String reEnterPassword = request.getParameter("repassword");
		if (reEnterPassword.equals("")	|| (password.equals("") )) 
		{
			errors.put("repassword", "Please confirm your password");
			reEnterPassword = "";
			//correctcontent = false;
		}
		else if(!password.equals(reEnterPassword) || (!reEnterPassword.equals(password) ))
		{
			errors.put("repassword", "Your Passwords do not match");
		}

		String zipcode = request.getParameter("zipcode");

		if (zipcode.equals("")) {
			errors.put("zipcode", "Please enter a valid zip code");
			zipcode = "";
			//correctcontent = false;
		} 
		else if(zipcode.length() != 5) 
		{
			errors.put("zipcode", "Please enter a zip code of not more/less than 5 digits");
		}
		else{
			try {
				//int x = Integer.parseInt(zipcode);
			} catch (NumberFormatException e) {
				errors.put("zipcode", "Please enter a valid zip code");
				zipcode = "";
				//correctcontent = false;
			}
		}

		this.getServletContext().setAttribute("Errors", errors);
		this.getServletContext().setAttribute("submitted", true);
		if(errors.size()==0 && (boolean)this.getServletContext().getAttribute("submitted") == true)
			
			//response.sendRedirect("http://cs3.calstatela.edu:8080/cs320stu154/success.html");
			response.sendRedirect("../success.html");
				
		doGet(request, response);

	}
}
