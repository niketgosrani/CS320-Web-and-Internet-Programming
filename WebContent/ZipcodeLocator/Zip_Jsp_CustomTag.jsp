<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="/WEB-INF/nearbyZipcodesTag.tld" prefix="CS320" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Zipcode Locator using Custom Tags</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
</head>
<body>
    <font color="red"> <marquee behavior="alternate">
            <h1>
                Welcome to Friend Locator !!!<img src="honey-bee-coloring-page.jpg"
                    height="50" width="50" alt=""></img><sub> <small><font
                        color="red">(A quick way to locate your friends nearby and
                            meet them for a quick Coffee)</font></small></sub>
            </h1>
        </marquee>
    </font>
    <fieldset>
        <legend>
            <b><font face="Courier New" size="+2" color="blue">Friend's
                    Locator</font></b>
        </legend>

        <form name="form1" method="get">
            <img src="honeybee.jpg"></img> <br>

            <table class="table table-striped table-hover table-bordered">
                <tr>
                    <td>Enter Latitude Value<br>
                    </td>
                </tr>
                <tr>
                    <td>
                                <input type="text" name="latitude"
                                    placeholder="Enter a Latitude"
                                    >
                           
                           <br /></td>
                </tr>
                <tr>
                    <td>Enter Longitude Value<br>
                    </td>
                </tr>
                <tr>
                    <td>
                                <input type="text" name="longitude"
                                    placeholder="Enter a longitude">
                            
                   <br /></td>
                </tr>

                <tr>
                    <td>Enter Search Radius (in miles)<br>
                    </td>
                </tr>
                <tr>
                    <td>
                                <input type="text" name="radius" placeholder="Enter a radius">
                            
                           <br> <br></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Find Nearby" /><br></td>
                </tr>
            </table>
        </form>
    
    </fieldset>
        <fieldset>
        <legend>
            <b><font face="Courier New" size="+2" color="blue">Search
                    Results</font></b>
        </legend>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <td><h2>Searched ${requestScope.Import.size() } Results
                        from nearby zipcodes</h2> <br></td>
            </tr>

         
                    <tr>
                        <th>Zipcode</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Distance</th>
                    </tr>

        </table>
    </fieldset>
     <CS320:nearbyZipcodes lat="${param.latitude}" lon="${param.longitude}"/>
</body>
</html>
