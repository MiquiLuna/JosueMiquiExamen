<%@page import="com.emergentes.modelo.Calificacion"%>
<%
    Calificacion reg = (Calificacion)request.getAttribute("misession");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div style="text-align:center;">
        <table border="1" style="margin: 0 auto;">         
            <tr>
                <th><br>Primer Parcial TEM-742</br>
                <br>NOMBRE: Jose Josue Miqui Luna</br>                               
                <br>CARNET: 9901822 </br></th>
                
            </tr>
        </table>
        </div>
        
        <h1>REGISTRO DE CALIFICACIONES</h1>
        <form action=""MainServlet" method="post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%=reg.getId()%>" size="2" readonly></td>             
                </tr>
                <tr>
                    <td>Nombres</td>
                    <td><input type="text" name="nombre" value="<%=reg.getNombre() %>"></td>
                </tr>
                <tr>
                    <td>P1</td>
                    <td><input type="text" name="p1" value="<%=reg.getP1() %>"></td>
                </tr>
                <tr>
                    <td>P2</td>
                    <td><input type="text" name="p2" value="<%=reg.getP2() %>"></td>
                </tr>
                <tr>
                    <td>EF</td>
                    <td><input type="text" name="ef" value="<%=reg.getEf() %>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
