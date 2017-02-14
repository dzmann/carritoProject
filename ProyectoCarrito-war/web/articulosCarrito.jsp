<%-- 
    Document   : articulosCarrito
    Created on : 10-ago-2016, 18:27:04
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:forEach var="carrito" begin="0" items="${sessionScope.Carrito}">
    <tr>
    <c:forEach var="articulo" begin="0" items="${sessionScope.listaCarrito}">
        <c:if test="${articulo.idArticulo == carrito.idArticulo}">
            
            <td class="valor-id-articulo">
                ${articulo.idArticulo}
            </td>
            
            <td class="celda-imagen-carrito">
                <img src="img/${articulo.imagen}" />
            </td>
           
            <td class="celda-descripcion-carrito">    
                ${articulo.nombreArticulo}
            </td>
            
            <td class="celda-precio-carrito">
                ${articulo.precio}
            </td>
           
            <td class="celda-cantidad-carrito">
                ${carrito.cantidad}
            </td>
            
            <td>
       

                <img id="agregar${articulo.idArticulo}" class="carrito-icono-agregar" src="img/agregar.png"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <img id="borrar${articulo.idArticulo}" class="carrito-icono-borrar" src="img/borrar.png"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <img id="eliminar${articulo.idArticulo}" class="carrito-icono-eliminar" src="img/deleteicon.png"/>
    
            </td>
        <tr>
            <td>
                <c:set var="cantidades" value="${carrito.cantidad * articulo.precio}"></c:set>
                <c:set var="resultado" value="${resultado + cantidades}">
                </c:set>
                <c:set var="total" value="${resultado}" scope="session"></c:set>
                
            </td>
            
        </tr>
        </c:if>
    </c:forEach>
    </tr>
 </c:forEach>
