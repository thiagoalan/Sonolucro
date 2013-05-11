package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.bean.api.ObjetivoValorGuardadoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.ForeignKey;

/**
 * @author Rodrigo Romero
 * @version 1.0
 * @since 03/03/2013
 */
@Entity(name = "Objetivo")
@Table(name = "objetivo")
public class ObjetivoBeanImpl implements ObjetivoBean, Serializable
{
    private static final long serialVersionUID = 7350932987708446336L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "titulo", length = 35, nullable = false)
    private String titulo;
    @Lob
    @Column(name = "descricao", length = 255, nullable = true)
    private String descricao;
    @Temporal(TemporalType.DATE)
    @Column(name = "datainicialprevista", nullable = true)
    private Date dataInicialPrevista;
    @Temporal(TemporalType.DATE)
    @Column(name = "datafinalprevista", nullable = true)
    private Date dataFinalPrevista;
    @Transient
    private Date dataFinalEfetiva;  
    @Column(name = "valortotal", nullable = false)
    private Float valorTotal;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, targetEntity= ObjetivoValorGuardadoBeanImpl.class, fetch= FetchType.EAGER)
    @JoinTable(joinColumns={@JoinColumn(name = "idobjetivo")}, inverseJoinColumns={@JoinColumn(name = "idvalorobjetivo")})
    private Collection<ObjetivoValorGuardadoBean> valoresGuardados = new HashSet<ObjetivoValorGuardadoBean>(); 
    @Transient
    private Float metaValorDia;
    @Transient
    private Float metaValorMes;
    @Column(name = "status", nullable = false)
    private Boolean status;
    @ManyToOne(targetEntity = UsuarioBeanImpl.class, optional=false)
    @JoinColumn(name = "idusuario", nullable=false)
    @ForeignKey(name = "fk_objetivo1")
    private UsuarioBean usuario;

    public ObjetivoBeanImpl()
    {
    }

    public ObjetivoBeanImpl(ObjetivoBean objetivo)
    {
        setId(objetivo.getId());
        setTitulo(objetivo.getTitulo());
        setDescricao(objetivo.getDescricao());
        setDataInicialPrevista(objetivo.getDataInicialPrevista());
        setDataFinalPrevista(objetivo.getDataFinalPrevista());
        setDataFinalEfetiva(objetivo.getDataFinalEfetiva()); 
        setValorTotal(objetivo.getValorTotal());
        setValoresGuardados(objetivo.getValoresGuardados()); 
        setMetaValorDia(objetivo.getMetaValorDia());
        setMetaValorMes(objetivo.getMetaValorMes());
        setUsuario(objetivo.getUsuario());
        setStatus(objetivo.getStatus()); 
    }

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public String getTitulo()
    {
        return titulo;
    }

    @Override
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    @Override
    public String getDescricao()
    {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public Date getDataInicialPrevista()
    {
        return dataInicialPrevista;
    }

    @Override
    public void setDataInicialPrevista(Date dataInicialPrevista)
    {
        this.dataInicialPrevista = dataInicialPrevista;
    }

    @Override
    public Date getDataFinalPrevista()
    {
        return dataFinalPrevista;
    }

    @Override
    public void setDataFinalPrevista(Date dataFinalPrevista)
    {
        this.dataFinalPrevista = dataFinalPrevista;

    }

    @Override
    public Date getDataFinalEfetiva()
    {
        return dataFinalEfetiva;
    }

    @Override
    public void setDataFinalEfetiva(Date dataFinalEfetiva)
    {
        this.dataFinalEfetiva = dataFinalEfetiva;
    }
    
    @Override
    public Float getValorTotal()
    {
        return valorTotal;
    }

    @Override
    public void setValorTotal(Float valorTotal)
    {
        this.valorTotal = (valorTotal == null)? 0.0f : valorTotal;
    }

    @Override
    public Float getMetaValorDia()
    {
        return metaValorDia;
    }

    @Override
    public void setMetaValorDia(Float metaValorDia)
    {
        this.metaValorDia = (metaValorDia == null)? 0.0f : metaValorDia;
    }

    @Override
    public Float getMetaValorMes()
    {
        return metaValorMes;
    }

    @Override
    public void setMetaValorMes(Float metaValorMes)
    {
        this.metaValorMes = (metaValorMes == null)? 0.0f : metaValorMes;
    }

    @Override
    public Boolean getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    @Override
    public UsuarioBean getUsuario()
    {
        return usuario;
    }

    @Override
    public void setUsuario(UsuarioBean usuario)
    {
        this.usuario = usuario;
    }

    @Override
    public Collection<ObjetivoValorGuardadoBean> getValoresGuardados()
    {
        return valoresGuardados;
    }

    @Override
    public void setValoresGuardados(Collection<ObjetivoValorGuardadoBean> valoresGuardados)
    {
        this.valoresGuardados = valoresGuardados;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ObjetivoBeanImpl other = (ObjetivoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
}
