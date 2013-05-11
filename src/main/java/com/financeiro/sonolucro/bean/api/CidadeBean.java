
package com.financeiro.sonolucro.bean.api;

/**
 *
 * @author rodrigo
 * @since 16/11/2012
 * @version 1.0
 */
public interface CidadeBean
{
    public Long getId(); 
    
    public void setId(Long id);
    
    public String getNome(); 
    
    public void setNome(String nome); 
    
    public EstadoBean getEstado();
    
    public void setEstado(EstadoBean estado); 
}