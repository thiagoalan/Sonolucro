
package com.financeiro.sonolucro.bean.api;

import java.util.Collection;
import java.util.Date;

/**
 * Interface para represantar faturas de cartões de crédito.
 * Uma fatura contém 0 ou M movimentações ligadas ao cartão que ela pertence
 * 
 * @author rodrigo
 * @since 24/11/2012
 * @version 1.0.0
 */
public interface FaturaCartaoBean
{
    public Long getId(); 
    
    public void setId(Long id); 
    
    public Date getDataInicial(); 
    
    public void setDataInicial(Date dataInicial);
    
    public Date getDataFinal(); 
    
    public void setDataFinal(Date dataFinal); 
    
    public Float getValorTotal(); 
    
    public void setValorTotal(Float valor);
    
    public Float getValorPago(); 
    
    public void setValorPago(Float valorPago); 
    
    public String getValorPagoStr(); 
    
    public void setValorPagoStr(String valorPagoStr); 
    
    public Collection<MovimentacaoBean> getMovimentacoes(); 
    
    public void setMovimentacoes(Collection<MovimentacaoBean>  movimentacoes); 
   
} 
