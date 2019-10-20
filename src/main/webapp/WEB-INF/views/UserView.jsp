<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<%@include file="MenuBar.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-info text-white">
				<h4>Welcome to User Details Page!!</h4>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>${user.userName}__Detais!</h3>
							</div>
							<div class="panel-footer">
								<table border="1">
									<tr>
										<td>USER ID</td>
										<td>${user.userId}</td>
									</tr>
									<tr>
										<td>NAME</td>
										<td>${user.userName}</td>
									</tr>
									<tr>
										<td>GENDER</td>
										<td>${user.gender}</td>
									</tr>
									<tr>
										<td>EMAIL</td>
										<td>${user.userEmail}</td>
									</tr>
									<tr>
										<td>MOBILE</td>
										<td>${user.userMobile}</td>
									</tr>
									<tr>
										<td>PASSWORD</td>
										<td>${user.userPassword}</td>
									</tr>
									<tr>
										<td>userRoles</td>
										<td>${user.userRoles}</td>
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