
package com.financeiro.sonolucro.bean.api;

/**
 * 
 * @author rodrigo
 * @since 16/11/2012
 * @version 1.0.0
 */
public interface EstadoBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public String getNome(); 
    
    public void setNome(String nome); 
    
    public PaisBean getPais(); 
    
    public void setPais(PaisBean pais); 
    
    public String getSigla(); 
    
    public void setSigla(String sigla); 
}
