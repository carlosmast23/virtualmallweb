/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = Usuario.NamedQueryFindById, query = "SELECT u FROM Usuario u WHERE u.nick = ?1"),
    @NamedQuery(name = "Usuario.findByNick", query = "SELECT u FROM Usuario u WHERE u.nick = :nick"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByFechaCreacionRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaCreacionRegistro = :fechaCreacionRegistro")})
public class Usuario implements Serializable {
    
    public static final String NamedQueryFindById="Usuario.findById";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 120)
    @Column(name = "NICK")
    private String nick;
    @Size(max = 1024)
    @Column(name = "CLAVE")
    private String clave;
    @Size(max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionRegistro;
    @OneToMany(mappedBy = "usuario",cascade = {})
    private List<Proveedor> proveedorList;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nick;
    }
    
    /**
     * =========================================================================
     *                  DATOS ADICIONALES
     * =========================================================================
     */
    
    public enum PerfilEnum
    {
        /**
         * Perfil para ingresar como proveedor al sistema
         */
        PROVEEDOR("p","Proveedor"),
        /**
         * Perfil para ingresar como administrador 
         */
        ADMINISTRADOR("a","Adminstrador"),
        /**
         * Clientes que quiere guardar su perfil de busquedas
         */
        CLIENTE("c","Cliente"),
        /**
         *  Personas que se encargan de asignar el flujo de las categorias a las busquedas 
         */
        CONTROLADOR("o","Controlador");

        private PerfilEnum(String letra, String nombre) {
            this.letra = letra;
            this.nombre = nombre;
        }
        
        
        
        private String letra;
        private String nombre;

        public String getLetra() {
            return letra;
        }

        public String getNombre() {
            return nombre;
        }
        
        
        
        public static PerfilEnum getByLetra(String letra)
        {
            for (PerfilEnum value : PerfilEnum.values()) {
                if(value.getLetra().equals(letra))
                {
                    return value;
                }
            }
            return null;
        }
    }
        
}
