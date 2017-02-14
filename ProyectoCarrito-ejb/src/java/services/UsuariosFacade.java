/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "ProyectoCarrito-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Usuarios buscarUsuario(String user) {
        
    
        Query q = em.createNamedQuery("Usuarios.findByNombreUsuario");
         q.setParameter("nombreUsuario", user);
         return (Usuarios)q.getSingleResult();
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
}

    