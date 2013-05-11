package com.financeiro.sonolucro.bean.api;

import java.util.Collection;

/**
 * Interface para cartoes de credito
 *
 * @since 05/08/2012
 * @author Rodrigo Romero
 * @version 1.0
 */
public interface CartaoBean
{

    public Long getId();

    public void setId(Long id);

    public String getNome();

    public void setNome(String nome);

    public String getDescricao();

    public void setDescricao(String descricao);

    public Float getLimite();

    public void setLimite(Float limite);

    public Float getSaldo();

    public void setSaldo(Float saldo);
    
    public UsuarioBean getUsuario();

    public void setUsuario(UsuarioBean usuario);

    public Boolean getStatus();

    public void setStatus(Boolean status);

    public Integer getDiaVencimento();

    public void setDiaVencimento(Integer dataVencimento);

    public Integer getDiaFechamentoFatura();

    public void setDiaFechamentoFatura(Integer dataFechamentoFatura);
    
    public Collection<FaturaCartaoBean> getFaturas(); 
    
    public void setFaturas(Collection<FaturaCartaoBean> faturas); 
    
}