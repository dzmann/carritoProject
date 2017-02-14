<%-- 
    Document   : HistorialCompras
    Created on : 11-ago-2016, 14:27:49
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>  
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            <div class="div-login-carrito">
                <table>
                    <tr>
                        <td>Usuario: ${sessionScope.usuarioAutenticado.nombreUsuario}</td>
                    </tr>
                    <tr>
                        <td><a href="LoginServlet?a=logout">Cerrar Sesion</a></td>
                    </tr>
                </table>


            </div>  
        </div>

        <table>
            <c:forEach var="ID" begin="0" items="${sessionScope.listaIDCompras}">
                <tr>
                    <td>
                        <a href="CompraServlet?id=${ID}">Ver compra: ${ID}</a>
                    </td>
                </tr>    
            </c:forEach>    

        </table>
        <br>

        <c:if test="${not empty sessionScope.listaHistorial}">
        <center>
            <h1>ID de compra: #${sessionScope.listaHistorial.get(0).idCompra}</h1>
            <br>
            <table>
                <tr>
                    <td>Id</td>
                    <td>Imagen</td>
                    <td>Descripcion</td>
                    <td>Precio</td>
                    <td>Cantidad</td>
                </tr>

                <c:forEach var="artCompra" begin="0" items="${sessionScope.listaHistorial}">

                    <c:forEach var="articulo" begin="0" items="${sessionScope.listaCarrito}">
                        <c:if test="${articulo.idArticulo == artCompra.idArticulo}">
                            <tr>
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
                                    ${artCompra.cantidad}
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <c:set var="cantidades" value="${artCompra.cantidad * articulo.precio}"></c:set>
                                    <c:set var="resultado" value="${resultado + cantidades}"></c:set>
                                    <c:set var="total" value="${resultado}" scope="session"></c:set>

                                    </td>
                                </tr>

                        </c:if>
                    </c:forEach>

                </c:forEach>

            </c:if>
                                <tr>
                                    <td><h2>TOTAL: $US ${total}</h2></td>
                                </tr>
                                
                                <center><h2><a href="index.jsp">Ir al inicio.</a></h2></center>
        </table>
    </center>
</body>
</html>
