/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "solicitud_busqueda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudBusqueda.findAll", query = "SELECT s FROM SolicitudBusqueda s"),
    @NamedQuery(name = "SolicitudBusqueda.findById", query = "SELECT s FROM SolicitudBusqueda s WHERE s.id = :id"),
    @NamedQuery(name = "SolicitudBusqueda.findByBusqueda", query = "SELECT s FROM SolicitudBusqueda s WHERE s.busqueda = :busqueda"),
    @NamedQuery(name = "SolicitudBusqueda.findByNombresCliente", query = "SELECT s FROM SolicitudBusqueda s WHERE s.nombresCliente = :nombresCliente"),
    @NamedQuery(name = "SolicitudBusqueda.findByWhatsappCliente", query = "SELECT s FROM SolicitudBusqueda s WHERE s.whatsappCliente = :whatsappCliente"),
    @NamedQuery(name = "SolicitudBusqueda.findByCorreoCliente", query = "SELECT s FROM SolicitudBusqueda s WHERE s.correoCliente = :correoCliente"),
    @NamedQuery(name = "SolicitudBusqueda.findByTiempoRespuestaMin", query = "SELECT s FROM SolicitudBusqueda s WHERE s.tiempoRespuestaMin = :tiempoRespuestaMin"),
    @NamedQuery(name = "SolicitudBusqueda.findByFechaHoraSolicitud", query = "SELECT s FROM SolicitudBusqueda s WHERE s.fechaHoraSolicitud = :fechaHoraSolicitud"),
    @NamedQuery(name = "SolicitudBusqueda.findByEstado", query = "SELECT s FROM SolicitudBusqueda s WHERE s.estado = :estado"),
    @NamedQuery(name = "SolicitudBusqueda.findByFechaCreacionRegistro", query = "SELECT s FROM SolicitudBusqueda s WHERE s.fechaCreacionRegistro = :fechaCreacionRegistro")})
public class SolicitudBusqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 2024)
    @Column(name = "BUSQUEDA")
    private String busqueda;
    @Size(max = 512)
    @Column(name = "NOMBRES_CLIENTE")
    private String nombresCliente;
    @Size(max = 16)
    @Column(name = "WHATSAPP_CLIENTE")
    private String whatsappCliente;
    @Size(max = 256)
    @Column(name = "CORREO_CLIENTE")
    private String correoCliente;
    @Column(name = "TIEMPO_RESPUESTA_MIN")
    private Integer tiempoRespuestaMin;
    @Column(name = "FECHA_HORA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSolicitud;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacionRegistro;
    @OneToMany(mappedBy = "solicitudBusquedaId")
    private List<Presupuesto> presupuestoList;

    public SolicitudBusqueda() {
    }

    public SolicitudBusqueda(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getWhatsappCliente() {
        return whatsappCliente;
    }

    public void setWhatsappCliente(String whatsappCliente) {
        this.whatsappCliente = whatsappCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public Integer getTiempoRespuestaMin() {
        return tiempoRespuestaMin;
    }

    public void setTiempoRespuestaMin(Integer tiempoRespuestaMin) {
        this.tiempoRespuestaMin = tiempoRespuestaMin;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitudBusqueda)) {
            return false;
        }
        SolicitudBusqueda other = (SolicitudBusqueda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return busqueda;
    }
    
    /**
     * ========================================================================
     *                         DATOS ADICIONALES
     * ========================================================================
     */
    
    public EstadoEnum getEstadoEnum() {
        return EstadoEnum.getByLetra(estado);
    }

    public void setEstadoEnum(EstadoEnum estadoEnum) {
        this.estado = estadoEnum.letra;
    }
    
    public enum EstadoEnum
    {
        /**
         * 1.- Generada:
         * Estado inicial que pasa toda busqueda al momento de generar por el cliente
         */
        GENERADA("g","Generada"),
        /**
         * 2.- Verificada:
         * Segundo estado que se ingresa despues que el verificador aprueba y categoriza la busqueda
         */
        VERIFICADA("v","Verificada"),
        /**
         * 3.- Estado cuando ya se mando los presupuestos al cliente para que pueda revisar
         */
        RESPONDIDA("r","Respondidad"),
        /**
         * 4.- Estado que se ingresa cuando el cliente por fin selecciona un presupuesto
         */
        FINALIZADA("f","Finalizada"),
        /**
         * 
         */
        ELIMINADA("e","Eliminado"),
        /**"
         * Estado cuando una busqueda incumple alguna norma de la pagina web
         */
        SANCIONADA("s","Sancionado");
        
        private String letra;
        private String nombre;

        private EstadoEnum(String letra, String nombre) {
            this.letra = letra;
            this.nombre = nombre;
        }

        
        public String getLetra() {
            return letra;
        }

        public String getNombre() {
            return nombre;
        }
        
        public static EstadoEnum getByLetra(String letra)
        {
            for (EstadoEnum object : EstadoEnum.values()) 
            {
                if(object.getLetra().equals(letra))
                {
                    return object;
                }
            }
            return null;
        }
        
    }
    
}
