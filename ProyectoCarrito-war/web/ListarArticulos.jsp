<%-- 
    Document   : listarTodos
    Created on : 28-jul-2016, 22:54:02
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:set var="validado" value="${requestScope.validar}"></c:set>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        <jsp:include page="obtenerCategorias" flush="true"></jsp:include>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>        

            <script type="text/javascript">
                $(document).ready(function () {
                    $("#tablaCategorias td").click(function () {

                        var idTD = $(this).attr("id");
                        var dir = "http://localhost:8080/ProyectoCarrito-war/ListarArticulos?listar=" + idTD;
                        $(location).attr('href', dir);
                    });

                    $(".add-cart-imagen").click(function () {

                        var idArt = $(this).attr("id");

                        var data = {valor: idArt,
                            operacion: 'agregar'};
                        $.ajax({
                            type: 'POST',
                            url: 'CarritoServlet',
                            data: data,
                            success: function (response) {
                                $("#loginSpan").load("login.jsp");

                            }
                        });




                    });

                });


            </script>





            <link rel="stylesheet" type="text/css" href="css/estilos.css"/>

        </head>
        <body>
            
            <div class="header">
                        <c:if test="${validar}">


            <div id="usuarioLogin" class="div-login">
                <c:if test="${not empty sessionScope.listaIDCompras}">
                    <h4><a href="HistorialCompras.jsp">Mis compras</a></h4>   
                </c:if>
                
                <h3><span id="loginSpan">
                        <jsp:include page="login.jsp"></jsp:include>

                        </span></h3>

                    <a href="LoginServlet?a=logout">Cerrar Sesion</a>
                </div>

        </c:if> 
                
            </div>


            <div id="div-buscar">
                <form action="ListarArticulos" method="GET">
                    Buscar:<input name="buscarArticulo" type="text"/>
                    <input hidden name="listar" value="buscar"/>
                    <input type="submit" value="Buscar"/>
                </form>
            </div>


        <c:if test="${!validar}">
            <div class="div-login">
                <form id="login" method="post" action="LoginServlet">
                    <table id="tb-login">
                        <tr>
                            <td>Usuario: </td>
                            <td><input id="input-usuario" type="text" name="usuario"/></td>
                        </tr>
                        <tr>
                            <td>Contraseña:</td>
                            <td><input id="input-password" type="password" name="password"</td>
                        </tr>
                        <tr>
                            <td><button id="btn-login" type="submit">Ingresar</button></td>
                        </tr>

                    </table>
                </form>
            </div>
        </c:if>




        <!--Menu lateral, listado de categorias-->
        <div class="div-contendor-categorias">
            <div class="div-tabla-categorias">
                <table id="tablaCategorias" class="tb-categorias">
                    <c:forEach var="categoria" begin="0" items="${requestScope.listarCategorias}">
                        <tr>
                            <td id="${categoria.idCategoria}">${categoria.nombreCategoria}</td>
                        </tr>


                    </c:forEach>
                </table>
            </div>
        </div>



        <!--Bloque donde se muestran los resultados-->
        <div class="div-resultados">


        <c:forEach var="articulo" begin="0" items="${sessionScope.listaArticulos}">
                <div class="div-articulos">
                    <table class="tb-articulos" border="0">
                        <tr>
                            <td>${articulo.nombreArticulo}&nbsp;&nbsp;</td>



                        </tr>
                        <tr>

                            <td><img src="img/${articulo.imagen}"/></td>

                        </tr>
                        <tr>
                            <td>U$S ${articulo.precio}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${validar}"><img class="add-cart-imagen" id="${articulo.idArticulo}" src="img/add-cart.png"/></c:if></td>
                            </tr>

                        </table>
                    </div>
            </c:forEach>
        <c:if test="${empty sessionScope.listaArticulos}">
            No se encontraron resultados.
        </c:if>

        </div>





    </body>
</html>
