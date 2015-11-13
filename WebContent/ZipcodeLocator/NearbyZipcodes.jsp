<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="zipcodes.Zipcodes"%>
<%@page import="java.util.ArrayList"%>


<!DOCTYPE html>
<html>
<head>
<title>Zipcode Locator</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
html, body, #map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px
}
</style>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=false">
    </script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"
	type="text/javascript"></script>
<style type="text/css">
body {
	background-image: url("img_tree.png");
	background-color: white;
}
</style>
<script type="text/javascript">
        var map = null;
            function showlocation() {
               // One-shot position request.
                navigator.geolocation.getCurrentPosition(callback);
            }
         
      function callback(position) {
         
        var lat = position.coords.latitude;
        var lon = position.coords.longitude;
         
             document.getElementById('latitude').innerHTML = lat;
         document.getElementById('longitude').innerHTML = lon;
             
        var latLong = new google.maps.LatLng(lat, lon);
         
                var marker = new google.maps.Marker({
                    position: latLong
                });      
                 
                marker.setMap(map);
        map.setZoom(15);
        map.setCenter(marker.getPosition());
      }
       
      google.maps.event.addDomListener(window, 'load', initMap);
      function initMap() {
        var mapOptions = {
          center: new google.maps.LatLng(0, 0),
          zoom: 1,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        
        map = new google.maps.Map(document.getElementById("map-canvas"), 
                                          mapOptions);
       
      }
    </script>
</head>

<body bgcolor="Yellowgreen">
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

		<form name="form1" action="../zipcodes/ImportZipcodes" method="post">
			<img src="honeybee.jpg"></img> <br>

			<table class="table table-striped table-hover table-bordered">
				<tr>
					<td>Enter Latitude Value<br>
					</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${not empty requestScope.latitude }">
								<input type="text" name="latitude"
									placeholder="Enter a Latitude"
									value="${requestScope.latitude }">
							</c:when>
							<c:otherwise>
								<input type="text" name="latitude"
									placeholder="Enter a Latitude" value="">
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>
				<tr>
					<td>Enter Longitude Value<br>
					</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${not empty requestScope.longitude }">
								<input type="text" name="longitude"
									placeholder="Enter a longitude"
									value="${requestScope.longitude }">
							</c:when>
							<c:otherwise>
								<input type="text" name="longitude"
									placeholder="Enter a longitude" value="">
							</c:otherwise>
						</c:choose> <br /></td>
				</tr>

				<tr>
					<td>Enter Search Radius (in miles)<br>
					</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${not empty requestScope.radius }">
								<input type="text" name="radius" placeholder="Enter a radius"
									value="${requestScope.radius }">
							</c:when>
							<c:otherwise>
								<input type="text" name="radius" placeholder="Enter a radius"
									value="">
							</c:otherwise>
						</c:choose> <br> <br></td>
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

			<c:choose>
				<c:when test="${not empty requestScope.radius || not empty requestScope.latitude || not empty requestScope.longitude }">
					<tr>
						<th>Zipcode</th>
						<th>City</th>
						<th>State</th>
						<th>Distance</th>
					</tr>
					<br>
					<br>
				</c:when>

			</c:choose>

			<c:forEach var="users" items="${requestScope.Import}"
				varStatus="count">

				<tr>
					<td>${users.zip}</td>
					<td>${users.city}</td>
					<td>${users.state}</td>
					<td>${users.distance}</td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<fieldset>
		<legend>
			<b><font face="Courier New" size="+2" color="blue">Location
					Viewer on Map</font></b>
		</legend>

		<input type="button" value="Show my location on Map"
			onclick="javascript:showlocation()" id="myButton" /> <br />

		Latitude: <span id="latitude"></span><br /> Longitude: <span
			id="longitude"></span><br />
		<br />
		<div id="map-canvas" style="width: 500px; height: 500px"></div>
		<br />
		<br />
	</fieldset>
</body>
</html>


