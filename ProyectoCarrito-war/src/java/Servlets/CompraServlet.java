/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entities.Compras;
import entities.Comprasfinales;
import entities.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.ArticulosFacadeLocal;
import services.ComprasFacadeLocal;
import services.ComprasfinalesFacadeLocal;
import services.RealizarCompraLocal;
import services.UsuariosFacadeLocal;

/**
 *
 * @author user
 */
@WebServlet(name = "CompraServlet", urlPatterns = {"/CompraServlet"})
public class CompraServlet extends HttpServlet {
    
    @EJB
    private RealizarCompraLocal realizarCompra;
    
    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    
    @EJB
    private ComprasfinalesFacadeLocal comprasfinalesFacade;
    
    @EJB
    private ComprasFacadeLocal comprasFacade;
    
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
            out.println("<title>Servlet CompraServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompraServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        String iDCompra = request.getParameter("id");
        
        List<Comprasfinales> listaHistorialCompras = comprasfinalesFacade.obtenerHistorialPorId(iDCompra);
        request.getSession().setAttribute("listaHistorial", listaHistorialCompras);
        response.sendRedirect("HistorialCompras.jsp");
        
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
        
        String accion = request.getParameter("accion");
        Usuarios userLogueado = (Usuarios) request.getSession().getAttribute("usuarioAutenticado");
        List<Compras> carrito = comprasFacade.findByUser(userLogueado.getNombreUsuario());
        
        if (accion.equals("finalizar")) {
            realizarCompra.finalizarCompra(carrito);
            response.sendRedirect("CompraRealizada.jsp");
        } else if (accion.equals("listar")) {
            List<Comprasfinales> historialCompras = realizarCompra.obtenerHistorial(userLogueado.getNombreUsuario());
            List<String> listaIDs = new ArrayList();
            
            for (Comprasfinales item : historialCompras) {
                
                if (!listaIDs.contains(item.getIdCompra())) {
                    listaIDs.add(item.getIdCompra());
                }
                
            }
            
            request.getSession().setAttribute("listaIDCompras", listaIDs);
            response.sendRedirect("HistorialCompras.jsp");
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
