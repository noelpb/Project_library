/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import library.StaffSesBeanLocal;

/**
 *
 * @author Nudista
 */
@WebServlet(name = "AddBook", urlPatterns = {"/ab"})
public class AddBook extends HttpServlet {
    StaffSesBeanLocal staffSesBean = lookupStaffSesBeanLocal();
   

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
            out.println("<title>Servlet AddBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String bookname, isbn, author;
        int pages, count;
        Genre g;
        response.setContentType("text/html");
        
        bookname=request.getParameter("bookname");
        isbn=request.getParameter("isbn");
        author=request.getParameter("author");
        pages=Integer.parseInt(request.getParameter("pages"));
        count=Integer.parseInt(request.getParameter("count"));
        g=Genre.valueOf(request.getParameter("genre"));

        staffSesBean.addLibItem(count, g, author, pages, isbn, bookname);
        PrintWriter out = response.getWriter();
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
        out.println("</body>");
        out.println("</html>");

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
        processRequest(request, response);

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

    private StaffSesBeanLocal lookupStaffSesBeanLocal() {
        try {
            Context c = new InitialContext();
            return (StaffSesBeanLocal) c.lookup("java:global/Project_library/Project_library-ejb/StaffSesBean!library.StaffSesBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

   

}
