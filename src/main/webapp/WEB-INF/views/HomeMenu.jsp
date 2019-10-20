<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="base">${pageContext.request.requestURL}</c:set>
<c:set var="url"	value="${fn:replace(base, pageContext.request.requestURI, pageContext.request.contextPath)}" />


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark ">
	<!-- Brand -->
	<a class="navbar-brand " href="#">Welcome to App Home Page!!</a>



		<li class="nav-item"><a class="nav-link" href="${url}/user/register">Register </a></li>
		<li class="nav-item"><a class="nav-link" href="${url}/login">Login</a></li>
		
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown">User Operations</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="${url}/user/register">Register </a> <a
					class="dropdown-item" href="${url}/login">Login</a>
			</div></li>

		

	</ul>
</nav>