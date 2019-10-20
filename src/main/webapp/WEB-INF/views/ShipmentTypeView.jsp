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
				<h2>Welcome to Shipment Type Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>ShipmentType Detais!</h3>
							</div>
							<div class="panel-footer">
								<table border="1">
									<tr>
										<td>Shipment Id</td>
										<td>${shipmentType.shipmentid}</td>
									</tr>
									<tr>
										<td>Shipment Mode</td>
										<td>${shipmentType.shipmentMode}</td>
									</tr>
									<tr>
										<td>Shipment Code</td>
										<td>${shipmentType.shipmentCode}</td>
									</tr>
									<tr>
										<td>Shipment Status</td>
										<td>${shipmentType.enableShipment}</td>
									</tr>
									<tr>
										<td>Shipment Grade</td>
										<td>${shipmentType.shipmentGrade}</td>
									</tr>
									<tr>
										<td>Shipment Description</td>
										<td>${shipmentType.shipDesc}</td>
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