
package com.financeiro.sonolucro.bean.api;

/**
 * @author Rodrigo Romero
 * @version 1.0
 * @since 23/03/2013
 * 
 */
public interface AcaoBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public Integer getCodigo(); 
    
    public void setCodigo(Integer codigo); 
    
    public String getSigla(); 
    
    public void setSigla(String sigla); 
    
    public Character getOrigem(); 
    
    public void setOrigem(Character origem); 
    
    public Float getQuantidade(); 
    
    public void setQuantidade(Float quantidade); 
    
    public Float getValorUnitario(); 
    
    public void setValorUnitario(Float valorUnitario); 
    
    public Float getValorDeCompra(); 
    
    public void setValorDeCompra(Float valorDeCompra); 
    
    public Float getValorDeVenda(); 
    
    public void setValorDeVenda(Float valorDeVenda);
    
    public Float getEmolumentos(); 
    
    public void setEmolumentos(Float emolumentos); 
    
    public Float getCustoDaAquisicao(); 
    
    public void setCustoDaAquisicao(Float valorDaAquisicao);
    
    public Float getImpostoDeRenda(); 
    
    public void setImpostoDeRenda(Float impostoDeRenda); 
    
    public String getDescricao(); 
    
    public void setDescricao(String descricao); 
    
    public UsuarioBean getUsuario(); 
    
    public void setUsuario(UsuarioBean usuario); 
}
