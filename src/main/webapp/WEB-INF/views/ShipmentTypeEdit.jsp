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
				<h2>Welcome to Shipment Type Edit page!!</h2>
			</div>
			<div class="card-body">
				<form:form cssClass="form " action="update" method="post"
					modelAttribute="shipmentType">

					<div class="form-group"></div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipmentid">Shipment Id :</label>
						</div>
						<div class="col-4">
							<form:input path="shipmentid" readonly="true"
								cssClass="form-control" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipmentMode">Shipment Mode </label>
						</div>
						<div class="col-4">
							<form:select path="shipmentMode" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="AIR">Air</form:option>
								<form:option value="TRUCK">Truck</form:option>
								<form:option value="TRAIN">Train</form:option>
								<form:option value="SHIP"></form:option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="shipmentMode" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipmentCode">Shipment Code </label>
						</div>
						<div class="col-4">
							<form:input path="shipmentCode" readonly="true" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="shipmentCode" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="enableShipment">Enable Shipment </label>
						</div>
						<div class="col-4">
							<form:select path="enableShipment" cssClass="form-control">
								<form:option value="">-select-</form:option>
								<form:option value="YES">Yes</form:option>
								<form:option value="NO">No</form:option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="enableShipment" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipmentGrade">Shipment Grade </label>
						</div>
						<div class="col-4">
							<form:radiobutton path="shipmentGrade" value="A" />
							A
							<form:radiobutton path="shipmentGrade" value="B" />
							B
							<form:radiobutton path="shipmentGrade" value="C" />
							C
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="shipmentGrade" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipDesc">Description </label>
						</div>
						<div class="col-4">
							<form:textarea path="shipDesc" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="shipDesc" cssClass="text-danger" />
						</div>
					</div>
					<br />
					<br />
					<div class="row">
						<div class="col-5"></div>
						<div class="col-2">
							<input type="submit" value="Update" class="btn btn-primary">
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