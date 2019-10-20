<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="HomeMenu.jsp"%>
	<div class="container">

		<!-- card start -->
		<div class="card">


			<!-- card Header -->
			<div class="card-header bg-info text-white ">
				<h2>WELCOME TO USER LOGIN PAGE</h2>
			</div>


			<!-- card body -->
			<div class="card-body">

				<form action='<spring:url value="/signin"/>' method="POST">
					<div class="form-group">
						<label for="username" class="control-label col-sm-4">USER
							ID</label> <input type="text" id="username" name="username" required="required"/>
					</div>
					<div class="form-group">
						<label for="password" class="control-label col-sm-4">PASSWORD</label>
						<input type="password" id="password" name="password" required="required"/>
					</div>
					<div class="form-group">
						<input type="submit" value="Login" class="btn btn-success" />
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>


			<c:if test="${param.error}">
				<div class="card-footer bg-danger text-white">Invalid Username
					and password.</div>
			</c:if>
			<c:if test="${param.logout}">
				<div class="card-footer bg-success text-white">You have been
					logged out.</div>
			</c:if>
		</div>
	</div>
</body>
</html>