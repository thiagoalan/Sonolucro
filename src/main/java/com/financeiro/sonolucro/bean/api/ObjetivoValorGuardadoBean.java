/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.bean.api;

import java.util.Date;

/**
 *
 * @author Rodrigo Romero
 * @since 30/03/2013
 * @version 1.0
 */

public interface ObjetivoValorGuardadoBean
{

    public Long getId();

    public void setId(Long id);

    public Date getDataValor();

    public void setDataValor(Date dataValor);

    public Float getValor();

    public void setValor(Float valor);
    
    public String getValorStr(); 
    
    public void setValorStr(String valorStr); 
}
