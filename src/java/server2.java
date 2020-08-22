/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rache
 */
@WebServlet(urlPatterns = {"/server2"})
public class server2 extends HttpServlet {

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
            out.println("<title>Servlet server2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet server2 at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        //processRequest(request, response);
        PrintWriter out=response.getWriter();
        String radio1=request.getParameter("Question1");
        int sum=0;
        if(radio1.equals("54"))
        {
            //out.print("Question1 correct");
            sum+=10;
        }
        String radio2=request.getParameter("Question2");
        if(radio2.equals("24"))
        {
            sum+=10;
        }
         String radio3=request.getParameter("Question3");
        if(radio3.equals("36"))
        {
            sum+=10;
        }
         String radio4=request.getParameter("Question4");
        if(radio4.equals("9"))
        {
            sum+=10;
        }
         String radio5=request.getParameter("Question5");
        if(radio5.equals("50"))
        {
            sum+=10;
        }
        //out.print(sum);
        Cookie[] cookie=null;
        cookie=request.getCookies();
        String username=null;
        int flag=0;
        int i;
        try
        {
        for(i=0;i<cookie.length;i++)
        {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/TEST_DETAILS","rachel","rachel");
        Statement statement = con.createStatement();
        ResultSet rs;
        rs = statement.executeQuery("SELECT username FROM marks");
        while(rs.next())
        {
          String name = rs.getString(1);
            if(name.equals(cookie[i].getValue()))
            {
              flag=1;
              username=name;
              break;
            }
        }
        if(flag==1)
        {
          break;
        }
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        out.print("<body><h1>Congratulations "+username+"!, you have scored "+sum);
        try
        {
             Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/TEST_DETAILS","rachel","rachel");
            String sql="Update marks set mark=? where username=?";
            try
            {  
            PreparedStatement stmt=con.prepareStatement(sql);
            stmt.setString(2,username);
            stmt.setInt(1, sum);
            stmt.executeUpdate();
            con.commit();
            }
            catch(Exception e)
                    {
                        e.getMessage();
                        out.print(e);
                        e.printStackTrace();
                    }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
