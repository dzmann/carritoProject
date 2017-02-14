/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entities.Articulos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ArticulosFacadeLocal;


@WebServlet(name = "ListarArticulos", urlPatterns = {"/ListarArticulos"})
public class ListarArticulos extends HttpServlet {

    @EJB
    private ArticulosFacadeLocal articulosFacade;
    
    


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListarArticulos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListarArticulos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
                              if(request.getParameter("listar").equals("0")){
            
                
            

            
            List<Articulos> articulos = articulosFacade.findAll();
            List<Articulos> articulosCarrito = articulosFacade.findAll();
            
            request.getSession().setAttribute("listaArticulos",articulos);
            request.getSession().setAttribute("listaCarrito",articulosCarrito);
      

    }else if(request.getParameter("listar").equals("buscar")){
    
                String busqueda = request.getParameter("buscarArticulo");
                
               List<Articulos> articulos = articulosFacade.buscar(busqueda);
               request.getSession().setAttribute("listaArticulos",articulos);
               request.getRequestDispatcher("ListarArticulos.jsp").forward(request, response);
    
    }else{
          

            int i = Integer.parseInt(request.getParameter("listar"));
            List<Articulos> articulos = articulosFacade.articulosPorCategoria(i);
            request.getSession().setAttribute("listaArticulos",articulos);
                    
            request.getRequestDispatcher("ListarArticulos.jsp").forward(request, response);
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
