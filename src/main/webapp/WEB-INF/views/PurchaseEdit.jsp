
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
				<h2>Welcome to Purchase Order Edit page</h2>
			</div>
			<div class="card-body">
				<form:form action="update" method="post" modelAttribute="purchase">

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="orderId">Order Id </label>
						</div>
						<div class="col-3">
							<form:input path="orderId" readonly="true"
								cssClass="form-control" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="orderCode">Order Code </label>
						</div>
						<div class="col-3">
							<form:input path="orderCode" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="orderCode" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="whUserType.whuser_id"> Vendor</label>
						</div>
						<div class="col-3">
							<form:select path="whUserType.userId" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${whUserType}" />
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="whUserType" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipmentType.shipmentid">Shipment Code</label>
						</div>
						<div class="col-3">
							<form:select path="shipmentType.shipmentid"
								cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${shipmentType}" />
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="shipmentType" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="refNumber"> Reference Number </label>
						</div>
						<div class="col-3">
							<form:input path="refNumber" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="refNumber" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="quaCheck">Quality Check </label>
						</div>
						<div class="col-3">
							<form:radiobutton path="quaCheck" value="REQUIRED" />
							Required
							<form:radiobutton path="quaCheck" value="NOT REQUIRED" />
							Not Required
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="quaCheck" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="orderStatus"> Order Status </label>
						</div>
						<div class="col-3">
							<form:select path="orderStatus" cssClass="form-control">
								<form:option value="OPEN">OPEN</form:option>
								<form:option value="CLOSE">CLOSE</form:option>
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="orderDesc"> Description </label>
						</div>
						<div class="col-3">
							<form:textarea path="orderDesc" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="orderDesc" cssClass="text-danger" />
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