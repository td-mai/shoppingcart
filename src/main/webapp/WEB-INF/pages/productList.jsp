<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ProductList</title>
</head>
<body>

	<div class="page-title" >Product List</div>
	<c:forEach items="${paginationProducts.list}" var="productInfo">
		<div>
		<li><img src="${pageContext.request.contextPath}/productImage?code=${productInfo.code}">
		</li>
		<li>Code: ${productInfo.code}</li>
		<li>Name: ${productInfo.name }: </li>
		</div>
		
	</c:forEach>
	
</body>
</html>