<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
	<%@include file="MenuBar.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-info text-white">
				<h2>Welcome to Sale Order Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>Sale Details!</h3>
							</div>
							<div class="panel-footer">

								<table border="1">
									<tr>
										<td>Order Id</td>
										<td>${saleOrder.saleOrderId}</td>
									</tr>
									<tr>
										<td>Order Code</td>
										<td>${saleOrder.saleOrderCode}</td>
									</tr>
									<tr>
										<td>Reference Number</td>
										<td>${saleOrder.refNum}</td>
									</tr>
									<tr>
										<td>Shipment Code</td>
										<td>${saleOrder.shipmentType.shipmentCode}</td>
									</tr>
									<tr>
										<td>Stock Mode</td>
										<td>${saleOrder.stockMode}</td>
									</tr>
									<tr>
										<td>Stock Source</td>
										<td>${saleOrder.stockSource}</td>
									</tr>
									<tr>
										<td>Status</td>
										<td>${saleOrder.orderStatus}</td>
									</tr>
									<tr>
										<td>Description</td>
										<td>${saleOrder.orderDesc}</td>
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