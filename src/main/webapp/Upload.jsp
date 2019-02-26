<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>文件上传</h1>
	<form method="post" action="upload.do" enctype="multipart/form-data"><!-- 声明序列化 -->
		<table>
			<tr>
				<td>文件描述</td>
				<td><input type="text" name="description"></td>				
			</tr>
			<tr>
				<td>选择文件</td>
				<td><input type="file" name="file"></td>				
			</tr>
			<tr>
				<td><input type="submit" value="上传"></td>				
			</tr>
		</table>
	</form>
	
	<h1>下载</h1>
	<form action="${pageContext.request.contextPath }/ToDownImg.do"
        method="get">
        <input type="submit" value="下载">
    </form>
   
</body> 
</html>