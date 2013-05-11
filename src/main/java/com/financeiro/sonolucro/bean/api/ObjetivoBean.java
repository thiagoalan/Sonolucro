
package com.financeiro.sonolucro.bean.api;

import java.util.Collection;
import java.util.Date;

/**
 * @author Rodrigo Romero
 * @since 23/11/2012
 * @version 1.0
 */
public interface ObjetivoBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public String getTitulo(); 
    
    public void setTitulo(String titulo);  
    
    public String getDescricao(); 
    
    public void setDescricao(String descricao); 
    
    public Date getDataInicialPrevista(); 
    
    public void setDataInicialPrevista(Date dataInicialPrevista); 
    
    public Date getDataFinalPrevista(); 
    
    public void setDataFinalPrevista(Date dataFinalPrevista);
    
    public Date getDataFinalEfetiva(); 
    
    public void setDataFinalEfetiva(Date dataFinalEfetiva);  
    
    public Float getValorTotal(); 
    
    public void setValorTotal(Float valorTotal);
    
    public Float getMetaValorDia(); 
    
    public void setMetaValorDia(Float metaValorDia); 
    
    public Float getMetaValorMes(); 
    
    public void setMetaValorMes(Float metaValorMes); 
    
    public Boolean getStatus(); 
    
    public void setStatus(Boolean status); 
    
    public UsuarioBean getUsuario(); 
    
    public void setUsuario(UsuarioBean usuario); 
    
    public Collection<ObjetivoValorGuardadoBean> getValoresGuardados(); 
    
    public void setValoresGuardados(Collection<ObjetivoValorGuardadoBean> valoresGuardados);
    
}
