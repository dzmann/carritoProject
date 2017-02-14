/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Compras;
import entities.Comprasfinales;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class RealizarCompra implements RealizarCompraLocal {

    @EJB
    private ComprasfinalesFacadeLocal comprasfinalesFacade;

    @EJB
    private ComprasFacadeLocal comprasFacade;
    
    
    
    @Override
    public void finalizarCompra(List<Compras> carrito){
        String ID = generarIDCompra();
        String usuario = carrito.get(0).getIdUsuario();
        
        for(Compras item : carrito){
            Comprasfinales compra = new Comprasfinales(null, ID, item.getIdUsuario(), item.getIdArticulo(), item.getCantidad());

            
            comprasfinalesFacade.create(compra);
        }
        borrarCarrito(usuario);
    }

    @Override
    public String generarIDCompra() {
        List<Comprasfinales> listarTodas = comprasfinalesFacade.findAll();
        String idGenerado = generarCadena();
        if (!listarTodas.isEmpty()) {
            for (Comprasfinales item : listarTodas) {
                while(item.getIdCompra().equals(idGenerado)){
                   idGenerado = generarCadena();
                }
            }
        }else{
            idGenerado = generarCadena();
        }
        return idGenerado;
    }
    
    

    @Override
    public String generarCadena() {
        char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    
    @Override
    public void borrarCarrito(String user){
        List<Compras> lista = comprasFacade.findByUser(user);
        for(Compras item : lista){
            comprasFacade.remove(item);
        }
    }
    

    
    @Override
    public List<Comprasfinales> obtenerHistorial(String user){
        List<Comprasfinales> lista = comprasfinalesFacade.findByUser(user);
        return lista;
    }
    
    

}
