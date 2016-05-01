<%-- 
    Document   : AddUser
    Created on : 30.4.2016, 17:52:12
    Author     : Nudista
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
    </head>
    <body>
        <div><h1>Servlet library staff</h1>
            <hr/>
            <h2>User saved!</h2>
            <table>
                <tr>
                    <td>Name :</td>
                    <td><%= request.getParameter("name")%></td>
                </tr>
                <tr>
                    <td>Surname :</td>
                    <td><%= request.getParameter("surname")%></td>
                </tr>
                <tr>
                    <td>E-mail :</td>
                    <td><%= request.getParameter("mail")%></td>
                </tr>
            </table>

            <hr/>
            <em>Leuven Group-T Library</em>
        </div>
    </body>
</html>
