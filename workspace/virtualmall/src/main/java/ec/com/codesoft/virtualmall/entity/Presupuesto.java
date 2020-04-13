/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({})
public class Presupuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 5000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 512)
    @Column(name = "FOTO1")
    private String foto1;
    @Size(max = 512)
    @Column(name = "FOTO2")
    private String foto2;
    @Size(max = 512)
    @Column(name = "FOTO3")
    private String foto3;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Size(max = 1)
    @Column(name = "NUEVO_O_USADO")
    private String nuevoOUsado;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionRegistro;
    @JoinColumn(name = "PROVEEDOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Proveedor proveedor;
    @JoinColumn(name = "SOLICITUD_BUSQUEDA_ID", referencedColumnName = "ID")
    @ManyToOne
    private SolicitudBusqueda solicitudBusquedaId;

    public Presupuesto() {
    }

    public Presupuesto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNuevoOUsado() {
        return nuevoOUsado;
    }

    public void setNuevoOUsado(String nuevoOUsado) {
        this.nuevoOUsado = nuevoOUsado;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public SolicitudBusqueda getSolicitudBusquedaId() {
        return solicitudBusquedaId;
    }

    public void setSolicitudBusquedaId(SolicitudBusqueda solicitudBusquedaId) {
        this.solicitudBusquedaId = solicitudBusquedaId;
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
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.codesoft.virtualmall.entity.Presupuesto[ id=" + id + " ]";
    }
    
}
