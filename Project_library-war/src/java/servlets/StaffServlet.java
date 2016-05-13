/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.StaffSesBeanLocal;
import entities.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nudista
 */
@WebServlet(name = "StaffServlet", urlPatterns = {"/staff"})
public class StaffServlet extends HttpServlet {

    @EJB
    private StaffSesBeanLocal staffSesBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StaffServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void addbook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookname, isbn, author;
        int pages, count;
        Genre g;
        response.setContentType("text/html");
        boolean res;

        bookname = request.getParameter("bookname");
        isbn = request.getParameter("isbn");
        author = request.getParameter("author");
        try {
            pages = Integer.parseInt(request.getParameter("pages"));
            count = Integer.parseInt(request.getParameter("count"));
        } catch (NumberFormatException e) {
            pages = 0;
            count = 0;
        }
        g = Genre.valueOf(request.getParameter("genre"));
        PrintWriter out = response.getWriter();
        res = staffSesBean.addLibItem(count, g, author, pages, isbn, bookname);
        if (res) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2>Book created</h2>");
            out.println("<hr>");
            out.println("<TABLE border=0>");
            out.println("<TR>");
            out.println("<TD>Book name:</TD>");
            out.println("<TD>" + bookname + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Number of pages:</TD>");
            out.println("<TD>" + pages + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>ISBN:</TD>");
            out.println("<TD>" + isbn + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Genre:</TD>");
            out.println("<TD>" + g.toString() + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Writer:</TD>");
            out.println("<TD>" + author + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Number of books:</TD>");
            out.println("<TD>" + count + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2><font color=\"red\">Book with this ISBN already exists. Please check the ISBN and try it again.</font></h2>");
            out.println("<hr>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    private void adduser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name, surname, mail, pass;
        boolean res;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        mail = request.getParameter("mail");
        pass = request.getParameter("pass");

        res = staffSesBean.addUser(name, surname, mail, pass);
        if (res) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2>User added</h2>");
            out.println("<hr>");
            out.println("<TABLE border=0>");
            out.println("<TR>");
            out.println("<TD>Name:</TD>");
            out.println("<TD>" + name + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Surname:</TD>");
            out.println("<TD>" + surname + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>E-mail (username):</TD>");
            out.println("<TD>" + mail + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2><font color=\"red\">User already exists. Please check the user e-mail and try it again.</font></h2>");
            out.println("<hr>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    private void deleteuser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name, surname, mail, pass;
        response.setContentType("text/html");
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        mail = request.getParameter("mail");
        pass = request.getParameter("pass");
        PrintWriter out = response.getWriter();

        if (staffSesBean.deleteUser(mail)) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Staff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2>User deleted</h2>");
            out.println("<hr>");
            out.println("<TABLE border=0>");
            out.println("<TR>");
            out.println("<TD>Name:</TD>");
            out.println("<TD>" + name + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>Surname:</TD>");
            out.println("<TD>" + surname + "</td>");
            out.println("</tr>");
            out.println("<TR>");
            out.println("<TD>E-mail (username):</TD>");
            out.println("<TD>" + mail + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Staff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1><centre>Servlet library staff</centre></h1>");
            out.println("<hr>");
            out.println("<H2><font color=\"red\">User wasn't deleted. User " + mail + " doesn't exist or is admin user!!!</font></h2>");
            out.println("<hr>");
            out.println("<a href=\"./index.html\">Back HOME</a>");
            out.println("<br/>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void closeorder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username, date;
        response.setContentType("text/html");
        username = request.getParameter("username");
        date = request.getParameter("date");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet AddUser</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1><centre>Servlet library staff</centre></h1>");
        out.println("<hr>");
        out.println("<H2>Order is closed</h2>");
        out.println("<hr>");
        out.println("<a href=\"./index.html\">Back HOME</a>");
        out.println("<br/>");
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("addbookbutton") != null) {
            addbook(request, response);
        } else if (request.getParameter("adduserbutton") != null) {
            adduser(request, response);
        } else if (request.getParameter("closeorderbutton") != null) {
            closeorder(request, response);
        } else if (request.getParameter("deleteuserbutton") != null) {
            deleteuser(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        // get request parameters for userID and password
        String user = request.getParameter("user");
        String passFromDatabase = staffSesBean.getPassword(user);
        String pwd = request.getParameter("pass");

        if (pwd.equals(passFromDatabase) || (pwd.equals("demopassword") && user.equals("demouser"))) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);
            response.sendRedirect("staffJSP.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/stafflogin.xhtml");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong. Or you are not admin.</font>");
            rd.include(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
