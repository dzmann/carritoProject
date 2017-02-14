/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Articulos;
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
public class ArticulosFacade extends AbstractFacade<Articulos> implements ArticulosFacadeLocal {

    @PersistenceContext(unitName = "ProyectoCarrito-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticulosFacade() {
        super(Articulos.class);
    }

    @Override
    public List<Articulos> articulosPorCategoria(int i) {

        Query q = em.createNamedQuery("Articulos.findByIdCategoria");
        q.setParameter("idCategoria", i);
        return q.getResultList();
    }

    @Override
    public List<Articulos> buscar(String busqueda) {
        Query q = em.createNamedQuery("Articulos.busquedaArticulos");
        q.setParameter("busqueda",'%' + busqueda.toLowerCase() + '%');
        return q.getResultList();
    }

}
