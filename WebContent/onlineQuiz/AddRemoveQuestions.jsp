<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<style type="text/css">
html, body, #map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px;
}
</style>
<style>
#footer {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}
</style>
</head>
<body>

	<sql:setDataSource driver="com.mysql.jdbc.Driver"
		url="jdbc:mysql://cs3.calstatela.edu:3306/cs320stu154"
		user="cs320stu154" password="JqHa!#eC" />


	<c:if test="${not empty param.add }">

		<sql:update>
	    INSERT INTO questionPg (Question,  answer) values (?,?)
	    <sql:param value="${param.Question}" />
			<sql:param value="${param.answer}" />
		</sql:update>
	</c:if>

	<c:if test="${not empty param.del_id }">

		<sql:update>
        DELETE FROM questionPg WHERE ID = ?
        <sql:param value="${param.del_id}" />
		</sql:update>
		<c:redirect url="AddRemoveQuestions.jsp" />
	</c:if>

	<sql:query var="list_quest">
       SELECT * from questionPg;
    </sql:query>

	<img src="prometric_test_center.png" height="150" width="1340" />
	<div align="center">
		<h1>Welcome to Admin's Page !!!</h1>
	</div>
	<fieldset>
		<legend>
			<b><font face="Courier New" size="+2" color="blue">You can
					View All Question's Here</font></b>
		</legend>
		<form name="form" action="" method="post">

			<table class="table table-striped table-hover table-bordered">

				<tr>
					<th>Sr No.</th>
					<th>Question</th>
					<th>Answer</th>
					<th>Operations</th>
				</tr>

				<c:forEach var="quest" items="${list_quest.rowsByIndex}">
					<tr>
						<th>${quest[0]}</th>
						<th>${quest[1]}</th>
						<th>${quest[2]}</th>
						<th><a href="AddRemoveQuestions.jsp?del_id=${quest[0]}">Delete</a></th>
					</tr>
				</c:forEach>

			</table>
		</form>
	</fieldset>
	<div align="center">
		<fieldset>
			<legend>
				<b><font face="Courier New" size="+2" color="blue">You
						can ADD Question's Here</font></b>
			</legend>
			<form name="form" action="" method="post">
				<table class="table table-striped table-hover table-bordered">
					<tr>
						<td align="center">Enter Question Here <input type="text"
							name="Question" /></td>
					</tr>
					<tr>
						<td align="center">Select Correct Answer <select
							name="answer">
								<option value="TRUE">True</option>
								<option value="FALSE">False</option>
						</select><br /> <br>
						</td>
					</tr>
					<tr>
						<td align="center"><input type="submit" name="add"
							value="Add" /></td>
					</tr>

				</table>

			</form>
		</fieldset>
	</div>
	<div id="footer">Copyright @ 2014 California State University,Los
		Angeles.All Rights Reserved.</div>
</body>
</html>