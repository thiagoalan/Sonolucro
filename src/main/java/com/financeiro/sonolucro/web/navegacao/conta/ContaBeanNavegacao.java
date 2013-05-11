/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.web.navegacao.conta;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.impl.ContaBeanImpl;
import java.io.Serializable;

/**
 *
 * @author rodrigo
 * @since 26/01/2013
 * @version 1.0
 * 
 * Classe auxiliar da contaNavegacao
 */

public class ContaBeanNavegacao extends ContaBeanImpl implements Serializable
{
    private String tipoStr;
    
    public ContaBeanNavegacao()
    {
        
    }
    
    public ContaBeanNavegacao(ContaBean conta)
    {
        super(conta); 
    }

    public String getTipoStr()
    {
        return tipoStr;
    }

    public void setTipoStr(String tipoStr)
    {
        this.tipoStr = tipoStr;
    }
    
    
}
