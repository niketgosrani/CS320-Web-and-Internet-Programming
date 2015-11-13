<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<sql:setDataSource
      driver="com.mysql.jdbc.Driver"
    url="jdbc:mysql://cs3.calstatela.edu/cs320stu156"
    user="cs320stu156"
    password="J1J.3#Rp"/>

<c:if test="${not empty param.restart}">
	<c:set var="index" scope="session" value="0"></c:set>
</c:if>

<c:if test="${index>0 and index <= 5 and not empty param.answer}">
<sql:update>
		INSERT INTO answers(question_id, answer) values(?, ?)
		 <sql:param value="${param.id}" />
		 <sql:param value="${param.answer}" />
		
 </sql:update>
</c:if>

<c:if test="${empty param.answer or not empty param.restart}">
	<sql:query var="questions" scope="session">
		SELECT * FROM triviaquiz ORDER BY RAND() limit 5
	</sql:query>
	<sql:update>
		DELETE FROM answers
  	</sql:update>
  	<c:set var="index" scope="session" value="0"></c:set>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<style>

#header{
background-color: black;
color:white;
text-align: center;
padding: 5px;
}
</style>

<body>
<div id="header">
<h1>TRIVIA QUIZ CHALLENGE</h1>
</div>
	<div class="container">
		<h1><strong><center>
		<br/>
		<c:choose>
		<c:when test="${index < 5 }">
			<c:set var="index" scope="session" value="${index }"></c:set>
			<c:set value="${questions.rowsByIndex[index]}" var="q"/>
				${q[1]}
			
			<form action="user.jsp" method="get" >
				<input type="hidden" value="${q[0]}" name="id">
				<input type="submit"  class="btn btn-primary" Primary name="answer" value="True" >
				<input type="submit"  class="btn btn-danger" Danger name="answer" value="False" >
			</form>
		
		</c:when>
		<c:otherwise>
			<sql:query var="score" scope="session">
				SELECT * FROM answers a join triviaquiz t on t.id=a.question_id where a.answer like t.answer
			</sql:query>
				Your Score is: ${score } out of 5
				<table class="table table-bordered">
	<thead>
	<tr>
	<th>SCORE</th>
	<th>REMARKS</th>
	</tr>
	</thead>
	<tbody>
	<tr>
	<td>0</td>	
	<td>TERRIBLE</td></tr>
	<td>1</td>
	<td>POOR</td></tr>
	<td>2</td>
	<td>BELOW AVERAGE</td></tr>
	<td>3</td>
	<td>AVERAGE</td></tr>
	<td>4</td>
	<td>ABOVE AVERAGE</td></tr>
	<td>5</td>
	<td>AWESOME</td></tr>
	</tr>
	
	</tbody>
	</table>
				<h4><a href="user.jsp?restart=yes">Restart Again!</a></h4>
		</c:otherwise>
		</c:choose>
		</center>   </strong></h1>
		
		
		<p><left><u>Question NO.${index}</u></left></p>
		
				</div>
				
		
<style>
#footer{
background-color: black;
color:white;
text-align: center;
padding: 5px;

}
</style>	
<div id="footer">
Copyright @ 2014 California State University,Los Angeles.All Rights Reserved.
</div>	

</body>
</html>


		