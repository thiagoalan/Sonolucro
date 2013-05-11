package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import com.financeiro.sonolucro.bean.api.PromocaoBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface EmpresaBean Representa as empresas
 * cadastradas no sonolucro, indicada nos indicadores de oportunidades
 *
 * @since 29/11/2012
 * @author rodrigo
 * @version 1.0
 *
 */
@Entity(name = "Empresa")
@Table(name = "empresa")
public class EmpresaBeanImpl implements EmpresaBean, Serializable
{

    public static final Integer TIPO_VAREJISTA = 1;
    public static final Integer TIPO_BANCARIA = 2;
    private static final long serialVersionUID = 7336780309440974868L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 30)
    private String nomeFantasia;
    @Column(nullable = false, length = 15)
    private String cnpj;
    @Column(nullable = false, length = 30)
    private Integer tipo;
    @Column(nullable = false)
    private Boolean status;
    @Column(name = "empresa_cliente", nullable = false)
    private Boolean eCliente;
    @Column(nullable = true, length = 30)
    private String telefone;
    @Column(nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(targetEntity = PromocaoBeanImpl.class, cascade = CascadeType.ALL)
    private Collection<PromocaoBean> promocoes = new ArrayList<PromocaoBean>();
    @ManyToOne(targetEntity = CidadeBeanImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idcidade", nullable = false)
    @ForeignKey(name = "fk_empresa1")
    private CidadeBean cidade;
    @ManyToMany(targetEntity = ResponsavelEmpresaBeanImpl.class, fetch = FetchType.EAGER)
    @JoinTable(name = "empresa_responsavel_empresa",
    joinColumns =
    @JoinColumn(name = "idempresa"), inverseJoinColumns =
    @JoinColumn(name = "idresponsavelempresa"))
    private Collection<PessoaBean> responsaveisPelaEmpresa;
    @OneToMany(targetEntity = LocalidadeComercialBeanImpl.class, cascade= CascadeType.ALL)
    private Collection<LocalidadeComercialBean> localidadesComercial = new ArrayList<LocalidadeComercialBean>();
    @Column(name = "email", nullable = true)
    private String email;
    @Column(name = "descricao", nullable = true, length = 200)
    private String descricao; 
    @Column(name = "site", nullable = true, length = 50)
    private String site; 

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
    public String getNomeFantasia()
    {
        return nomeFantasia;
    }

    @Override
    public void setNomeFantasia(String nomeFantasia)
    {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String getCnpj()
    {
        return cnpj;
    }

    @Override
    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    @Override
    public Integer getTipo()
    {
        return tipo;
    }

    @Override
    public void setTipo(Integer tipo)
    {
        this.tipo = tipo;
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
    public Boolean getECliente()
    {
        return eCliente;
    }

    @Override
    public void setECliente(Boolean eCliente)
    {
        this.eCliente = eCliente;
    }

    @Override
    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    @Override
    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public Collection<PromocaoBean> getPromocoes()
    {
        return promocoes;
    }

    @Override
    public void setPromocoes(Collection<PromocaoBean> promocoes)
    {
        this.promocoes = promocoes;
    }

    @Override
    public String getTelefone()
    {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    @Override
    public CidadeBean getCidade()
    {
        return cidade;
    }

    @Override
    public void setCidade(CidadeBean cidade)
    {
        this.cidade = cidade;
    }

    @Override
    public Collection<PessoaBean> getResponsaveisPelaEmpresa()
    {
        return responsaveisPelaEmpresa;
    }

    @Override
    public void setResponsaveisPelaEmpresa(Collection<PessoaBean> responsaveisPelaEmpresa)
    {
        this.responsaveisPelaEmpresa = responsaveisPelaEmpresa;
    }

    @Override
    public Collection<LocalidadeComercialBean> getLocalidadesComercial()
    {
        return localidadesComercial;
    }

    @Override
    public void setLocalidadesComercial(Collection<LocalidadeComercialBean> localidadesComercial)
    {
        this.localidadesComercial = localidadesComercial;
    }

    @Override
    public String getEmail()
    {
        return email;
    }

    @Override
    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getSite()
    {
        return site;
    }

    @Override
    public void setSite(String site)
    {
        this.site = site;
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
        final EmpresaBeanImpl other = (EmpresaBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.cnpj == null) ? (other.cnpj != null) : !this.cnpj.equals(other.cnpj))
            return false;
        return true;
    }
     
}
