package zipcodes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

@WebServlet("/zipcodes/ImportZipcodes")
public class ImportZipcodes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Write HTML
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("		<title>CS320 Session Countdown</title>");
		out.println("		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\">");
		out.println("	</head>");
		out.println("	<body><pre>");

		// Construct the URL
		String username = "cs320stu154";
		String password = "JqHa!#eC";
		String host = "cs3.calstatela.edu";
		String port = "3306";
		String dbName = "cs320stu154";

		String lat = request.getParameter("latitude");
		if (lat == "" || lat == null) {
			request.setAttribute("", lat);
		} else
			request.setAttribute("latitude", lat);

		String lon = request.getParameter("longitude");
		if (lon == "" || lon == null) {
			request.setAttribute("", lon);
		} else
			request.setAttribute("longitude", lon);

		String radius = request.getParameter("radius");
		if (radius == "" || radius == null) {
			request.setAttribute("", radius);
		} else
			request.setAttribute("radius", radius);

		if (lat == "" || lat == null || lon == "" || lon == null
				|| radius == "" || radius == null) {
			request.setAttribute("error", "Invalid Entry");
			response.sendRedirect("../ZipcodeLocator/NearbyZipcodes.jsp");
		}

		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

		try {
			System.out.println("Class.forName");
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();

			Driver driver = new org.gjt.mm.mysql.Driver();

			System.out.println("Creating Connection");
			// Connect to the database
			Connection connection = (Connection) DriverManager.getConnection(
					url, username, password);

			System.out.println("Creating Statement");
			// Create Statement
			Statement statement = (Statement) connection.createStatement();

			// Create SQL query
			String query = "SELECT * FROM CA_Zipcodes";

			System.out.println("Executing Query");
			ResultSet resultSet = statement.executeQuery(query);

			System.out.println("Iterating Over Results");

			ArrayList<Zipcodes> zcode = new ArrayList<Zipcodes>();
			while (resultSet.next()) {

				// System.out.println("---" + resultSet.getDouble("Zip"));
				zcode.add(new Zipcodes(resultSet.getDouble("Zip"), resultSet
						.getDouble("Latitude"), resultSet
						.getDouble("Longitude"), resultSet.getString("City"),
						resultSet.getString("State"), resultSet
						.getDouble("distance")));
			}

			Double Lat = Double.parseDouble(request.getParameter("latitude"));
			// System.out.println(lat);

			Double Lon = Double.parseDouble(request.getParameter("longitude"));

			Double Radius = Double.parseDouble(request.getParameter("radius"));

			ArrayList<Zipcodes> imported = new ArrayList<Zipcodes>();

			for (Zipcodes code : zcode) {

				double d = Math.acos((Math.cos(Math.toRadians(Lat)) * Math.cos(Math.toRadians(code.getLat())))
						+ (Math.sin(Math.toRadians(Lat)) * Math.sin(Math.toRadians(code.getLat())))
						* (Math.cos(Math.toRadians(Lon - code.getLon()))));
				int distance = (int) (d * 3959);

				if (distance <= Radius) {
					System.out.println(code.getZip());
					imported.add(new Zipcodes(code.getZip(), code.getLat(),
							code.getLon(), code.getCity(), code.getState(),
							distance));
				}

			}


			int length = (imported.size());
			request.setAttribute("Length", length);
			request.setAttribute("Import", imported);
			request.setAttribute("latitude", lat);
			request.setAttribute("longitude", lon);
			request.setAttribute("radius", radius);
			request.getRequestDispatcher("/ZipcodeLocator/NearbyZipcodes.jsp")
			.forward(request, response);

			// Tidy Up
			connection.close();
			System.out.println("Connection Closed");

		} catch (SQLException sqle) {
			System.out.println("Exception 1 msg - " + sqle.getMessage());

		}

		catch (Exception e) {
			System.out.println("Exception 2 msg - " + e.getMessage());

		}

		out.println("</pre>	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
