<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="MenuBar.jsp"%>
	<div class="container">

		<div class="card">
			<div class="card-header bg-info text-white">
				<h2>Welcome Shipping Data Page!!</h2>
			</div>


			<div class="card-body">
				<div class="row">
					<div class="col=3">
						<label>Shipping Code : </label>
					</div>
					<div class="col=2">
						<input type="text" value="${shipCode}"
							class="form-control bg-light" readonly="readonly">
					</div>
					<div class="col=3">
						<c:if test="${nullCount != 0}">
							<a
								href="updateOrderStatus?shipId=${shipId}&saleOrderId=${saleOrderId}&shipSatus=ACCEPTED"
								class="btn btn-success">Accept All</a>
							<a
								href="updateOrderStatus?shipId=${shipId}&saleOrderId=${saleOrderId}&shipSatus=REJECTED"
								class="btn btn-danger">Reject All</a>
						</c:if>
						<br /> <br />
					</div>
				</div>
			</div>
			<br /> <br />
			<!-- card body end -->


			<!-- It will display all child items in Purchase if exist -->
			<c:if test="${!empty salesDetails }">
				<div class="card-footer">
					<table class="table table-hover table-bordered">
						<tr>
							<th>SL NO</th>
							<th>ITEM CODE</th>
							<th>BASE COST</th>
							<th>QTY</th>
							<th>TOTAL</th>
							<th colspan="2">OPERATIONS</th>
						</tr>
						<c:forEach items="${salesDetails}" var="sdtls">
							<tr>

								<td><c:out value="${sdtls.slno}" /></td>
								<td><c:out value="${sdtls.item.itemCode}" /></td>
								<td><c:out value="${sdtls.item.itemBaseCost}" /></td>
								<td><c:out value="${sdtls.quantity}" /></td>
								<td><c:out
										value="${sdtls.quantity*sdtls.item.itemBaseCost}" /></td>
								<c:if test="${sdtls.shipSatus == null}">
									<td><a
										href="updateOrderStatus?shipId=${shipId}&salesDtlsId=${sdtls.salesDtlsId}&shipSatus=ACCEPTED"
										class="btn btn-success">Accept</a> <a
										href="updateOrderStatus?shipId=${shipId}&salesDtlsId=${sdtls.salesDtlsId}&shipSatus=REJECTED"
										class="btn btn-danger">Return</a> <br /> <br /></td>
								</c:if>
								<c:if test="${sdtls.shipSatus=='ACCEPTED'}">
									<td><font color="green"> Accepted </font></td>
								</c:if>
								<c:if test="${sdtls.shipSatus=='REJECTED'}">
									<td><font color="red"> Rejected </font></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>