<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<h2>Welcome to Order Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>Order Method Detais</h3>
							</div>
							<div class="panel-footer">
								<table class="table table-hover table-bordered">
									<tr>
										<td>Order Id</td>
										<td>${orderMethod.orderId}</td>
									</tr>
									<tr>
										<td>Order Mode</td>
										<td>${orderMethod.orderMode}</td>
									</tr>
									<tr>
										<td>Order Code</td>
										<td>${orderMethod.orderCode}</td>
									</tr>
									<tr>
										<td>Order ExecuteType</td>
										<td>${orderMethod.orderExeType}</td>
									</tr>
									<tr>
										<td>Order Accept</td>
										<td>${orderMethod.orderAccept}</td>
									</tr>
									<tr>
										<td>Order Description</td>
										<td>${orderMethod.orderDecs}</td>
									</tr>
								</table>
							</div>

							<div class="card-footer">
								<a href="view">Back</a>
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