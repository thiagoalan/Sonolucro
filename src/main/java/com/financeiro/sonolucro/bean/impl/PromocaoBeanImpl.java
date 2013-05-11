package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.PromocaoBean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface PromocaaoBean Represanta as promoções
 * Anunciadas pelas empresas.
 *
 * @author rodrigo
 * @since 17/11/2012
 * @version 1.0.0
 */
@Entity(name = "Promocao")
@Table(name = "promocao")
public class PromocaoBeanImpl implements PromocaoBean, Serializable
{
    private static final long serialVersionUID = 5344431870734210843L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "titulo", length = 30)
    private String titulo;
    private Integer numeroDaPromocao;
    @Column(name = "descricao", length = 100)
    private String descricao;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataInicial;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataFinal;
    @ManyToOne(targetEntity = EmpresaBeanImpl.class)
    @JoinColumn(name = "idempresa")
    @ForeignKey(name = "fk_promocao1")
    private EmpresaBean empresa;

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
    public Integer getNumeroDaPromocao()
    {
        return numeroDaPromocao;
    }

    @Override
    public void setNumeroDaPromocao(Integer numeroDaPromocao)
    {
        this.numeroDaPromocao = numeroDaPromocao;
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
    public Date getDataInicial()
    {
        return dataInicial;
    }

    @Override
    public void setDataInicial(Date dataInicial)
    {
        this.dataInicial = dataInicial;
    }

    @Override
    public Date getDataFinal()
    {
        return dataFinal;
    }

    @Override
    public void setDataFinal(Date dataFinal)
    {
        this.dataFinal = dataFinal;
    }

    @Override
    public EmpresaBean getEmpresa()
    {
        return empresa; 
    }

    @Override
    public void setEmpresa(EmpresaBean empresa)
    {
        this.empresa = empresa;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PromocaoBeanImpl other = (PromocaoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.titulo == null) ? (other.titulo != null) : !this.titulo.equals(other.titulo))
            return false;
        if (this.numeroDaPromocao != other.numeroDaPromocao && (this.numeroDaPromocao == null || !this.numeroDaPromocao.equals(other.numeroDaPromocao)))
            return false;
        if (this.dataInicial != other.dataInicial && (this.dataInicial == null || !this.dataInicial.equals(other.dataInicial)))
            return false;
        if (this.dataFinal != other.dataFinal && (this.dataFinal == null || !this.dataFinal.equals(other.dataFinal)))
            return false;
        if (this.empresa != other.empresa && (this.empresa == null || !this.empresa.equals(other.empresa)))
            return false;
        return true;
    }
    
    
}
