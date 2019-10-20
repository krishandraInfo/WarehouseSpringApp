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
								<h3>GRN Detais!</h3>
							</div>
							<div class="panel-footer">

								<table border="1">
									<tr>
										<td>GRN Id</td>
										<td>${goodRecieveNote.grnId}</td>
									</tr>
									<tr>
										<td>GRN Model</td>
										<td>${goodRecieveNote.grnCode}</td>
									</tr>
									<tr>
										<td>GRB Type</td>
										<td>${goodRecieveNote.grnType}</td>
									</tr>
									<tr>
										<td>Purchase Order Code</td>
										<td>${goodRecieveNote.purchase.orderCode}</td>
									</tr>
									<tr>
										<td>GRN Description</td>
										<td>${goodRecieveNote.grnDesc}</td>
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