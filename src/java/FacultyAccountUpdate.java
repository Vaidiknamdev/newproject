import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class FacultyAccountUpdate extends HttpServlet {
                  
        private PreparedStatement ps;
        private Connection con;
        public void init(){
               try{
              con = Utility.connect();
              String sql = "UPDATE faculty set password=?,address=?,email=?,mobile=?,status='enabled' WHERE userid=?";
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
          String address = request.getParameter("address");
          String email = request.getParameter("email");
          String mobile = request.getParameter("mobile");
//process the request 
          try{
              
              ps.setString(1,password);
              ps.setString(2,address);
              ps.setString(3,email);
              ps.setString(4,mobile);
              ps.setString(5,userid);
              ps.executeUpdate();
           pw.println("<html><body>");
           pw.println("<h3>profile udated successfully</h3>");
           pw.println("<hr>");
           pw.println("<h4><a href=index.jsp>login-now</h3>");
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
