/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.web.navegacao.cartao;

import com.financeiro.sonolucro.bean.api.CartaoBean;
import com.financeiro.sonolucro.bean.impl.CartaoBeanImpl;
import java.io.Serializable;

/**
 *
 * @author rodrigo
 * @since 26/01/2013
 * @version 1.0
 * 
 * Classe auxiliar de cartaoNavegacao
 */
public class CartaoBeanNavegacao extends CartaoBeanImpl implements Serializable
{
    private Float saldoPorcento;
    
    public CartaoBeanNavegacao()
    {
        
    }
    
    public CartaoBeanNavegacao(CartaoBean cartao)
    {
        super(cartao); 
    }

    public Float getSaldoPorcento()
    {
        return saldoPorcento;
    }

    public void setSaldoPorcento(Float saldoPorcento)
    {
        this.saldoPorcento = saldoPorcento;
    }
    
    
}
