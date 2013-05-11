
package com.financeiro.sonolucro.web.navegacao.objetivo;

import com.financeiro.sonolucro.web.navegacao.objetivo.ObjetivoNavegacaoUtil.ObjetivoMes;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Rodrigo Romero 
 * @since 31/03/2013
 * @version 1.0
 */
public class GraficoObjetivoUtil
{
    public CartesianChartModel criaGraficoObjetivo(ObjetivoMes[] meses)
    {
        CartesianChartModel model = new CartesianChartModel(); 
        
        ChartSeries seriesGuardado = new ChartSeries();
        seriesGuardado.setLabel("guardado");
        ChartSeries seriesTotal = new ChartSeries();
        seriesTotal.setLabel("meta");
        
        for(ObjetivoMes mes : meses)
        {
            String dataStr = mes.getNome() + "/" + mes.getAno(); 
            seriesGuardado.set(dataStr, mes.getMetaAlcansada());
            seriesTotal.set(dataStr, mes.getMetaEstipulada()); 
        }
        
        model.addSeries(seriesGuardado);
        model.addSeries(seriesTotal);
        
        return model; 
    }
}
