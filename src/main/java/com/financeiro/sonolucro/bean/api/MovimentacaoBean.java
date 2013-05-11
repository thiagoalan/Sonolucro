package com.financeiro.sonolucro.bean.api;

import java.util.Date;

/**
 * Representa as movimentações financeiras realizadas pelo usuário, cada
 * movimentação pertence ao um tipo de conta, pode ou não estar veinculada ao um
 * cartão de crédito.
 *
 * @since 05/08/2012
 * @author Rodrigo Romero   
 * @version 1.0
 */
public interface MovimentacaoBean
{

    public Long getId();

    public void setId(Long id); 
    
    public Float getValor(); 
    
    public void setValor(Float valor);

    public String getDescricao();

    public void setDescricao(String descricao);

    public Date getDataLancamento();

    public void setDataLancamento(Date dataLancamento);

    public Date getDataVencimento();

    public void setDataVencimento(Date dataVencimento);

    public Integer getSequencia();

    public void setSequencia(Integer sequencia);

    public ContaBean getConta();

    public void setConta(ContaBean conta);

    public CartaoBean getCartao();

    public void setCartao(CartaoBean cartao);

    public Float getCredito();

    public void setCredito(Float credito);

    public Float getDebito();

    public void setDebito(Float debito);

    public Float getSaldo();

    public void setSaldo(Float saldo);
}