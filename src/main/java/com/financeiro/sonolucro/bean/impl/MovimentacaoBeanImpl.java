package com.financeiro.sonolucro.bean.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;

/**
 * Implementação padrão da interface MovimentacaoBean. Representa as
 * movimentações financeiras realizadas pelo usuário, cada movimentação pertence
 * ao um tipo de conta, pode ou não estar veinculada ao um cartão de crédito.
 *
 * @author Rodrigo Romero
 * @since 05/08/2012
 * @version 1.0
 */
@Entity(name = "Movimentacao")
@Table(name = "movimentacao")
public class MovimentacaoBeanImpl implements MovimentacaoBean, Serializable, Comparable<MovimentacaoBean>
{

    private static final long serialVersionUID = 3074181638044191647L;
    
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "valor", nullable = false)
    private Float valor;
    @Column(name = "descricao", nullable = true, length = 50)
    private String descricao;
    @Column(nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataLancamento;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataVencimento;
    @Column(nullable = false)
    private Integer sequencia;
    @ManyToOne(targetEntity = ContaBeanImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "idconta")
    @ForeignKey(name = "fk_movimentacao1")
    private ContaBean conta;
    @ManyToOne(targetEntity = CartaoBeanImpl.class, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "idcartao", nullable = true)
    @ForeignKey(name = "fk_movimentacao2")
    private CartaoBean cartao;
    @Transient
    private Float debito;
    @Transient
    private Float credito;
    @Transient
    private Float saldo;

    public MovimentacaoBeanImpl()
    {
    }

    public MovimentacaoBeanImpl(MovimentacaoBean mov)
    {
        setId(mov.getId());
        setCartao(mov.getCartao());
        setConta(mov.getConta());
        setSaldo(mov.getSaldo());
        setCredito(mov.getCredito());
        setDebito(mov.getDebito());
        setSequencia(mov.getSequencia());
        setDescricao(mov.getDescricao());
        setDataVencimento(mov.getDataVencimento());
        setDataLancamento(mov.getDataLancamento());
        setValor(mov.getValor());

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
    public Date getDataLancamento()
    {
        return dataLancamento;
    }

    @Override
    public void setDataLancamento(Date dataLancamento)
    {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public Date getDataVencimento()
    {
        return dataVencimento;
    }

    @Override
    public void setDataVencimento(Date dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public Integer getSequencia()
    {
        return sequencia;
    }

    @Override
    public void setSequencia(Integer sequencia)
    {
        this.sequencia = ((sequencia > 500 || sequencia < 1) ? 500 : sequencia);
    }

    @Override
    public ContaBean getConta()
    {
        return conta;
    }

    @Override
    public void setConta(ContaBean conta)
    {
        this.conta = conta;
    }

    @Override
    public CartaoBean getCartao()
    {
        return cartao;
    }

    @Override
    public void setCartao(CartaoBean cartao)
    {
        this.cartao = cartao;
    }

    @Override
    public Float getDebito()
    {
        return debito;
    }

    @Override
    public void setDebito(Float debito)
    {
        this.debito = debito;
    }

    @Override
    public Float getCredito()
    {
        return credito;
    }

    @Override
    public void setCredito(Float credito)
    {
        this.credito = credito;
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
    public Float getValor()
    {
        return valor;
    }

    @Override
    public void setValor(Float valor)
    {
        this.valor = valor;
    }

    @Override
    public int compareTo(MovimentacaoBean mov)
    {
        if(this.dataVencimento.equals(mov.getDataVencimento()) 
                && this.sequencia.intValue() == mov.getSequencia().intValue())
            return 0; 
        else if(this.dataVencimento.before(mov.getDataVencimento()) 
                && this.sequencia.intValue() < mov.getSequencia().intValue())
            return -1; 
        else
            return 1;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 47 * hash + (this.dataVencimento != null ? this.dataVencimento.hashCode() : 0);
        hash = 47 * hash + (this.sequencia != null ? this.sequencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MovimentacaoBeanImpl other = (MovimentacaoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
    
}
