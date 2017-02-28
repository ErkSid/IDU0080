<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>
        
        <c:if test="${not empty cars}">
<table class="normal-table">
  <thead>
      <tr>
	   <th>Autod</th>
	   <th></th>
	   </tr></thead> 
	   <tbody> 
	   <c:forEach var="car" items="${cars}">	   
	   	   <tr><td><a href="<c:url  value="/service/car/${car.id}">
						</c:url>">${car.make} ${car.model} </a></td><td></td></tr>
						
	   </c:forEach>
	   </tbody>
	   </table>
	   </c:if>
	   </td></tr>
	   </tbody>
	   </table>	
	   
    </body>
</html>
