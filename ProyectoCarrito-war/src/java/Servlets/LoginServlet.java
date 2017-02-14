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
import javax.servlet.http.HttpSession;
import services.ComprasFacadeLocal;
import services.ComprasfinalesFacadeLocal;
import services.RealizarCompraLocal;
import services.UsuariosFacadeLocal;
import services.ValidarLoginLocal;

/**
 *
 * @author user
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private RealizarCompraLocal realizarCompra;

    @EJB
    private ComprasfinalesFacadeLocal comprasfinalesFacade;

    @EJB
    private ComprasFacadeLocal comprasFacade;

    @EJB
    private UsuariosFacadeLocal usuariosFacade;

    @EJB
    private ValidarLoginLocal validarLogin;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("usuario");
        String pass = request.getParameter("password");
        int sumarArt = 0;
        response.setContentType("text/html;charset=UTF-8");

        boolean validar = validarLogin.validarUsuario(user, pass);
        PrintWriter out = response.getWriter();

        if (validar) {
            Usuarios usuarioAutenticado = usuariosFacade.buscarUsuario(user);
            HttpSession sesion = request.getSession();

            sesion.setAttribute("usuarioAutenticado", usuarioAutenticado);
            sesion.setAttribute("validar", validar);

            List<Comprasfinales> historialCompras = realizarCompra.obtenerHistorial(usuarioAutenticado.getNombreUsuario());
            List<String> listaIDs = new ArrayList();

            for (Comprasfinales item : historialCompras) {

                if (!listaIDs.contains(item.getIdCompra())) {
                    listaIDs.add(item.getIdCompra());
                }

            }

            List<Compras> carrito = comprasFacade.findByUser(usuarioAutenticado.getNombreUsuario());
            for (Compras compra : carrito) {
                sumarArt += compra.getCantidad();
            }
            request.getSession().setAttribute("CantidadCarrito", sumarArt);
            request.getSession().setAttribute("Carrito", carrito);

            request.getSession().setAttribute("listaIDCompras", listaIDs);

            //List<Compras> carrito = comprasFacade.findByUser(usuarioAutenticado.getNombreUsuario());

            if (!carrito.isEmpty()) {

                for (Compras compra : carrito) {
                    sumarArt += compra.getCantidad();
                }

                request.getSession().setAttribute("Carrito", carrito);

            }
            request.getSession().setAttribute("CantidadCarrito", sumarArt);

            response.sendRedirect("index.jsp");

        } else {
            out.println("<h2>Error de login</h2>");
            out.println("<a href='index.jsp'>Volver</a>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String i = request.getParameter("a");

        if (i.equals("logout")) {
            HttpSession sesion = request.getSession();
            sesion.invalidate();
            response.sendRedirect("index.jsp");
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
