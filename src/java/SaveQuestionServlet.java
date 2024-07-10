import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class SaveQuestionServlet extends HttpServlet {
                  
        private PreparedStatement ps;
        private Connection con;
        public void init(){
               try{
              con = Utility.connect();
              String sql = "INSERT INTO Questionbank(question,sdate,fid,subject) values(?,?,?,?)";
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
          String question = request.getParameter("question");
          String subject = request.getParameter("subject");
          java.util.Date dt= new java.util.Date();
          long val=dt.getTime();
          java.sql.Date sdate= new java.sql.Date(val);
          
          
//process the request 
          try{
              ps.setString(1,question);
              ps.setDate(2, sdate);
              ps.setString(3,userid);
              ps.setString(4,subject);
              ps.executeUpdate();
           pw.println("<html><body>");
           pw.println("<h3>question uploaded successfully</h3>");
           pw.println("<hr>");
           pw.println("<h4><a href=admindashboard.jsp>Admin-Dashboard</h3>");
           pw.println("<h4><a href=questionupload.jsp>another upload question</h3>");
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
