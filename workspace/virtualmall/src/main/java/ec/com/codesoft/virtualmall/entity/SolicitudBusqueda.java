/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.codesoft.virtualmall.entity;

import ec.com.codesoft.virtualmall.enumerador.GeneralEnumEstado;
import ec.com.codesoft.virtualmall.util.UtilidadesFechas;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "solicitudBusqueda")
    private List<SubcategoriaBusqueda> subcategoriaBusquedas;

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

    public List<SubcategoriaBusqueda> getSubcategoriaBusquedas() {
        return subcategoriaBusquedas;
    }

    public void setSubcategoriaBusquedas(List<SubcategoriaBusqueda> subcategoriaBusquedas) {
        this.subcategoriaBusquedas = subcategoriaBusquedas;
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
     * DATOS ADICIONALES
     * ========================================================================
     */
    public void addSubCategoria(Subcategoria subCategoria)
    {
        if(subcategoriaBusquedas==null)
        {
            subcategoriaBusquedas=new ArrayList<SubcategoriaBusqueda>();
        }
        
        SubcategoriaBusqueda dato=new SubcategoriaBusqueda();
        dato.setEstado(GeneralEnumEstado.ACTIVO.getEstado());
        dato.setFechaCreacionRegistro(UtilidadesFechas.getFechaHoyUtil());
        dato.setSolicitudBusqueda(this);
        dato.setSubcategoria(subCategoria);
        subcategoriaBusquedas.add(dato);
    }
    
    public void addAllCategoria(List<Subcategoria> subcategorias)
    {
        for (Subcategoria subcategoria : subcategorias) {
            addSubCategoria(subcategoria);
        }
    }
    
    public void addAllCategoria(Subcategoria[] subcategorias)
    {
        for (Subcategoria subcategoria : subcategorias) {
            addSubCategoria(subcategoria);
        }
    }
    
    
    public List<SubcategoriaBusqueda> obtenerSubCategoriaActivos() {
        List<SubcategoriaBusqueda> resultado = new ArrayList<SubcategoriaBusqueda>();
        if (subcategoriaBusquedas != null) {
            for (SubcategoriaBusqueda subcategoria : subcategoriaBusquedas) {
                if (subcategoria.getEstadoEnum().equals(GeneralEnumEstado.ACTIVO)) 
                {
                    resultado.add(subcategoria);
                }
            }
        }
        return resultado;
    }

    public EstadoEnum getEstadoEnum() {
        return EstadoEnum.getByLetra(estado);
    }

    public void setEstadoEnum(EstadoEnum estadoEnum) {
        this.estado = estadoEnum.letra;
    }

    public enum EstadoEnum {
        /**
         * 1.- Generada: Estado inicial que pasa toda busqueda al momento de
         * generar por el cliente
         */
        GENERADA("g", "Generada"),
        /**
         * 2.- Verificada: Segundo estado que se ingresa despues que el
         * verificador aprueba y categoriza la busqueda
         */
        VERIFICADA("v", "Verificada"),
        /**
         * 3.- Estado cuando ya se mando los presupuestos al cliente para que
         * pueda revisar
         */
        RESPONDIDA("r", "Respondidad"),
        /**
         * 4.- Estado que se ingresa cuando el cliente por fin selecciona un
         * presupuesto
         */
        FINALIZADA("f", "Finalizada"),
        /**
         *
         */
        ELIMINADA("e", "Eliminado"),
        /**
         * "
         * Estado cuando una busqueda incumple alguna norma de la pagina web
         */
        SANCIONADA("s", "Sancionado");

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

        public static EstadoEnum getByLetra(String letra) {
            for (EstadoEnum object : EstadoEnum.values()) {
                if (object.getLetra().equals(letra)) {
                    return object;
                }
            }
            return null;
        }

    }

}
