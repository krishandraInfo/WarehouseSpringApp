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
				<h2>Welcome to Shipping Edit page!!</h2>
			</div>
			<div class="card-body">
				<form:form cssClass="form" action="update" method="post"
					modelAttribute="shipping">
					
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipId"> Shipping Code </label>
						</div>
						<div class="col-4">
							<form:input path="shipId" readonly="true" cssClass="form-control" />
						</div>
					</div>
					
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipCode"> Shipping Code </label>
						</div>
						<div class="col-4">
							<form:input path="shipCode" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="shipCode" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipRefNum"> Shipping Reference Num</label>
						</div>
						<div class="col-4">
							<form:input path="shipRefNum" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="shipRefNum" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for=saleOrder.saleOrderId> Purchase Order Code </label>
						</div>
						<div class="col-4">
							<form:select path="saleOrder.saleOrderId" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:options items="${saleOrder}" />
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="col-5"></div>
						<div class="col-6">
							<form:errors path="saleOrder" cssClass="text-danger" />
						</div>
					</div>

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="courRefNum"> Courier Reference Num</label>
						</div>
						<div class="col-4">
							<form:input path="courRefNum" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="courRefNum" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="couContdtls"> Courier Contact Num</label>
						</div>
						<div class="col-4">
							<form:input path="couContdtls" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="couContdtls" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="billAddr"> Billing Address</label>
						</div>
						<div class="col-4">
							<form:textarea path="billAddr" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="billAddr" cssClass="text-danger" />
						</div>
					</div>
					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="shipAddr"> Shipping Address</label>
						</div>
						<div class="col-4">
							<form:textarea path="shipAddr" cssClass="form-control" />
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4">
							<form:errors path="shipAddr" cssClass="text-danger" />
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
						<div class="col-4">
							<form:errors path="shipDesc" cssClass="text-danger" />
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