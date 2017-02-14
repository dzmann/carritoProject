/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Comprasfinales;
import entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class ComprasfinalesFacade extends AbstractFacade<Comprasfinales> implements ComprasfinalesFacadeLocal {

    @PersistenceContext(unitName = "ProyectoCarrito-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprasfinalesFacade() {
        super(Comprasfinales.class);
    }

    @Override
    public List<Comprasfinales> findByUser(String name) {
        Query q = em.createNamedQuery("Comprasfinales.findByIdUsuario");
        q.setParameter("idUsuario", name);
        return q.getResultList();
    }

    @Override
    public List<Comprasfinales> obtenerHistorialPorId(String id) {
        Query q = em.createNamedQuery("Comprasfinales.findByIdCompra");
        q.setParameter("idCompra", id);
        return q.getResultList();
    }

}
