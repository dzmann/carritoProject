/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Comprasfinales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ComprasfinalesFacadeLocal {

    void create(Comprasfinales comprasfinales);

    void edit(Comprasfinales comprasfinales);

    void remove(Comprasfinales comprasfinales);

    Comprasfinales find(Object id);

    List<Comprasfinales> findAll();

    List<Comprasfinales> findRange(int[] range);
    
    List<Comprasfinales> findByUser(String name);
    
    public List<Comprasfinales> obtenerHistorialPorId(String id);
    
    int count();
    
}
