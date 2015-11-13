<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<title>Quiz Page</title>
<style>
#footer {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}
</style>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>

<script>
   

    var questionID = 1;
    
    $(document).ready(function(){
        
        $(".submitAnswer").click(function(){
            var answer = $(this).val();
            var qid = $(this).attr("data-qid");
            console.log("Question ID is "+ qid + " and answer is " + answer);
            
            $("#QuestionContainer"+questionID).hide();
            
            $.post("SaveResponses",{"count":questionID, "qid":qid, "answer":answer}, function(){
                questionID++;
                $("#QuestionContainer"+questionID).show();
            })
            
            if(questionID==5) {
                top.location.href="ResultPage.jsp";
            }
            
        });
        
    })
    
    
    
   
    </script>
</head>
<body>
	<div id="header">
		<form action="" method="post">
			<img src="prometric_test_center.png" height="150" width="1340" /><br>
			<br>
			<div class="container">

				<sql:setDataSource driver="com.mysql.jdbc.Driver"
					url="jdbc:mysql://cs3.calstatela.edu:3306/cs320stu154"
					user="cs320stu154" password="JqHa!#eC" />
				<sql:query var="questions">
                    select * from questionPg ORDER BY RAND() LIMIT 5;
       
                </sql:query>

				<c:remove var="score" scope="session" />

				<c:forEach var="quest" items="${questions.rowsByIndex}"
					varStatus="index">



					<c:choose>
						<c:when test="${index.count==1}">

							<div class="QuestionDiv" id="QuestionContainer${index.count}">

								<div id="QuestionDiv${index.count}">
									<font color="Blue" size="14"> Question ${index.count} :
										${quest[1]} </font>
								</div>

								<br> <br>
								<div align="center">
									<br> <input type="button"
										class="btn btn-primary submitAnswer" Primary name="answer"
										value="TRUE" id="OPT1" data-qid="${quest[0]}" /> <input
										type="button" class="btn btn-danger submitAnswer"
										data-qid="${quest[0]}" Danger name="answer" value="FALSE"
										id="OPT2" />
								</div>

							</div>


						</c:when>

						<c:otherwise>

							<div class="QuestionDiv" id="QuestionContainer${index.count}"
								style="display: none;">

								<div id="QuestionDiv${index.count}">
									<font color="Blue" size="14"> Question ${index.count} :
										${quest[1]} </font>
								</div>

								<br> <br>
								<div align="center">
									<br> <input type="button"
										class="btn btn-primary submitAnswer" Primary name="answer"
										value="TRUE" id="OPT1" data-qid="${quest[0]}" /> <input
										type="button" class="btn btn-danger submitAnswer" Danger
										name="answer" value="FALSE" id="OPT2" data-qid="${quest[0]}" />
								</div>

							</div>

						</c:otherwise>

					</c:choose>


				</c:forEach>
				<br> <br>
			</div>
		</form>
	</div>

	<div id="footer">Copyright @ 2014 California State University,Los
		Angeles.All Rights Reserved.</div>
</body>
</html>