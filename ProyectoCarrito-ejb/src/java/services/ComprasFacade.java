/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Compras;
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
public class ComprasFacade extends AbstractFacade<Compras> implements ComprasFacadeLocal {

    @PersistenceContext(unitName = "ProyectoCarrito-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComprasFacade() {
        super(Compras.class);
    }

    @Override
    public List<Compras> findByArtAndUser(String user, int idArt) {
        Query q = em.createNamedQuery("Compras.findByUserArticulo");
        q.setParameter("idUsuario", user);
        q.setParameter("idArticulo", idArt);
        return q.getResultList();
    }

    @Override
    public List<Compras> findByUser(String user) {
        Query q = em.createNamedQuery("Compras.findByIdUsuario");
        q.setParameter("idUsuario", user);
        return q.getResultList();
    }
    
    
    
}
