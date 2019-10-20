<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>welcome to Documents Page</h2>
<form action="upload" method="post" enctype="multipart/form-data">
<pre>
Choose Document  : <input type="file" name="commonsMultipartFile"/>

<input type="submit" name="Upload">

</pre>
</form>
<table border="1">
	<tr>
		<th>ID</th>
		<th>NAME</th>
		<th>LINK</th>
	</tr>
	<c:forEach items="${documents}" var="ob">
		<tr>
			<td> <c:out value="${ob[0]}"/> </td>
			<td> <c:out value="${ob[1]}"/> </td>
			<td>
				<a href="download?fileId=${ob[0]}"><img src="../resources/images/download.png" height="20" width="40"></a>
			</td>
		</tr>
	</c:forEach>
</table>
<br/>

${message}
</body>
</html>