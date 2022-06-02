/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import longtv.student.StudentDAO;
import org.w3c.dom.Document;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DeleteStudentController", urlPatterns = {"/DeleteStudentController"})
public class DeleteStudentController extends HttpServlet {
    private static final String ERROR = "invalid.html";
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
        String url = ERROR;
        try {
            ServletContext context = this.getServletContext();
            Document document = (Document) context.getAttribute("DOM_TREE");
            String id = request.getParameter("id");
            String searchValue = request.getParameter("lastSearchValue");
            if(document == null) {
                //Call Parse DOM Again
                //Store Context
            } // end Document this not exist
            //1. Call Model - DAO
            String xmlFilePath = context.getInitParameter("STUDENT_DB_FILE");
            String webApp = context.getRealPath("/");
            String xmlFile = webApp + xmlFilePath;
            StudentDAO dao = new StudentDAO();
            boolean result = dao.deleteStudent(id, document, xmlFile);
            
            //2. Process Result.
            if(result) {
                url = "DispatchController?btAction=Search&txtSearchValue=" + searchValue;
            }
        } catch (XPathExpressionException | TransformerConfigurationException ex) {
            Logger.getLogger(DeleteStudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
