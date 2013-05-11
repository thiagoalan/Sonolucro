package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.GrupoBean;
import org.hibernate.annotations.ForeignKey;

@Entity(name = "Conta")
@Table(name = "conta")
public class ContaBeanImpl implements ContaBean, Serializable
{

    private static final long serialVersionUID = -8093339741686881351L;
    
    public static Integer TIPO_RECEITA = 2;
    public static Integer TIPO_DESPESA = 1; 
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true, length = 50)
    private String nome;
    @Column(nullable = true)
    private Integer tipo;
    @Column(nullable = true)
    private Boolean status;
    @ManyToOne(targetEntity = GrupoBeanImpl.class)
    @JoinColumn(name = "idgrupo", nullable = false)
    @ForeignKey(name = "fk_conta1")
    private GrupoBean grupo;
   
    
    public ContaBeanImpl()
    {
     
    }

    public ContaBeanImpl(ContaBean conta)
    {
        setId(conta.getId()); 
        setNome(conta.getNome());
        setTipo(conta.getTipo());
        setGrupo(conta.getGrupo());
        setStatus(conta.getStatus()); 
    }
    
    public ContaBeanImpl(String nome, Integer tipo, GrupoBean grupo, Boolean status)
    {
        setNome(nome);
        setTipo(tipo);
        setGrupo(grupo);
        setStatus(status); 
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
    public GrupoBean getGrupo()
    {
        return grupo;
    }

    @Override
    public void setGrupo(GrupoBean grupo)
    {
        this.grupo = grupo;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 71 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 71 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 71 * hash + (this.grupo != null ? this.grupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ContaBeanImpl other = (ContaBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }

    
    
}
