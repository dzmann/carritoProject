/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Compras;
import entities.Comprasfinales;
import java.util.List;
import javax.ejb.Local;


@Local
public interface RealizarCompraLocal {
    
     public void finalizarCompra(List<Compras> carrito);
     
      public String generarIDCompra();
      
      public String generarCadena();
      
      public void borrarCarrito(String name);
      
      public List<Comprasfinales> obtenerHistorial(String user);
      
      
    
}
