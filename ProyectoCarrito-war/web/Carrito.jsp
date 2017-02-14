<%-- 
    Document   : Carrito
    Created on : 10-ago-2016, 17:00:43
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>  
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>

        <script type="text/javascript">
            $(document).ready(function () {
                $(".carrito-icono-agregar").click(function () {

                    cadena = $(this).attr('id');
                    valor = cadena.replace(/[A-Za-z$-]/g, "");



                    var data = {valor: valor,
                        operacion: 'agregar'};

                    $.ajax({
                        type: 'POST',
                        url: 'CarritoServlet',
                        data: data,
                        success: function (response) {
                            location.reload();

                        }
                    });

                });

                $(".carrito-icono-borrar").click(function () {

                    cadena = $(this).attr('id');
                    valor = cadena.replace(/[A-Za-z$-]/g, "");


                    var data = {valor: valor,
                        operacion: 'borrar'};

                    $.ajax({
                        type: 'POST',
                        url: 'CarritoServlet',
                        data: data,
                        success: function (response) {
                            location.reload();

                        }
                    });

                });

                $(".carrito-icono-eliminar").click(function () {

                    cadena = $(this).attr('id');
                    valor = cadena.replace(/[A-Za-z$-]/g, "");


                    var data = {valor: valor,
                        operacion: 'eliminar'};

                    $.ajax({
                        type: 'POST',
                        url: 'CarritoServlet',
                        data: data,
                        success: function (response) {
                            location.reload();

                        }
                    });

                });




            });
        </script>

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

    <center>
        <c:if test="${not empty sessionScope.Carrito}">


            <div id="resultados-carrito" class="resultados-carrito">
                <table id="danilo" class="tb-resultado-carrito" border="0">
                    <tr>
                        <td>ID</td>
                        <td>Imagen</td>
                        <td>Descripción</td>
                        <td>Precio</td>
                        <td>Cantidad</td>
                    </tr>

                    <jsp:include page="articulosCarrito.jsp"></jsp:include>

                    <tr><td>
                                       
                    <center><h1>TOTAL: $US&nbsp;<c:out value="${total}"></c:out></center></td>
                    </tr>
                    </table>
                </div>
                    
                    <form action="CompraServlet" method="POST"> 
                        <input hidden name="accion" value="finalizar"/>
                    <input  type="submit" value="Realizar la compra"/>
                    </form>
        </c:if>
        <c:if test="${empty sessionScope.Carrito}">
            No quedan más artículos en el carrito.<br>
            <a href="index.jsp">Volver a la tienda</a>
        </c:if>
    </center>
    <center><h2><a href="index.jsp">Ir al inicio.</a></h2></center>

</body>
</html>
