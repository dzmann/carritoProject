/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articulos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ArticulosFacadeLocal {

    void create(Articulos articulos);

    void edit(Articulos articulos);

    void remove(Articulos articulos);

    Articulos find(Object id);

    List<Articulos> findAll();

    List<Articulos> findRange(int[] range);
    
    List<Articulos> buscar(String busqueda);

    int count();
    
    List<Articulos> articulosPorCategoria(int i);
    
}
