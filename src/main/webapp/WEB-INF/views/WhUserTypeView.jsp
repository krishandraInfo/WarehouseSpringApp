<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
	<%@include file="MenuBar.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-info text-white">
				<h2>Welcome to WhareHouse User Type Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>WhUserTypeDetais!</h3>
							</div>
							<div class="panel-footer">

								<table border="1">
									<tr>
										<td>Wharehouse Use Id</td>
										<td>${whUserType.userId}</td>
									</tr>
									<tr>
										<td>User Code</td>
										<td>${whUserType.userCode}</td>
									</tr>
									<tr>
										<td>User Name</td>
										<td>${whUserType.userName}</td>
									</tr>
									<tr>
										<td>User For</td>
										<td>${whUserType.userFor}</td>
									</tr>
									<tr>
										<td>Email</td>
										<td>${whUserType.userEmail}</td>
									</tr>
									<tr>
										<td>Contact Number</td>
										<td>${whUserType.userNumber}</td>
									</tr>
									<tr>
										<td>Id Type</td>
										<td>${whUserType.userIdType}</td>
									</tr>
									<tr>
										<c:choose>
											<c:when test="${whUserType.userIdType != 'OTHER'}">
												<td>Id Type</td>
												<td>${whUserType.userIdType}</td>
											</c:when>
											<c:otherwise>
												<td>Id Type</td>
												<td>${whUserType.ifOther}</td>
											</c:otherwise>
										</c:choose>
									</tr>
									<tr>
										<td>Id Number</td>
										<td>${whUserType.userIdNumber}</td>
									</tr>
								</table>

								<div class="card-footer">
									<a href="view">Back</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>

</body>
</html>