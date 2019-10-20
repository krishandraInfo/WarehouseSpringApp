
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h2>Welcome to Order Method Data page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<c:choose>
					<c:when test="${empty orderMethod}">
						<h4>No Data Found</h4>
					</c:when>
					<c:otherwise>
						<table class="table table-hover table-bordered">
							<tr class="thead-light">
								<th>Order Id</th>
								<th>Mode</th>
								<th>Accept</th>
								<th>Description</th>
								<th colspan="5">Operations</th>
							</tr>

							<c:forEach items="${orderMethod}" var="om">
								<tr>
									<td><c:out value="${om.orderId}"></c:out></td>
									<td><c:out value="${om.orderMode}"></c:out></td>
									<td><c:out value="${om.orderAccept}"></c:out></td>
									<td><c:out value="${om.orderDecs}"></c:out></td>
									<td><a href="view?orderId=${om.orderId}"> <img
											src="../resources/images/view.png" height="40" width="40">
									</a></td>
									<td><a href="editOne?orderId=${om.orderId}"> <img
											src="../resources/images/edit.png" height="40" width="40">
									</a></td>
									<td><a href="delete?orderId=${om.orderId}"> <img
											src="../resources/images/delete.png" height="40" width="40">
									</a></td>
									<td><a href="excelExport?orderId=${om.orderId}"><img
											src="../resources/images/excel.png" height="40" width="40">
									</a></td>
									<td><a href="pdfExport?orderId=${om.orderId }"> <img
											src="../resources/images/pdf.png" height="40" width="40" />
									</a></td>
								</tr>
							</c:forEach>
						</table>
						<br />
						<h6>
							<a href="excelExport"><img src="../resources/images/BulkExcel.png" height="80" width="180" /></a> 
							<a href="pdfExport"><img src="../resources/images/BulkPdf.png" height="80" width="180" /></a>
							<a href="report"><img src="../resources/images/charts.png" height="80" width="180" /></a>
						</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<br>

			<c:if test="${message !=null}">
				<div class="card-footer">${message}</div>
			</c:if>
		</div>
	</div>
	<br>
</body>
</html>