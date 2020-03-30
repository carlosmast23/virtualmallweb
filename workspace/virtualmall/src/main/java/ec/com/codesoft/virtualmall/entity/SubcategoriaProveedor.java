/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CARLOS_CODESOFT
 */
@Entity
@Table(name = "subcategoria_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubcategoriaProveedor.findAll", query = "SELECT s FROM SubcategoriaProveedor s"),
    @NamedQuery(name = "SubcategoriaProveedor.findById", query = "SELECT s FROM SubcategoriaProveedor s WHERE s.id = :id"),
    @NamedQuery(name = "SubcategoriaProveedor.findByEstado", query = "SELECT s FROM SubcategoriaProveedor s WHERE s.estado = :estado"),
    @NamedQuery(name = "SubcategoriaProveedor.findByFechaCreacionRegistro", query = "SELECT s FROM SubcategoriaProveedor s WHERE s.fechaCreacionRegistro = :fechaCreacionRegistro")})
public class SubcategoriaProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionRegistro;
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Proveedor proveedorId;
    @JoinColumn(name = "SUBCATEGORIA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Subcategoria subcategoriaId;

    public SubcategoriaProveedor() {
    }

    public SubcategoriaProveedor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCreacionRegistro() {
        return fechaCreacionRegistro;
    }

    public void setFechaCreacionRegistro(Date fechaCreacionRegistro) {
        this.fechaCreacionRegistro = fechaCreacionRegistro;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Subcategoria getSubcategoriaId() {
        return subcategoriaId;
    }

    public void setSubcategoriaId(Subcategoria subcategoriaId) {
        this.subcategoriaId = subcategoriaId;
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
        if (!(object instanceof SubcategoriaProveedor)) {
            return false;
        }
        SubcategoriaProveedor other = (SubcategoriaProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.codesoft.virtualmall.entity.SubcategoriaProveedor[ id=" + id + " ]";
    }
    
}
