/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CARLOS_CODESOFT
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findById", query = "SELECT p FROM Proveedor p WHERE p.id = :id"),
    @NamedQuery(name = "Proveedor.findByIdentificacion", query = "SELECT p FROM Proveedor p WHERE p.identificacion = :identificacion"),
    @NamedQuery(name = "Proveedor.findByNombres", query = "SELECT p FROM Proveedor p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Proveedor.findByApellidos", query = "SELECT p FROM Proveedor p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedor.findByCorreo", query = "SELECT p FROM Proveedor p WHERE p.correo = :correo"),
    @NamedQuery(name = "Proveedor.findByWhatsapp", query = "SELECT p FROM Proveedor p WHERE p.whatsapp = :whatsapp"),
    @NamedQuery(name = "Proveedor.findByTelefono2", query = "SELECT p FROM Proveedor p WHERE p.telefono2 = :telefono2"),
    @NamedQuery(name = "Proveedor.findByTelefono3", query = "SELECT p FROM Proveedor p WHERE p.telefono3 = :telefono3"),
    @NamedQuery(name = "Proveedor.findByNombreNegocio", query = "SELECT p FROM Proveedor p WHERE p.nombreNegocio = :nombreNegocio"),
    @NamedQuery(name = "Proveedor.findByDescripcionNegocio", query = "SELECT p FROM Proveedor p WHERE p.descripcionNegocio = :descripcionNegocio"),
    @NamedQuery(name = "Proveedor.findByValoresPagar", query = "SELECT p FROM Proveedor p WHERE p.valoresPagar = :valoresPagar"),
    @NamedQuery(name = "Proveedor.findByFechaCortePago", query = "SELECT p FROM Proveedor p WHERE p.fechaCortePago = :fechaCortePago"),
    @NamedQuery(name = "Proveedor.findByEstado", query = "SELECT p FROM Proveedor p WHERE p.estado = :estado"),
    @NamedQuery(name = "Proveedor.findByFechaCreacionRegistro", query = "SELECT p FROM Proveedor p WHERE p.fechaCreacionRegistro = :fechaCreacionRegistro")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 15)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 256)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 256)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 256)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 256)
    @Column(name = "CORREO")
    private String correo;
    @Size(max = 16)
    @Column(name = "WHATSAPP")
    private String whatsapp;
    @Size(max = 16)
    @Column(name = "TELEFONO2")
    private String telefono2;
    @Size(max = 16)
    @Column(name = "TELEFONO3")
    private String telefono3;
    @Size(max = 256)
    @Column(name = "NOMBRE_NEGOCIO")
    private String nombreNegocio;
    @Size(max = 1024)
    @Column(name = "DESCRIPCION_NEGOCIO")
    private String descripcionNegocio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORES_PAGAR")
    private BigDecimal valoresPagar;
    @Column(name = "FECHA_CORTE_PAGO")
    @Temporal(TemporalType.DATE)
    private Date fechaCortePago;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionRegistro;
    
    @OneToMany(mappedBy = "proveedorId")
    private List<Presupuesto> presupuestoList;
    @OneToMany(mappedBy = "proveedorId")
    private List<SubcategoriaBusqueda> subcategoriaBusquedaList;
    @OneToMany(mappedBy = "proveedorId")
    private List<SubcategoriaProveedor> subcategoriaProveedorList;
    
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario usuario;

    public Proveedor() {
    }

    public Proveedor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDescripcionNegocio() {
        return descripcionNegocio;
    }

    public void setDescripcionNegocio(String descripcionNegocio) {
        this.descripcionNegocio = descripcionNegocio;
    }

    public BigDecimal getValoresPagar() {
        return valoresPagar;
    }

    public void setValoresPagar(BigDecimal valoresPagar) {
        this.valoresPagar = valoresPagar;
    }

    public Date getFechaCortePago() {
        return fechaCortePago;
    }

    public void setFechaCortePago(Date fechaCortePago) {
        this.fechaCortePago = fechaCortePago;
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

    @XmlTransient
    public List<Presupuesto> getPresupuestoList() {
        return presupuestoList;
    }

    public void setPresupuestoList(List<Presupuesto> presupuestoList) {
        this.presupuestoList = presupuestoList;
    }

    @XmlTransient
    public List<SubcategoriaBusqueda> getSubcategoriaBusquedaList() {
        return subcategoriaBusquedaList;
    }

    public void setSubcategoriaBusquedaList(List<SubcategoriaBusqueda> subcategoriaBusquedaList) {
        this.subcategoriaBusquedaList = subcategoriaBusquedaList;
    }

    @XmlTransient
    public List<SubcategoriaProveedor> getSubcategoriaProveedorList() {
        return subcategoriaProveedorList;
    }

    public void setSubcategoriaProveedorList(List<SubcategoriaProveedor> subcategoriaProveedorList) {
        this.subcategoriaProveedorList = subcategoriaProveedorList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.codesoft.virtualmall.entity.Proveedor[ id=" + id + " ]";
    }
    
}
