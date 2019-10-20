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
				<h2>Welcome to GRN Register page!!</h2>
			</div>
			<div class="card-body">
				<form:form cssClass="form" action="insert" method="post"
					modelAttribute="goodRecieveNote">
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="grnCode"> GRN Code </label>
						</div>
						<div class="col-4">
							<form:input path="grnCode" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="grnCode" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="grnType">GRN Type </label>
						</div>
						<div class="col-4">
							<form:input path="grnType" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="grnType" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for=purchase.orderId> Purchase Order Code </label>
						</div>
						<div class="col-4">
							<form:select path="purchase.orderId" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${purchase}" />
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="purchase" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="grnDesc">Description </label>
						</div>
						<div class="col-4">
							<form:textarea path="grnDesc" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="grnDesc" cssClass="text-danger" />
						</div>
					</div>

					<br>
					<br>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-2">
							<input type="submit" value="Submit" class="btn btn-primary" />
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