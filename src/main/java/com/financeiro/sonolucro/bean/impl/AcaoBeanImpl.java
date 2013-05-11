
package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.AcaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rodrigo Romero 
 * @since 23/03/2013
 * @version 1.0
 */

//@Entity(name = "Acao")
//@Table(name = "acao")
public class AcaoBeanImpl //implements AcaoBean, Serializable
{
    /*
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Integer codigo; 
    @Column(nullable = false, length = 10)
    private String sigla; 
    @Column(nullable = false, length = 1)
    private Character origem;
    @Column(nullable = false)
    private Float quantidade;
    @Column(nullable = false)
    private Float valorUnitario; 
    @Column(nullable = false)
    private Float valorDeCompra;
    @Column(nullable = true)
    private Float valorDeVenda; 
    @Column(nullable = false)
    private Float emolumentos; 
    @Column(nullable = false)
    private Float impostoDeRenda; 
    @Transient
    private Float custoDaAquisicao; 
    @Column(nullable = true, length = 50)
    private String descricao; 
    @ManyToOne(targetEntity=UsuarioBeanImpl.class)
    @JoinColumn(name = "idusuario", nullable=false)
    private UsuarioBean usuario; 

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
    public Integer getCodigo()
    {
        return codigo;
    }

    @Override
    public void setCodigo(Integer codigo)
    {
        this.codigo = codigo;
    }

    @Override
    public String getSigla()
    {
        return sigla;
    }

    @Override
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }

    @Override
    public Character getOrigem()
    {
        return origem;
    }

    @Override
    public void setOrigem(Character origem)
    {
        this.origem = origem;
    }

    @Override
    public Float getQuantidade()
    {
        return quantidade;
    }

    @Override
    public void setQuantidade(Float quantidade)
    {
        this.quantidade = quantidade;
    }

    @Override
    public Float getValorUnitario()
    {
        return valorUnitario;
    }

    @Override
    public void setValorUnitario(Float valorUnitario)
    {
        this.valorUnitario = valorUnitario;
    }
    
    @Override
    public Float getValorDeCompra()
    {
        return valorDeCompra;
    }

    @Override
    public void setValorDeCompra(Float valorDeCompra)
    {
        this.valorDeCompra = valorDeCompra;
    }

    @Override
    public Float getValorDeVenda()
    {
        return valorDeVenda;
    }

    @Override
    public void setValorDeVenda(Float valorDeVenda)
    {
        this.valorDeVenda = valorDeVenda;
    }

    @Override
    public Float getEmolumentos()
    {
        return emolumentos;
    }

    @Override
    public void setEmolumentos(Float emolumentos)
    {
        this.emolumentos = emolumentos;
    }

    @Override
    public Float getCustoDaAquisicao()
    {
        return custoDaAquisicao;
    }

    @Override
    public void setCustoDaAquisicao(Float custoDaAquisicao)
    {
        this.custoDaAquisicao = custoDaAquisicao;
    }

    @Override
    public Float getImpostoDeRenda()
    {
        return impostoDeRenda;
    }

    @Override
    public void setImpostoDeRenda(Float impostoDeRenda)
    {
        this.impostoDeRenda = impostoDeRenda;
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
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        hash = 37 * hash + (this.sigla != null ? this.sigla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AcaoBeanImpl other = (AcaoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo)))
            return false;
        return true;
    }
  
    */
}
