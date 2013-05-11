/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.web.navegacao.dica;

import com.financeiro.sonolucro.bean.api.DicaBean;
import com.financeiro.sonolucro.bean.impl.DicaBeanImpl;
import java.io.Serializable;

/**
 *
 * @author rodrigo
 * @since 26/01/2013
 * @version 1.0
 *
 * Classe auxiliar de DicaNavegacao
 *
 */
public class DicaBeanNavegacao extends DicaBeanImpl implements Serializable
{

    private String categoriaStr;
    private String resumoTexto;
    
    public DicaBeanNavegacao()
    {
        
    }
    
    public DicaBeanNavegacao(DicaBean dica)
    {
        super(dica); 
    }

    public String getCategoriaStr()
    {
        return categoriaStr;
    }

    public void setCategoriaStr(String categoriaStr)
    {
        this.categoriaStr = categoriaStr;
    }

    public String getResumoTexto()
    {
        return resumoTexto;
    }

    public void setResumoTexto(String resumoTexto)
    {
        this.resumoTexto = resumoTexto;
    }
    
    
}
