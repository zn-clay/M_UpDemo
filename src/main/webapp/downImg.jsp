<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="jquery-1.42.min.js"></script> 
<title>Insert title here</title>

</head>
<body>
<h1>好</h1>
<div id="d">
	
</div>
<c:forEach var="image" items="${imgNameList}">   
            <a href="images/${image}" target="_blank"><img  src="images/${image}" /></a>
      <a href="${pageContext.request.contextPath}/?filename=${image}">${image}</a>      
  </c:forEach> 
</body>
</html>