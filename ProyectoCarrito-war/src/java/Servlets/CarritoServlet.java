/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entities.Compras;
import entities.Usuarios;
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
import services.ComprasFacadeLocal;
import services.UsuariosFacadeLocal;

/**
 *
 * @author user
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/CarritoServlet"})
public class CarritoServlet extends HttpServlet {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;

    @EJB
    private ArticulosFacadeLocal articulosFacade;

    @EJB
    private ComprasFacadeLocal comprasFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CarritoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CarritoServlet at " + request.getContextPath() + "</h1>");
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

        String operacion = request.getParameter("operacion");
        int i = Integer.parseInt(request.getParameter("valor"));
        Usuarios userLogueado = (Usuarios) request.getSession().getAttribute("usuarioAutenticado");
        int sumarArt = 0;

        List<Compras> resultCompras = comprasFacade.findByArtAndUser(userLogueado.getNombreUsuario(), i);
        response.setContentType("text/html;charset=UTF-8");

        if (operacion.equals("agregar")) {

            if (resultCompras.isEmpty()) {
                Compras articuloCompra = new Compras(null, userLogueado.getNombreUsuario(), i, 1);

                comprasFacade.create(articuloCompra);

            } else {

                Compras modificarCompra = resultCompras.get(0);
                int cant = modificarCompra.getCantidad() + 1;
                modificarCompra.setCantidad(cant);
                comprasFacade.edit(modificarCompra);
            }

            List<Compras> carrito = comprasFacade.findByUser(userLogueado.getNombreUsuario());

            if (!carrito.isEmpty()) {

                for (Compras compra : carrito) {
                    sumarArt += compra.getCantidad();
                }

                request.getSession().setAttribute("Carrito", carrito);

            }
            request.getSession().setAttribute("CantidadCarrito", sumarArt);
        } else if (operacion.equals("borrar")) {
            Compras modificarCompra = resultCompras.get(0);
            if (modificarCompra.getCantidad() == 1) {
                comprasFacade.remove(modificarCompra);
            } else {
                int cant = modificarCompra.getCantidad() - 1;
                modificarCompra.setCantidad(cant);
                comprasFacade.edit(modificarCompra);
            }

            List<Compras> carrito = comprasFacade.findByUser(userLogueado.getNombreUsuario());
            for (Compras compra : carrito) {
                sumarArt += compra.getCantidad();
            }
            request.getSession().setAttribute("CantidadCarrito", sumarArt);
            request.getSession().setAttribute("Carrito", carrito);

        }else if (operacion.equals("eliminar")) {
            Compras modificarCompra = resultCompras.get(0);
          
                comprasFacade.remove(modificarCompra);
            

            List<Compras> carrito = comprasFacade.findByUser(userLogueado.getNombreUsuario());
            for (Compras compra : carrito) {
                sumarArt += compra.getCantidad();
                
            }
            request.getSession().setAttribute("CantidadCarrito", sumarArt);
            request.getSession().setAttribute("Carrito", carrito);

        }


    }

    @Override
    public String getServletInfo() {

        return "Short description";
    }// </editor-fold>

}
