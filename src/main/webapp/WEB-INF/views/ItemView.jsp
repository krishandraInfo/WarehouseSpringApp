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
				<h2>Welcome to Item Data Page!!</h2>
			</div>
			<br> <br> <br>
			<div class="card-body">
				<div class="container" style="text-align: center;">
					<div class="col-md-6 col-md-offset-3">
						<div class="panel_form panel panel-default">
							<div class="panel-content">
								<h3>Item Detais!</h3>
							</div>
							<br> <br>
							<div class="panel-footer">
								<div class="row">
									<div class="col=4"></div>
									<div class="col=4">

										<table border="1">
											<tr>
												<td>Id</td>
												<td>${item.itemId}</td>
											</tr>
											<tr>
												<td>Code</td>
												<td>${item.itemCode}</td>
											</tr>
											<tr>
												<td>Dimensions</td>
												<td>${item.itemLength}(L),<br>${item.itemWidth}(W),<br>${item.itemHeight}(H)</td>
											</tr>
											<tr>
												<td>Price</td>
												<td>${item.itemBaseCost}</td>
											</tr>
											<tr>
												<td>Currency</td>
												<td>${item.itemCurrentCurrency}</td>
											</tr>
											<tr>
												<td>NOTE</td>
												<td>${item.itemDesc}</td>
											</tr>
											<tr>
												<td>UOM MODE</td>
												<td>${item.uom.uomModel}</td>
											</tr>
											<tr>
												<td>ORDER MODE</td>
												<td>${item.uom.uomType}</td>
											</tr>
										</table>
									</div>
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
	</div>
	<br>

</body>
</html>