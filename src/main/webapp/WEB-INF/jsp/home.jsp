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
		
			<c:if test="${not empty errors}">
				<div class="alert alert-danger">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  <strong>Error!</strong>
				  	<p> ${errors} </p>
				</div>			
			</c:if>
			
			<form:form method="post" servletRelativeAction="search">
			
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Start Date</th>
							<th>Duration</th>
							<th>threshold</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
							<tr>
								<td>
									<div class="input-group date">
									    <input type="text" name="startDate" 
									    	class="form-control datepicker" 
									    	placeholder="format: yyyy-MM-dd HH:mm:ss"
									    	required="required">
									    	
									    <div class="input-group-addon">
									        <span class="glyphicon glyphicon-th"></span>
									    </div>
									</div>														
								</td>
								<td>
									<select class="form-control" name="duration" required="required">
									  <option>hourly</option>
									  <option>daily</option>
									</select>							
								</td>
								<td>
									<input type="number" class="form-control" name="threshold" required="required">
								</td>
								<td>
									 <button type="submit" class="btn btn-primary">Search</button>
								</td>
							</tr>
					</tbody>
				</table>
			</form:form>
			
			<c:if test="${not empty msgInfo}">
				<div class="alert alert-warning">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				  	<p> ${msgInfo} </p>
				</div>			
			</c:if>			
			
			<c:if test="${not empty listIps}">
				<c:forEach items="${listIps}" var="list">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>IP</th>
								<th>Bloqued</th>
							</tr>
						</thead>
						<tbody>
								<tr>
									<td>${list.ip}</td>
									<td>${list.bloqued}</td>
								</tr>
						</tbody>
					</table>			
				</c:forEach>
			</c:if>
				
		
			<div>
				<a class="btn btn-danger" href="/ips-bloqued">List of blocked ips</a>
			</div>
			
			<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
			<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		</div>
	</body>
</html>