package zipcodes;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZipCustomTag extends SimpleTagSupport {

	double lat = 34.0664856;
	double lon = -118.16672369999998;

/*	public double getLat() {
		return lat;
	}*/

	public void setLat(String lat) {
		try {
			this.lat = Double.parseDouble(lat);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			this.lat = 34.0664856;
		}

	}

/*	public double getLon() {
		return lon;
	}*/

	public void setLon(String lon) {
		try {
			this.lon = Double.parseDouble(lon);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			this.lon = -118.16672369999998;
		}
	}

	public void doTag() throws JspException, IOException

	{
		JspWriter out = getJspContext().getOut();
		// Construct the URL
		String username = "cs320stu154";
		String password = "JqHa!#eC";
		String host = "cs3.calstatela.edu";
		String port = "3306";
		String dbName = "cs320stu154";

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

			while (resultSet.next()) {

				double zip = resultSet.getDouble("Zip");
				double latitude = resultSet.getDouble("Latitude");
				double longitude = resultSet.getDouble("Longitude");
				String city = resultSet.getString("City");
				String state = resultSet.getString("State");
				double distance = resultSet.getDouble("distance");

				double d = Math.acos((Math.cos(latitude) * Math.cos(lat))
						+ (Math.sin(latitude) * Math.sin(lat))
						* (Math.cos(longitude - lon)));
				distance = (int) (d * 3959);
				
				out.println("Zipcode:" + zip);
				out.println("Latitude:" + latitude);
				out.println("Longitude:" + longitude);
				out.println("City:" + city);
				out.println("State:" + state);
				out.println("Distance is:" + distance);

			}

		} catch (SQLException sqle) {
			System.out.println("Exception 1 msg - " + sqle.getMessage());

		}

		catch (Exception e) {
			System.out.println("Exception 2 msg - " + e.getMessage());

		}

		out.println("</pre>	</body>");
		out.println("</html>");
	}
}
