<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
    <html>
    <head>
     <script type="text/javascript">
      function getLocation(){
      if (navigator.geolocation){
          navigator.geolocation.getCurrentPosition(showPosition);
        }
      }
      function displayLocation(latitude,longitude){
        var request = new XMLHttpRequest();

    var method = 'GET';
    var url = 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+latitude+','+longitude+'&sensor=true';
    var async = true;

    request.open(method, url, async);
    request.onreadystatechange = function(){
      if(request.readyState == 4 && request.status == 200){
        var data = JSON.parse(request.responseText);
        var address = data.results[0];
        //document.write(address.formatted_address);
        var city=document.getElementById("city");
        var n = address.formatted_address.split(",");
        city.value = n[n.length-3];
      }
    };
    request.send();
  };

function showPosition(position){
//var x = document.getElementById("demo");
var latitude=document.getElementById("latitude"),longitude=document.getElementById("longitude");
//x.innerHTML="+" + position.coords.latitude + "+" + position.coords.longitude;
latitude.value = position.coords.latitude;
longitude.value = position.coords.longitude;
displayLocation(latitude.value,longitude.value);
}

</script>

</head>
<body onload="getLocation()">
<%! String city; String latitude; String longitude; %>
<%
String query=request.getParameter("myQuery");
String city=request.getParameter("city");
String latitude=request.getParameter("latitude");
String longitude=request.getParameter("longitude");
%>
<form name="frm" method="post" action="test.jsp">
<input type="text" name="myQuery" placeholder="Type here">
<input name="latitude" id="latitude" type="hidden">
<input name="longitude" id="longitude" type="hidden">
<input name="city" id="city" type="hidden">
<input type="submit" name="submit" value="Submit">
</form>
<p>Query phrase is : <%=query%></p>
<p>User's city : <%=city%></p>
</body>
</html>