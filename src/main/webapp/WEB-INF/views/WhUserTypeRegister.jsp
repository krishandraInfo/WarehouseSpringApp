
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
				<h2>Welcome to WhareHouse User Register page</h2>
			</div>
			<div class="card-body">
				<form:form action="insert" method="post" modelAttribute="whUserType">

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userType">WhUser Type </label>
						</div>
						<div class="col-4">
							<form:radiobutton path="userType" value="CUSTOMER" />
							Customer
							<form:radiobutton path="userType" value="VENDOR" />
							Vendor
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userType" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userCode">WhUser Code </label>
						</div>
						<div class="col-4">
							<form:input path="userCode" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userCode" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userName">WhUser Name </label>
						</div>
						<div class="col-4">
							<form:input path="userName" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userName" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userFor"> User For </label>
						</div>
						<div class="col-4">
							<form:select path="userFor" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="SALES">Sales</form:option>
								<form:option value="PURCHASE">Purchse</form:option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userFor" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userEmail"> User Email </label>
						</div>
						<div class="col-4">
							<form:input path="userEmail" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userEmail" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userNumber"> User Contact Number </label>
						</div>
						<div class="col-4">
							<form:input path="userNumber" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userNumber" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userIdType"> User Id Type </label>
						</div>
						<div class="col-4">
							<form:select path="userIdType" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="PANCARD">Pancard</form:option>
								<form:option value="AADHAAR CARD">Aadhaar Card</form:option>
								<form:option value="VOTER CARD">Voter Card</form:option>
								<form:option value="OTHER">Other</form:option>
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userIdType" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="ifOther"> If Other Doument </label>
						</div>
						<div class="col-4">
							<form:input path="ifOther" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="ifOther" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="userIdNumber"> Id Number </label>
						</div>
						<div class="col-4">
							<form:input path="userIdNumber" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="userIdNumber" cssClass="text-danger" />
						</div>
					</div>
					<br>
					<br>
					<div class="row">
						<div class="col-5"></div>
						<input type="submit" value="Submit" class="btn btn-primary">
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