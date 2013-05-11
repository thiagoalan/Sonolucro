/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.bean.api;

import java.util.List;

/**
 *
 * @author rodrigo
 * @since 16/11/2012
 * @version 1.0.0
 */
public interface PaisBean
{
    public Long getId();
    
    public void setId(Long id); 
    
    public String getNome(); 
    
    public void setNome(String nome);
    
    public String getSigla(); 
    
    public void setSigla(String sigla); 
    
    public String getContinente(); 
    
    public void setContinente(String continente); 
  
}
