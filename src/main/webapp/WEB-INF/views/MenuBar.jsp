<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base">${pageContext.request.requestURL}</c:set>
<c:set var="url"
	value="${fn:replace(base, pageContext.request.requestURI, pageContext.request.contextPath)}" />


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
	<!-- Brand -->
	<a class="navbar-brand " href="#"><img
		src="../resources/images/logo.png" width="100" height="60" /></a>

	<!-- Links -->
	<ul class="navbar-nav">
		<!--   <li class="nav-item">
      <a class="nav-link" href="#">Link 1</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link 2</a>
    </li> -->

		<!--Uom Dropdown start -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> UOM </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/uom/register">Register </a> <a
					class="dropdown-item" href="${url}/uom/view">View All</a>
			</div></li>
		<!--Uom Dropdown end -->


		<!-- Order Method start-->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Order Method </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/order/register">Register </a>
				<a class="dropdown-item" href="${url}/order/view">View All</a>

			</div></li>
		<!-- Order Method end-->

		<!-- Shipment Type start-->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Shipment Type </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/shipment/register">Register
				</a> <a class="dropdown-item" href="${url}/shipment/view">View All</a>

			</div></li>
		<!-- Shipment Type end-->


		<!-- WH USER TYPE -->

		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Warehouse User </a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/whuser/register">Register
				</a> <a class="dropdown-item" href="${url}/whuser/view">View All</a>

			</div></li>

		<!-- ITEM  -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">Item</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/item/register">Register </a> <a
					class="dropdown-item" href="${url}/item/view">View All</a>

			</div></li>
		<%-- 	<!-- Employee -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">Employee</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/employee/register">Register </a>
        <a class="dropdown-item" href="${url}/employee/all">View All</a>
       
			</div></li> --%>

		<!-- Purchase Order -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">Purchase Order</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/purchase/register">Register
				</a> <a class="dropdown-item" href="${url}/purchase/view">View All</a>
			</div></li>

		<!-- Sale Order -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">Sale Order</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/sale/register">Register </a> <a
					class="dropdown-item" href="${url}/sale/view">View All</a>
			</div></li>

		<!-- User -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">User</a>
			<div class="dropdown-menu">
				<%-- <a class="dropdown-item" href="${url}/user/register">Register </a> --%>
				 <a class="dropdown-item" href="${url}/user/view">View All</a>
			</div></li>

		<!-- Grn -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">GRN</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/grn/register">Register </a> <a
					class="dropdown-item" href="${url}/grn/view">View All</a>
			</div></li>

		<!-- Shipping -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">Shipping</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/shipping/register">Register </a> <a
					class="dropdown-item" href="${url}/shipping/view">View All</a>
			</div></li>

		<li class="nav-item"><a class="nav-link" href="${url}/logout">LOGOUT</a></li>
		<%-- 		<!-- SWAGGER -->
		<li class="nav-item"><a class="nav-link"
			href="${url}/swagger-ui.html">Swagger</a></li>

		<li class="nav-item"><a class="nav-link" href="${url}/logout">logout</a></li>

 --%>
	</ul>
</nav>