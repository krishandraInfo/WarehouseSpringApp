
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
				<h2>Welcome to Item Register page</h2>
			</div>
			<div class="card-body">
				<form:form action="insert" method="post" modelAttribute="item">

					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label for="itemCode">Item Code </label>
						</div>
						<div class="col-6">
							<form:input path="itemCode" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="itemCode" cssClass="text-danger" />
						</div>
					</div>


					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label>Dimensions </label>
						</div>
						<div class="col-1">
							<label for="itemLength"> Length </label>
						</div>
						<div class="col-1">
							<form:input path="itemLength" cssClass="form-control" />
						</div>
						<div class="col-1">
							<label for="itemWidth"> Width </label>
						</div>
						<div class="col-1">
							<form:input path="itemWidth" cssClass="form-control" />
						</div>
						<div class="col-1">
							<label for="itemHeight"> Height </label>
						</div>
						<div class="col-1">
							<form:input path="itemHeight" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-1">
							<form:errors path="itemLength" cssClass="text-danger" />
						</div>
						<div class="col-1"></div>
						<div class="col-1">
							<form:errors path="itemWidth" cssClass="text-danger" />
						</div>
						<div class="col-1"></div>
						<div class="col-1">
							<form:errors path="itemHeight" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label for="itemBaseCost"> Price </label>
						</div>
						<div class="col-6">
							<form:input path="itemBaseCost" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="itemBaseCost" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label for="itemCurrentCurrency"> Currency </label>
						</div>
						<div class="col-6">
							<form:select path="itemCurrentCurrency" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="IND">IND</form:option>
								<form:option value="USD">USD</form:option>
								<form:option value="EUR">EUR</form:option>
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="itemCurrentCurrency" cssClass="text-danger" />
						</div>
					</div>

					<!-- uom component integration -->
					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label for=uom.uomId> Uom </label>
						</div>
						<div class="col-6">
							<form:select path="uom.uomId" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${uom}" />
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="uom" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">

						<div class="col-2"></div>
						<div class="col-2">
							<label for="">Order Method Code</label>
						</div>
						<div class="col-6">
							<form:select path="orderMethod.orderId" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${orderMethod}" />
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="orderMethod" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-2"></div>
						<div class="col-2">
							<label for="itemDesc"> Description </label>
						</div>
						<div class="col-6">
							<form:textarea path="itemDesc" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-4"></div>
						<div class="col-6">
							<form:errors path="itemDesc" cssClass="text-danger" />
						</div>
					</div>



					<br>
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