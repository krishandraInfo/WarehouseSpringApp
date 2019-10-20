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
				<h2>Welcome to Uom Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>Uom Detais!</h3>
							</div>
							<div class="panel-footer">

								<table border="1">
									<tr>
										<td>Uom Id</td>
										<td>${uom.uomId}</td>
									</tr>
									<tr>
										<td>Uom Type</td>
										<td>${uom.uomType}</td>
									</tr>
									<tr>
										<td>Uom Model</td>
										<td>${uom.uomModel}</td>
									</tr>
									<tr>
										<td>Uom Description</td>
										<td>${uom.uomDesc}</td>
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