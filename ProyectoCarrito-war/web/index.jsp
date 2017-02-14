<%-- 
    Document   : listarTodos
    Created on : 28-jul-2016, 22:54:02
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
      
        <jsp:include page="ListarArticulos?listar=0" flush="true"></jsp:include>
        
        <jsp:include page="ListarArticulos.jsp"></jsp:include>
    </head>





    </body>
</html>
