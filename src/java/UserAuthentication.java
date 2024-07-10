import java.sql.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class UserAuthentication extends HttpServlet {
    private Connection con;
    private PreparedStatement ps,ps1;
    public void init(){
               try{
              con = Utility.connect();
              String sql = "select * from student where userid=? AND password=?";
              ps = con.prepareStatement(sql);
              ps1 = con.prepareCall("SELECT * FROM faculty where userid=? AND password=?");
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
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        PrintWriter pw = response.getWriter();    
       
        if(usertype.equals("Admin")){
            ServletConfig config = getServletConfig();
            String adminid = config.getInitParameter("userid");
            String adminpass = config.getInitParameter("password");
            if(userid.equals(adminid) && password.equals(adminpass)){
            response.sendRedirect("admindashboard.jsp");
            }else{
                pw.println("invalid admin");
            }
        }else if(usertype.equals("Faculty")){
            try{
            ps1.setString(1, userid);
            ps1.setString(2, password);
           ResultSet rs = ps1.executeQuery(); 
           boolean found = rs.next();
           if(found){
               String status = rs.getString("status");
               if(status.equals("Disabled")){
              response.sendRedirect("facultyupdation.jsp");
              }
               else{
                   response.sendRedirect("facultydashboard.jsp");
               }
           }else{
               pw.println("invalid faculty ");
           }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(usertype.equals("Student")){
            try{
            ps.setString(1, userid);
            ps.setString(2, password);
           ResultSet rs = ps.executeQuery(); 
           boolean found = rs.next();
           if(found){
              response.sendRedirect("studentdashboard.jsp");
           }else{
               pw.println("invalid student");
           }
            }catch(Exception e){
                e.printStackTrace();
            }
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
