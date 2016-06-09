<%-- 
    Document   : staffJSP
    Created on : 8.5.2016, 16:31:43
    Author     : Nudista
--%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
        <title>Login Success Page</title>
    </head>
    <body>
        <%
//allow access only if session exists
            String user = (String) session.getAttribute("user");
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                }
            }
        %>
        <div><h1>Servlet library staff</h1>
            <hr/>
            <table style="width:100%">
                <tr>
                    <td>
                        <form action="./staff" method="get">
                            <h2>Create a new book item</h2>
                            <hr width="50%" align="left"/>
                            <h3>Book info</h3>
                            <table>
                                <tr>
                                    <td>Book title :</td>
                                    <td><input type="text" name="bookname"/></td>
                                </tr>
                                <tr>
                                    <td>Number of pages :</td>
                                    <td><input type="text" name="pages"/></td>
                                </tr>
                                <tr>
                                    <td>ISBN :</td>
                                    <td><input type="text" name="isbn" value="ISBN-"/></td>
                                </tr>
                                <tr>
                                    <td>Genre :</td>
                                    <td>

                                        <select name="genre">
                                            <option value="COMEDY">Comedy</option>
                                            <option value="DRAMA">Drama</option>
                                            <option value="NONFICTION">Nonfiction</option>
                                            <option value="NOVEL">Novel</option>
                                            <option value="FICTION">Fiction</option>
                                            <option value="SATIRE">Satire</option>
                                            <option value="TRAGEDY">Tragedy</option>
                                        </select>
                                    </td>
                                </tr>
                            </table>
                            <hr width="50%" align="left"/>
                            <h3>Author info</h3>
                            <table>
                                <tr>
                                    <td>Author :</td>
                                    <td><input type="text" name="author"/></td>
                                </tr>
                            </table>

                            <hr width="50%" align="left"/>
                            <h3>Set info</h3>
                            <table>
                                <tr>
                                    <td>Number of books :</td>
                                    <td><input type="text" name="count"/></td>
                                </tr>
                            </table>
                            <input type="submit" name="addbookbutton" value="Create Book"/>
                            <hr width="50%" align="left"/>
                        </form>
                    </td>

                    <td>
                        <form action="./staff" method="get">
                            <h2>Edit user</h2>
                            <hr  width="50%" align="left"/>
                            <table>
                                <tr>
                                    <td>Name :</td>
                                    <td><input type="text" name="name"/></td>
                                </tr>
                                <tr>
                                    <td>Surname :</td>
                                    <td><input type="text" name="surname"/></td>
                                </tr>
                                <tr>
                                    <td>E-mail :</td>
                                    <td><input type="email" name="mail"/></td>
                                </tr>
                                <tr>
                                    <td>Password :</td>
                                    <td><input type="password" name="pass"/></td>
                                </tr>
                            </table>
                            <input type="submit" name="adduserbutton" value="Create User"/>
                            <input type="submit" name="deleteuserbutton" value="Delete User"/>
                        </form>

                    </td>

                    <td>
                        <form action="./staff" method="get">
                            <h2>Close order</h2>
                            <hr width="50%" align="left"/>
                            <table>
                                <tr>
                                    <td>Order of user :</td>
                                    <td><input type="email" name="username"/></td>
                                </tr>
                                <tr>
                                    <td>From date :</td>
                                    <td><input type="text" name="date" value="mm/dd/yyyy"/></td>
                                </tr>
                            </table>
                            <input type="submit" name="closeorderbutton" value="Close Order"/>
                        </form>
                    </td>
                </tr>
            </table>
             
            <hr/>
            <form action="./staff" method="get">
            <input type="hidden" name="usermail1" value=<%=user%> />
            <input type="submit" name="director" value="Director" />
            </form>
            <br/>
            User=<%=user%>
            <br/>
             Hi <%=userName%>, Login successful. Your Session ID=<%=sessionID%>
            <hr/>
            <a href="./index.html">Back HOME</a>
            <br/>
            <em>Leuven Group-T Library</em></div>
    </body>
</html>
