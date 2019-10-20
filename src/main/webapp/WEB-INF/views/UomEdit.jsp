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
				<h2>Welcome to Uom Edit page!!</h2>
			</div>
			<div class="card-body">
				<form:form cssClass="form" action="update" method="post"
					modelAttribute="uom">

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="uomId"> Uom Id :</label>
						</div>
						<div class="col-4">
							<form:input path="uomId" readonly="true" cssClass="form-control"/>
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="uomType"> Uom Type </label>
						</div>
						<div class="col-4">
							<form:select path="uomType" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="PACKING">PACKING</form:option>
								<form:option value="NON-PACKING">NON-PACKING</form:option>
								<form:option value="--NA--">--NA--</form:option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="uomType" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="uomModel">Uom Model </label>
						</div>
						<div class="col-4">
							<form:input path="uomModel" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="uomModel" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="uomDesc">Description </label>
						</div>
						<div class="col-4">
							<form:textarea path="uomDesc" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="uomDesc" cssClass="text-danger" />
						</div>
					</div>

					<br>
					<br>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-2">
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