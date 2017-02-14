<%-- 
    Document   : login
    Created on : 10-ago-2016, 17:14:56
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>

Usuario Logueado: <c:out value="${sessionScope.usuarioAutenticado.nombreUsuario}"></c:out><br>
<c:if test="${sessionScope.CantidadCarrito>0}"><img style="width: 50px; height: 50px" src="img/cart.png"/>(<c:out value="${sessionScope.CantidadCarrito}"></c:out>)<a href="Carrito.jsp">Ver carrito</a></c:if>
