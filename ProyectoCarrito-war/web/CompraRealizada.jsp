<%-- 
    Document   : CompraRealizada
    Created on : 11-ago-2016, 14:34:18
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Has realizado la compra!</h1><br>
        
        <form action="CompraServlet" method="POST">
            <input hidden name="accion" value="listar"/>
            <input type="submit" value="Ver historial de Compras" />
        </form>
    </body>
</html>
