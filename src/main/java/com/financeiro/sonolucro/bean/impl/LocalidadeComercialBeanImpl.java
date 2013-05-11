package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.CidadeBean;
import com.financeiro.sonolucro.bean.api.EmpresaBean;
import com.financeiro.sonolucro.bean.api.LocalidadeComercialBean;
import com.financeiro.sonolucro.bean.api.PessoaBean;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface localidade comercial bean. Representa as
 * lojas, agencias "Localidades" onde as empresas realizam suas atividades
 * comerciais.
 *
 * @author rodrigo
 * @since 17/11/2012
 * @version 1.0.0
 */
@Entity(name = "LocalidadeComercial")
@Table(name = "localidade_comercial")
public class LocalidadeComercialBeanImpl implements LocalidadeComercialBean, Serializable
{
    private static final long serialVersionUID = 5316253976998623161L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "nome", nullable = false, length = 50)
    private String nome; 
    @Column(nullable = false, length = 15)
    private String longitude;
    @Column(nullable = false, length = 15)
    private String latitude;
    @ManyToOne(targetEntity = CidadeBeanImpl.class)
    @JoinColumn(name = "cidade", nullable = false)
    @ForeignKey(name = "fk_localidadecomercial1")
    private CidadeBean cidade;
    @Column(nullable = false, length = 30)
    private String bairro;
    @Column(nullable = false, length = 30)
    private String rua;
    @Column(nullable = true, length = 10)
    private String numero;
    @Column(nullable = true, length = 20)
    private String complemento;
    @Column(nullable = true, length = 12)
    private String cep;
    @Column(nullable = true, length = 30)
    private String telefone;
    @Column(nullable = true, length = 30)
    private String site;
    @Column(nullable = true, length = 30)
    private String email;
    @ManyToMany(targetEntity = ResponsavelEmpresaBeanImpl.class, fetch = FetchType.LAZY)
    @JoinTable(name = "localidade_comercial_responsavel_localidade",
    joinColumns =
    @JoinColumn(name = "idlocalidadecomercial"), inverseJoinColumns =
    @JoinColumn(name = "idresponsavellocalidade"))
    private Collection<PessoaBean> responsaveisLocalidadesComerciais;
    @ManyToOne(targetEntity = EmpresaBeanImpl.class, fetch = FetchType.EAGER)
    private EmpresaBean empresa;
    @Column(name = "descricao", nullable = true, length = 200)
    private String descricao; 
    
    
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
    public String getNome()
    {
        return nome;
    }

    @Override
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Override
    public String getLongitude()
    {
        return longitude;
    }

    @Override
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String getLatitude()
    {
        return latitude;
    }

    @Override
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
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
    public String getBairro()
    {
        return bairro;
    }

    @Override
    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    @Override
    public String getRua()
    {
        return rua;
    }

    @Override
    public void setRua(String rua)
    {
        this.rua = rua;
    }

    @Override
    public String getNumero()
    {
        return numero;
    }

    @Override
    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    @Override
    public String getComplemento()
    {
        return complemento;
    }

    @Override
    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    @Override
    public String getCep()
    {
        return cep;
    }

    @Override
    public void setCep(String cep)
    {
        this.cep = cep;
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
    public String toString()
    {
        return getNome(); 
    }

    @Override
    public Collection<PessoaBean> getResponsaveisLocalidadesComerciais()
    {
        return responsaveisLocalidadesComerciais; 
    }

    @Override
    public void setResponsavelLocalidadeComercial(Collection<PessoaBean> responsaveisLocalidadesComerciais)
    {
        this.responsaveisLocalidadesComerciais = responsaveisLocalidadesComerciais; 
    }
        
}
