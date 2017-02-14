/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Usuarios;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author user
 */
@Stateless
public class ValidarLogin implements ValidarLoginLocal {

    @EJB
    private UsuariosFacadeLocal usuariosFacade;
    
    

    

    @Override
    public boolean validarUsuario(String user, String pass) {
        boolean check=false;
        List<Usuarios> lista = usuariosFacade.findAll();
        for(Usuarios u : lista){
            if(u.getNombreUsuario().equals(user) && u.getPassword().equals(pass)){
                check=true;
            }
        }
        return check;
    }
}
