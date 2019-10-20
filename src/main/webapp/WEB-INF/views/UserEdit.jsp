<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
				<h2>Wlecome To User Edit Page</h2>
			</div>
			<div class="card-body">

				<form:form action="update" method="post" modelAttribute="user">

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userId">User Id :</label>
						</div>
						<div class="col-3">
							<form:input path="userId" readonly="true" cssClass="form-control" />
						</div>
					</div>


					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userName">NAME :</label>
						</div>
						<div class="col-3">
							<form:input path="userName" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="gender">GENDER :</label>
						</div>
						<div class="col-3">
							<form:radiobutton path="gender" value="MALE" />
							Male
							<form:radiobutton path="gender" value="FEMALE" />
							Female
							<form:radiobutton path="gender" value="OTHER" />
							Other
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userEmail">EMAIL:</label>
						</div>
						<div class="col-3">
							<form:input path="userEmail" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userMobile">MOBILE NUMBER :</label>
						</div>
						<div class="col-3">
							<form:input path="userMobile" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userPassword">PASSWORD :</label>
						</div>
						<div class="col-3">
							<form:password path="userPassword" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userRoles">ROLES :</label>
						</div>
						<div class="col-3">
							<form:checkbox path="userRoles" value="ADMIN" />
							Admin
							<form:checkbox path="userRoles" value="APP USER" />
							App User
						</div>
					</div>
					<!-- <div class="form-group">
						<input type="submit" value="Crate New User" />
					</div> -->
					<br>
					<br>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-3">
							<input type="submit" value="Update" class="btn btn-primary" />
						</div>
					</div>
				</form:form>
			</div>
			<h5>
				<a href="view" class="btn btn-info">View Data Here</a>
			</h5>
			<c:if test="${message !=null}">
				<div class="card-footer">${message}</div>
			</c:if>
		</div>
	</div>
	<br>
</body>
</html>