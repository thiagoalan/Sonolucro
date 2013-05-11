
package com.financeiro.sonolucro.web.navegacao.objetivo;

import com.financeiro.sonolucro.bean.api.ObjetivoBean;
import com.financeiro.sonolucro.bean.impl.ObjetivoBeanImpl;
import java.io.Serializable;
import org.primefaces.model.chart.CartesianChartModel;

/**
 * @author Rodrigo Romero
 * @since 17/03/2013
 * @version 1.0
 */
public class ObjetivoBeanNavegacao extends ObjetivoBeanImpl implements Serializable
{
    private static final long serialVersionUID = -3083914274535948584L;
    
    private Float totalPorcento; 
    private String statusStr; 
    private String mesAtualStr;
    private Float metaEfetivoDia; 
    private Float metaEfetivoMes; 
    private String valorTotalGuardadoStr;
    private CartesianChartModel graficoBarra; 
    private CartesianChartModel graficoLinha; 
    
    public ObjetivoBeanNavegacao()
    {
        super(); 
    }
    
    public ObjetivoBeanNavegacao(ObjetivoBean objetivo)
    {
        super(objetivo); 
    }

    public Float getTotalPorcento()
    {
        return totalPorcento;
    }

    public void setTotalPorcento(Float totalPorcento)
    {
        this.totalPorcento = totalPorcento;
    }

    public String getStatusStr()
    {
        return statusStr;
    }

    public void setStatusStr(String statusStr)
    {
        this.statusStr = statusStr;
    }

    public String getMesAtualStr()
    {
        return mesAtualStr;
    }

    public void setMesAtualStr(String mesAtualStr)
    {
        this.mesAtualStr = mesAtualStr;
    }

    public String getValorTotalGuardadoStr()
    {
        return valorTotalGuardadoStr;
    }

    public void setValorTotalGuardadoStr(String valorTotalGuardadoStr)
    {
        this.valorTotalGuardadoStr = valorTotalGuardadoStr;
    }

    public CartesianChartModel getGraficoBarra()
    {
        if(graficoBarra == null)
            setGraficoBarra(new CartesianChartModel()); 
        
        return graficoBarra;
    }

    public void setGraficoBarra(CartesianChartModel graficoBarra)
    {
        this.graficoBarra = graficoBarra;
    }

    public CartesianChartModel getGraficoLinha()
    {
        if(graficoLinha == null)
            setGraficoLinha(new CartesianChartModel()); 
        
        return graficoLinha;
    }

    public void setGraficoLinha(CartesianChartModel graficoLinha)
    {
        this.graficoLinha = graficoLinha;
    }

    public Float getMetaEfetivoDia()
    {
        return metaEfetivoDia;
    }

    public void setMetaEfetivoDia(Float metaEfetivoDia)
    {
        this.metaEfetivoDia = metaEfetivoDia;
    }

    public Float getMetaEfetivoMes()
    {
        return metaEfetivoMes;
    }

    public void setMetaEfetivoMes(Float metaEfetivoMes)
    {
        this.metaEfetivoMes = metaEfetivoMes;
    }
}
