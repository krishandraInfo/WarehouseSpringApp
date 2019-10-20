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
				<h2>Welcome to OrderMethod Register Page!!</h2>
			</div>
			<div class="card-body">
				<form:form action="insert" method="post"
					modelAttribute="orderMethod">

					<div class="row">
						<div class="col-3"></div>
						<div class="col-2">
							<label for="orderMode">Order Mode </label>
						</div>
						<div class="col-5">
							<form:radiobutton path="orderMode" value="SALE" />
							Sale
							<form:radiobutton path="orderMode" value="PURCHASE" />
							Purchare
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4 ">
							<form:errors path="orderMode" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row">
					<div class="col-3"></div>
						<div class="col-2">
							<label for="orderCode"> Order Code </label>
						</div>
						<div class="col-5">
							<form:input path="orderCode" cssClass="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4 ">
							<form:errors path="orderCode" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row">
					<div class="col-3"></div>
						<div class="col-2">
							<label for="orderExeType"> Execution Type </label>
						</div>
						<div class="col-5">
							<form:select path="orderExeType" cssClass="form-control">
								<form:option value="">--select--</form:option>
								<form:option value="FIFO">FIFO</form:option>
								<form:option value="LIFO">LIFO</form:option>
								<form:option value="FCFO">FCFO</form:option>
								<form:option value="FEFO">FEFO</form:option>
							</form:select>
						</div>
					</div>
					<div class="row">
						<div class="col-5"></div>
						<div class="col-4 ">
							<form:errors path="orderExeType" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<br>
					<div class="row">
					<div class="col-3"></div>
						<div class="col-2">
							<label for="orderAccept"> Order Accept </label>
						</div>
						<div class="col-5">
							<form:checkbox path="orderAccept" value="MULTI MODEL"/>
							Multi Model
							<form:checkbox path="orderAccept" value="ACCEPT RETURN"/>
							Accept Return
							<form:checkbox path="orderAccept" value="SERVICE EXTEND"/>
							Service Extend
						</div>
					</div>
					<div class="row">
						<div class="col-5 "></div>
						<div class="col-4 ">
							<form:errors path="orderAccept" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<div class="row">
					<div class="col-3"></div>
						<div class="col-2">
							<label for="orderDecs"> Description </label>
						</div>
						<div class="col-5">
							<form:textarea path="orderDecs" cssClass="form-control"/>
						</div>
					</div>
					<div class="row">
						<div class="col-5 "></div>
						<div class="col-4 ">
							<form:errors path="orderDecs" cssClass="text-danger"></form:errors>
						</div>
					</div>
					<br>
					<div class="row">
					<div class="col-3"></div>
					<div class="col-2"></div>
						<div class="col-5">
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