
package com.financeiro.sonolucro.web.navegacao.objetivo;

import com.financeiro.sonolucro.bean.api.ObjetivoValorGuardadoBean;
import com.financeiro.sonolucro.bean.impl.ObjetivoValorGuardadoBeanImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

/**
 * @author Rodrigo Romero
 * @version 1.0
 * @since 04/03/2013
 */

@Named(value = "objetivoNavegacao")
@ViewAccessScoped  
public class ObjetivoNavegacao implements Serializable
{
    
    private static final long serialVersionUID = -5899296368492797290L;    
    protected static final String BEAN_NAME = "objetivoNavegacao"; 
    
    @Inject
    private Conversation conversation;
    
    private ObjetivoBeanNavegacao objetivo; 
    private String valorTotalStr; 
    private String valorGuardadoStr; 
    private String valorStr; 
    private List<ObjetivoBeanNavegacao> objetivos;  
    private ObjetivoValorGuardadoBean valorGuardado; 
    private ObjetivoValorGuardadoBean ultimoValorGuardado; 
    
    public ObjetivoBeanNavegacao getObjetivo()
    {
        if(objetivo == null)
            setObjetivo(new ObjetivoBeanNavegacao()); 
        
        return objetivo;
    }

    public void setObjetivo(ObjetivoBeanNavegacao objetivo)
    {
        this.objetivo = objetivo;
    }

    public String getValorTotalStr()
    {
        return valorTotalStr;
    }

    public void setValorTotalStr(String valorTotalStr)
    {
        this.valorTotalStr = valorTotalStr;
    }

    public List<ObjetivoBeanNavegacao> getObjetivos()
    {
        if(objetivos == null)
            setObjetivos(new ArrayList<ObjetivoBeanNavegacao>()); 
        
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoBeanNavegacao> objetivos)
    {
        this.objetivos = objetivos;
    }
    
    public String getValorGuardadoStr()
    {
        return valorGuardadoStr;
    }

    public void setValorGuardadoStr(String valorGuardadoStr)
    {
        this.valorGuardadoStr = valorGuardadoStr;
    }

    public String getValorStr()
    {
        return valorStr;
    }

    public void setValorStr(String valorStr)
    {
        this.valorStr = valorStr;
    }

    public ObjetivoValorGuardadoBean getValorGuardado()
    {
        if(valorGuardado == null)
            setValorGuardado(new ObjetivoValorGuardadoBeanImpl()); 
        
        return valorGuardado;
    }

    public void setValorGuardado(ObjetivoValorGuardadoBean valorGuardado)
    {
        this.valorGuardado = valorGuardado;
    }

    public ObjetivoValorGuardadoBean getUltimoValorGuardado()
    {
        if(ultimoValorGuardado == null)
            setUltimoValorGuardado(new ObjetivoValorGuardadoBeanImpl()); 
        
        return ultimoValorGuardado;
    }

    public void setUltimoValorGuardado(ObjetivoValorGuardadoBean ultimoValorGuardado)
    {
        this.ultimoValorGuardado = ultimoValorGuardado;
    }
   
}
