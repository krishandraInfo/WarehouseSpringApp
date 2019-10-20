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
				<h2>Welcome to Purchase Order Data Page!!</h2>
			</div>


			<div class="card-body">
				Order Code : <input type="text" value="${poCode}"
					class="form-control bg-light" readonly="readonly"> <br />
				Order Status : <input type="text" value="${poStatus}"
					class="form-control bg-light" readonly="readonly"> <br />					
				<br />
				
				
				<c:if test="${poStatus =='OPEN' || poStatus=='PICKING' }">
				<form:form action="addItem" method="post"
					modelAttribute="purchaseDtl" cssClass="form-inline">
					<form:hidden path="poHdrId" />
                Choose Item:
				<form:select path="itemDtl.itemId" class="form-control">
						<form:option value="">--SELECT--</form:option>
						<form:options items="${itemsMap}" />
					</form:select>
				Quantity :	<form:input path="itemsQty" class="form-control" />
					<input type="submit" value="ADD ITEM" class="btn btn-success">
				</form:form>
				</c:if>
				
				
			</div>  <!-- card body end -->


			<!-- It will display all child items in Purchase if exist -->
			<c:if test="${!empty dtls }">
				<div class="card-footer">
					<table class="table table-hover table-bordered">
						<tr>
							<th>SL NO</th>
							<th>ITEM CODE</th>
							<th>BASE COST</th>
							<th>QTY</th>
							<th>LINE TOTAL</th>
						</tr>
						<c:forEach items="${dtls}" var="poDtl">
							<tr>

								<td><c:out value="${poDtl.slno}" /></td>
								<td><c:out value="${poDtl.itemDtl.itemCode}" /></td>
								<td><c:out value="${poDtl.itemDtl.itemBaseCost}" /></td>
								<td><c:out value="${poDtl.itemsQty}" /></td>
								<td><c:out value="${poDtl.itemDtl.itemBaseCost * poDtl.itemsQty}" /></td>
								<c:if test="${poStatus =='PICKING' }">
									<td><a
										href="removeItem?orderDtlId=${poDtl.orderDtlId}&orderId=${poDtl.poHdrId}"
										class="btn btn-danger">DELETE</a></td>
								</c:if>
							</tr>
						</c:forEach>
						<tr>
							<c:if test="${poStatus =='PICKING' }">
								<td colspan="5" align="center"><a
									href="updateOrderStatus?orderId=${poId}&status=ORDERED"
									class="btn btn-success">CONFIRM ORDER</a></td>
							</c:if>
						</tr>
					</table>
				</div>
			</c:if>


		</div>

	</div>
</body>
</html>