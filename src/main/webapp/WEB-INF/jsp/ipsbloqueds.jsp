<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

	<head>
		<title>Welcome</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	
	</head>
	
	<body>
		<div class="container">
		
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="list">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>IP</th>
								<th>Description</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>${list.ip}</td>
									<td>${list.description}</td>
								</tr>
						</tbody>
					</table>			
				</c:forEach>
			</c:if>
			
			
			<div>
				<a class="btn btn-primary" href="/home">Go Home!</a>
			</div>	
		
			
			<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
			<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		</div>
	</body>
</html>