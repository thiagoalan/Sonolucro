package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface CartaoBean Representa cartões de crédito
 *
 * @since 05/08/2012
 * @author rodrigo
 * @version 1.0
 */
@Entity(name = "Cartao")
@Table(name = "cartao")
public class CartaoBeanImpl implements CartaoBean, Serializable
{

    private static final long serialVersionUID = -3197308724727899233L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true, length = 30)
    private String nome;
    @Column(nullable = true, length = 30)
    private String descricao;
    @Column(nullable = true)
    private Float limite;
    @Column(nullable = true)
    private Boolean status;
    @ManyToOne(targetEntity = UsuarioBeanImpl.class)
    @ForeignKey(name= "fk_cartao1")
    @JoinColumn(name = "idusuario", nullable = false)
    private UsuarioBean usuario;
    @Column(nullable = false, name = "diavencimento")
    private Integer diaVencimento;
    @Column(nullable = false, name = "diafechamento")
    private Integer diaFechamentoFatura;
    @Transient
    private Float saldo;
    @OneToMany(targetEntity = FaturaCartaoBeanImpl.class,
    cascade = CascadeType.ALL, 
    orphanRemoval= true)
    @JoinTable(joinColumns= {@JoinColumn(name = "idcartao")}, 
               inverseJoinColumns={@JoinColumn(name = "idfatura")})
    private Collection<FaturaCartaoBean> faturas = new ArrayList<FaturaCartaoBean>();

    public CartaoBeanImpl()
    {
        
    }
    
    public CartaoBeanImpl(CartaoBean cartao)
    {
        setId(cartao.getId()); 
        setNome(cartao.getNome()); 
        setDescricao(cartao.getDescricao()); 
        setLimite(cartao.getLimite()); 
        setStatus(cartao.getStatus()); 
        setUsuario(cartao.getUsuario()); 
        setDiaVencimento(cartao.getDiaVencimento());
        setDiaFechamentoFatura(cartao.getDiaFechamentoFatura());
        setSaldo(cartao.getSaldo());
        setFaturas(cartao.getFaturas()); 
    }
    
    public CartaoBeanImpl(String nome, String descricao, Float limite,
            Boolean status, UsuarioBean usuario, Integer diaVencimento,
            Integer diaFechamentoFatura, Float saldo, Collection<FaturaCartaoBean> faturas)
    {
        setNome(nome); 
        setDescricao(descricao); 
        setLimite(limite); 
        setStatus(status); 
        setUsuario(usuario); 
        setDiaVencimento(diaVencimento);
        setDiaFechamentoFatura(diaFechamentoFatura);
        setSaldo(saldo); 
        setFaturas(faturas); 
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
    public Float getLimite()
    {
        return limite;
    }

    @Override
    public void setLimite(Float limite)
    {
        this.limite = ((limite > 0) ? limite : limite * -1);
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
    public Integer getDiaVencimento()
    {
        return diaVencimento;
    }

    @Override
    public void setDiaVencimento(Integer diaVencimento)
    {
        this.diaVencimento = (diaVencimento > 0 && diaVencimento < 25) ? diaVencimento : 1;
    }

    @Override
    public Float getSaldo()
    {
        return saldo;
    }

    @Override
    public void setSaldo(Float saldo)
    {
        this.saldo = saldo;
    }
    
    @Override
    public Integer getDiaFechamentoFatura()
    {
        return diaFechamentoFatura;
    }

    @Override
    public void setDiaFechamentoFatura(Integer diaFechamentoFatura)
    {
        this.diaFechamentoFatura = (diaFechamentoFatura > 0 && diaFechamentoFatura < 25) ? diaFechamentoFatura : 1;
    }

    @Override
    public Collection<FaturaCartaoBean> getFaturas()
    {
        return faturas;
    }

    @Override
    public void setFaturas(Collection<FaturaCartaoBean> faturas)
    {
        this.faturas = faturas;
    }

    @Override
    public String toString()
    {
        return getNome() + " " + getDescricao();
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CartaoBeanImpl other = (CartaoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
            return false;
        if ((this.descricao == null) ? (other.descricao != null) : !this.descricao.equals(other.descricao))
            return false;
        return true;
    }
    
    
}
