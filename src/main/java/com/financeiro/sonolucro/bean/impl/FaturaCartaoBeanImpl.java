package com.financeiro.sonolucro.bean.impl;

import com.financeiro.sonolucro.bean.api.FaturaCartaoBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Implementação da interface FaturaCartaoBean Uma fatura contém 0 ou M
 * movimentações ligadas ao cartão que ela pertence
 *
 * @author Rodrigo Romero
 * @since 24/11/2012
 * @version 1.0
 *
 */
@Entity(name = "FaturaCartao")
@Table(name = "fatura_cartao")
public class FaturaCartaoBeanImpl implements FaturaCartaoBean, Serializable, Comparable<FaturaCartaoBean>
{
    private static final long serialVersionUID = 182047348419497612L;

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_inicial", nullable = false)
    private Date dataInicial;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_final", nullable = false)
    private Date dataFinal;
    @Column(name = "valor_pago")
    private Float valorPago;
    @Transient
    private Float valorTotal;
    @Transient
    private Collection<MovimentacaoBean> movimentacoes = new ArrayList<MovimentacaoBean>();
    @Transient
    private String valorPagoStr;

    public FaturaCartaoBeanImpl()
    {
    }

    public FaturaCartaoBeanImpl(FaturaCartaoBean fatura)
    {
        setId(fatura.getId());
        setDataInicial(fatura.getDataInicial());
        setDataFinal(fatura.getDataFinal());
        setValorPago(fatura.getValorPago());
        setValorTotal(fatura.getValorTotal());
    }

    public FaturaCartaoBeanImpl(Date dataInicial, Date dataFinal,
                                Float valorPago, Float valorTotal)
    {
        setDataInicial(dataInicial);
        setDataFinal(dataFinal);
        setValorPago(valorPago);
        setValorTotal(valorTotal);
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
    public Float getValorPago()
    {
        if (valorPago == null)
            setValorPago(0.0f);

        return valorPago;
    }

    @Override
    public void setValorPago(Float valorPago)
    {
        this.valorPago = valorPago;
    }

    @Override
    public Float getValorTotal()
    {
        if (valorTotal == null)
            setValorTotal(0.0f);

        return valorTotal;
    }

    @Override
    public void setValorTotal(Float valorTotal)
    {
        this.valorTotal = valorTotal;
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
    public Collection<MovimentacaoBean> getMovimentacoes()
    {
        return movimentacoes;
    }

    @Override
    public void setMovimentacoes(Collection<MovimentacaoBean> movimentacoes)
    {
        this.movimentacoes = movimentacoes;
    }

    @Override
    public String getValorPagoStr()
    {
        return valorPagoStr;
    }

    @Override
    public void setValorPagoStr(String valorPagoStr)
    {
        this.valorPagoStr = valorPagoStr;
    }

    @Override
    public int compareTo(FaturaCartaoBean fatura)
    {
        if(this.dataInicial.equals(fatura.getDataInicial()))
            return 0; 
        else if(this.dataInicial.before(fatura.getDataInicial()))
            return -1; 
        else
            return 1;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.dataInicial != null ? this.dataInicial.hashCode() : 0);
        hash = 97 * hash + (this.dataFinal != null ? this.dataFinal.hashCode() : 0);
        hash = 97 * hash + (this.valorPago != null ? this.valorPago.hashCode() : 0);
        hash = 97 * hash + (this.valorTotal != null ? this.valorTotal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FaturaCartaoBeanImpl other = (FaturaCartaoBeanImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id)))
            return false;
        return true;
    }
    
    

}
