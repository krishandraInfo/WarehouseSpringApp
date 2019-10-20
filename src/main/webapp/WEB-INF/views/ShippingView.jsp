<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<h2>Welcome to GRN Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>Shipping  Detais!</h3>
							</div>
							<div class="panel-footer">

								<table border="1">
									<tr>
										<td>Shipping Id</td>
										<td>${shipping.shipId}</td>
									</tr>
									<tr>
										<td>Shipping Code</td>
										<td>${shipping.shipCode}</td>
									</tr>
									<tr>
										<td>Shipping Ref Num</td>
										<td>${shipping.shipRefNum}</td>
									</tr>
									<tr>
										<td>Sale Order Code</td>
										<td>${shipping.saleOrder.saleOrderCode}</td>
									</tr>
									<tr>
										<td>Courier Ref Num</td>
										<td>${shipping.courRefNum}</td>
									</tr>
									<tr>
										<td>Courier Cont Dtls</td>
										<td>${shipping.couContdtls}</td>
									</tr>
									<tr>
										<td>Billing Address</td>
										<td>${shipping.billAddr}</td>
									</tr>
									<tr>
										<td>Shipping Address</td>
										<td>${shipping.shipAddr}</td>
									</tr>
									<tr>
										<td> Description</td>
										<td>${shipping.shipDesc}</td>
									</tr>
								</table>
								<div class="card-footer">
									<a href="view">Back</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
</body>
</html>