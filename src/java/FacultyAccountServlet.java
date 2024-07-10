import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class FacultyAccountServlet extends HttpServlet {
                  
        private PreparedStatement ps;
        private Connection con;
        public void init(){
               try{
              con = Utility.connect();
              String sql = "INSERT INTO faculty(userid,password,name,status) values(?,?,?,'Disabled')";
              ps = con.prepareStatement(sql);
             }catch(Exception e){
                  e.printStackTrace();
             }
          }
        public void destroy() {
            try{
                  con.close();
          }catch(SQLException e){
              e.printStackTrace();
          }
        }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        PrintWriter pw = response.getWriter();
//       read the requestide 
          String userid = request.getParameter("userid");
          String password = request.getParameter("password");
          String name = request.getParameter("name");
          
//process the request 
          try{
              ps.setString(1,userid);
              ps.setString(2,password);
              ps.setString(3,name);
              ps.executeUpdate();
           pw.println("<html><body>");
           pw.println("<h3>Account created</h3>");
           pw.println("<hr>");
           pw.println("<h4><a href=admindashboard.jsp>Admin-Dashboard</h3>");
           pw.println("<hr>");
           pw.println("</body></html>");
          }catch(Exception e){
              pw.println(e);
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

}
