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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rache
 */
@WebServlet(urlPatterns = {"/server4"})
public class server4 extends HttpServlet {

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
            out.println("<title>Servlet server4</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet server4 at " + request.getContextPath() + "</h1>");
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
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        try
        {
         Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/TEST_DETAILS","rachel","rachel");
            PreparedStatement stmt= con.prepareStatement("Select * from admin");
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                if(username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                {
                    //response.sendRedirect("server3");
                    String sql="Select * from marks";
                    try
                    {
                        Statement stm=con.createStatement();
                        ResultSet rs1=stm.executeQuery(sql);
                        out.print("<body><h2>MARK LIST</h2><br><style>table {" +
                            "border-collapse: collapse;width:100%;text-align:center;" +
                            "}table, th, td{border: 1px solid black;}"
                             + "</style><table><tr><th>Name</th><th>Marks</th></tr>");
                        while(rs1.next())
                        {
                            String name=rs1.getString("username");
                            int marks=rs1.getInt("mark");
                            out.print("<tr><td>"+name+"</td><td>"+marks+"</td></tr>");
                        }
                        out.print("</table></body>");
                    }
                    catch(Exception e)
                    {
                        e.getMessage();
                        out.print(e);      
                    }
                }
            }
        }
        catch(Exception e)
                {
                    
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
