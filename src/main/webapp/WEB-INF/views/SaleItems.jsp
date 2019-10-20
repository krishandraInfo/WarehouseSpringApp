<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="MenuBar.jsp"%>
	<div class="container">

		<div class="card">

			<div class="card-header bg-info text-white">
				<h3>Welcome to SaleOrder Data Page</h3>
			</div>

			<div class="card-body">
				Order Code :<input type="text" value="${saleOrderCode}"
					class="form-control bg-light " readonly="readonly" /><br /> Order
				Status :<input type="text" value="${orderStatus}"
					class="form-control bg-light" readonly="readonly" />
			</div>
			<br />
			<c:if test="${orderStatus=='OPEN' || orderStatus=='READY'}">
				<form:form action="addItem" method="post"
					modelAttribute="salesDetails" cssClass="form-inline ">
					<form:hidden path="soHoId" />
					Choose Item :
					<form:select path="item.itemId" class="form-control">
						<form:option value="">--select--</form:option>
						<form:options items="${item}" />
					</form:select>
					Quantity :
					<form:input path="quantity" class="form-control" />
					<input type="submit" value="ADD ITEM" class="btn btn-success" />

				</form:form>
			</c:if>

			<div class="card-footer">
			<c:if test="${!empty salesDetailsList}">
				<table class="table table table-hover table-bordered">
					<tr>
						<th>SL NO</th>
						<th>ITEM CODE</th>
						<th>BASE COST</th>
						<th>QTY</th>
						<th>LINE TOTAL</th>
						<c:if test="${orderStatus == 'OPEN' || orderStatus == 'READY'}">
							<th>OPERATIONS</th>
						</c:if>
					</tr>
					<c:forEach items="${salesDetailsList}" var="sdl">
						<tr>
							<td>${sdl.slno}</td>
							<td>${sdl.item.itemCode}</td>
							<td>${sdl.item.itemBaseCost}</td>
							<td>${sdl.quantity}</td>
							<td>${sdl.item.itemBaseCost * sdl.quantity}</td>
							<c:if test="${orderStatus=='READY'}">
								<td><a
									href="removeItem?salesDtlsId=${sdl.salesDtlsId}&saleOrderId=${sdl.soHoId}"
									class="btn btn-danger">DELETE</a></td>
							</c:if>
						</tr>
					</c:forEach>
					<c:if test="${orderStatus=='READY'}">
						<tr>
							<td colspan="6" align="center"><a
								href="updateOrderStatus?saleOrderId=${saleOrderId}&orderStatus=CONFIRM"
								class="btn btn-success">CONFIRM OREDER</a></td>
						</tr>
					</c:if>
				</table>


			</c:if>
			</div>

		</div>

	</div>
</body>
</html>