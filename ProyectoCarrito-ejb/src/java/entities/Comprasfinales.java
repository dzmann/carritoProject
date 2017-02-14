/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "comprasfinales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comprasfinales.findAll", query = "SELECT c FROM Comprasfinales c"),
    @NamedQuery(name = "Comprasfinales.findById", query = "SELECT c FROM Comprasfinales c WHERE c.id = :id"),
    @NamedQuery(name = "Comprasfinales.findByIdCompra", query = "SELECT c FROM Comprasfinales c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Comprasfinales.findByIdUsuario", query = "SELECT c FROM Comprasfinales c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Comprasfinales.findByIdArticulo", query = "SELECT c FROM Comprasfinales c WHERE c.idArticulo = :idArticulo"),
    @NamedQuery(name = "Comprasfinales.findByCantidad", query = "SELECT c FROM Comprasfinales c WHERE c.cantidad = :cantidad")})
public class Comprasfinales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_compra")
    private String idCompra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_usuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_articulo")
    private int idArticulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;

    public Comprasfinales() {
    }

    public Comprasfinales(Integer id) {
        this.id = id;
    }

    public Comprasfinales(Integer id, String idCompra, String idUsuario, int idArticulo, int cantidad) {
        this.id = id;
        this.idCompra = idCompra;
        this.idUsuario = idUsuario;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(String idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comprasfinales)) {
            return false;
        }
        Comprasfinales other = (Comprasfinales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comprasfinales[ id=" + id + " ]";
    }
    
}
