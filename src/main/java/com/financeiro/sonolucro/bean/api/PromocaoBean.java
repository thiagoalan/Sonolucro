/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.financeiro.sonolucro.bean.api;

import java.util.Date;

/**
 * @since 29/11/2012
 * @author rodrigo
 * @version 1.0.0
 */
public interface PromocaoBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public String getTitulo(); 
    
    public void setTitulo(String titulo); 
    
    public Integer getNumeroDaPromocao(); 
    
    public void setNumeroDaPromocao(Integer numeroDaPromocao);  
    
    public String getDescricao();
    
    public void setDescricao(String descricao);
    
    public Date getDataInicial(); 
    
    public void setDataInicial(Date dataInicial); 
    
    public Date getDataFinal(); 
    
    public void setDataFinal(Date dataFinal);
    
    public EmpresaBean getEmpresa(); 
    
    public void setEmpresa(EmpresaBean empresa); 
    
}
