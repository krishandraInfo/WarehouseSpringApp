<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h2>Welcome to Purchase Order Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<c:choose>
					<c:when test="${empty purchase}">
						<h4>No Data Found</h4>
					</c:when>
					<c:otherwise>

						<div class="table-responsive">
							<table class="table table-hover table-bordered ">

								<tr class="thead-light">
									<th>ID</th>
									<th>CODE</th>
									<th class="text-info">STATUS*</th>
									<th>REF NUM</th>
									<th>QC</th>
									<th>VENDOR</th>
									<th>SHIPMENT CODE</th>
									<th colspan="8">OPERATIONS</th>
								</tr>
								<c:forEach items="${purchase}" var="po">
									<tr>
										<td><c:out value="${po.orderId}" /></td>
										<td><c:out value="${po.orderCode}" /></td>
										<td class="text-info"><c:out value="${po.orderStatus}" />
											&nbsp;</td>
										<td><c:out value="${po.refNumber}" /></td>
										<td><c:out value="${po.quaCheck}" /></td>
										<td><c:out value="${po.whUserType.userCode}" /></td>
										<td><c:out value="${po.shipmentType.shipmentCode}" /></td>
										<td><c:choose>
												<c:when test="${po.orderStatus =='ORDERED' }">
													<a
														href="updateOrderStatus?orderId=${po.orderId}&status=INVOICED"
														class="btn btn-success">INVOICE</a>
												</c:when>
												<c:when test="${po.orderStatus=='CANCELLED'}">
													<b class="text-danger">ORDERE CANCELLED</b>
												</c:when>
												<c:when test="${po.orderStatus=='INVOICED'}">
													<a href="viewInvoice?orderId=${po.orderId}">SHOW VENDOR
														INVOICE</a>
												</c:when>
												<c:when
													test="${po.orderStatus == ('OPEN' || 'PICKING')}">
													<b>TO BE CONFIRMED </b>
												</c:when>

											</c:choose></td>
										<td><c:if
												test="${po.orderStatus == 'OPEN' || po.orderStatus == 'PICKING' || po.orderStatus == 'ORDERED'}">
												<a
													href="updateOrderStatus?orderId=${po.orderId}&status=CANCELLED"
													class="btn btn-danger">CANCEL</a>
											</c:if></td>
										<td><a href="viewItems?orderId=${po.orderId}"> <img
												src="../resources/images/items2.png" height="50" width="50">
										</a></td>


										<td><a href="view?orderId=${po.orderId}"> <img
												src="../resources/images/view.png" height="40" width="40">
										</a></td>
										<td><a href="edit?orderId=${po.orderId}"> <img
												src="../resources/images/edit.png" height="40" width="40">
										</a></td>
										<td><a href="delete?orderId=${po.orderId}"> <img
												src="../resources/images/delete.png" height="40" width="40">
										</a></td>
										<td><a href="excelExport?orderId=${po.orderId }"> <img
												src="../resources/images/excel.png" height="40" width="40" />
										</a></td>
										<td><a href="pdfExport?orderId=${po.orderId}"> <img
												src="../resources/images/pdf.png" height="40" width="40" />
										</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>



						<br>
						<h6>
							<a href="excelExport"><img
								src="../resources/images/BulkExcel.png" height="80" width="180" /></a>
							<a href="pdfExport"><img
								src="../resources/images/BulkPdf.png" height="80" width="180" /></a>
							<a href="report"><img src="../resources/images/charts.png"
								height="80" width="180" /></a>
						</h6>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="${message !=null}">
				<div class="card-footer">${message}</div>
			</c:if>
		</div>
	</div>
	<br>
</body>
</html>