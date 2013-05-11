package com.financeiro.sonolucro.web.navegacao.movimentacao;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.api.UsuarioBean;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * Classe responsavel pela criação dos graficos do sonolucro
 * 
 * @author Rodrigo Romero
 * @since 25/08/2012
 * @version 1.0
 */

public class GraficoMovimentacaoUtil
{

    public CartesianChartModel criaGraficoLinhaMovimentacao(UsuarioBean usuario,
            List<MovimentacaoBean> movimentacoes, String label, Integer ano)
    {
         
        CartesianChartModel grafico = new CartesianChartModel();
        criaGrafico(grafico, usuario, movimentacoes, label, ano);
        
        return grafico; 
        
    }
    
    public  CartesianChartModel criaGraficoBarraMovimentacao(UsuarioBean usuario,
            List<MovimentacaoBean> movimentacoes, String label, Integer ano)
    { 
        CartesianChartModel grafico = new CartesianChartModel(); 
        criaGrafico(grafico, usuario, movimentacoes, label, ano); 
        
        return grafico; 
    }
    
    private void criaGrafico(CartesianChartModel grafico, UsuarioBean usuario,
            List<MovimentacaoBean> movimentacoes, String label, Integer ano)
    {

        SimpleDateFormat dateFormat = new DataUtil().formatoUsuarioLogado(usuario.getIdioma().getSigla());
        List<Float> saldos = new ArrayList<Float>();

        try
        {
            Date dataInicial = dateFormat.parse("31/12/" + (ano - 1));
            Date dataFinal = dateFormat.parse("01/01/" + (ano + 1));

            Calendar calendar = Calendar.getInstance();

            for (Integer i = 1; i <= 12; i++)
                saldos.add(null);
      

            if (movimentacoes.size() > 0)
            {
                for (MovimentacaoBean mov : movimentacoes)
                {
                    if (mov.getDataVencimento().before(dataInicial) || mov.getDataVencimento().equals(dataInicial))
                        continue;
                    else
                        if (mov.getDataVencimento().after(dataFinal) || mov.getDataVencimento().after(dataFinal))
                            break;

                    calendar.setTime(mov.getDataVencimento());

                    Integer mesMov = calendar.get(calendar.MONTH) + 1;

                    Float saldo = mov.getSaldo();

                    saldos.set(mesMov - 1, saldo);
                }

                for (Integer i = 0; i < saldos.size(); i++)
                {
                    if (saldos.get(i) == null)
                    {
                        if (i == 0)
                            saldos.set(i, 0f);
                        else
                            if (saldos.get(i) == null)
                                saldos.set(i, saldos.get(i - 1));
                    }
                }
            }
            else
            {
                for (Integer i = 0; i < saldos.size(); i++)                
                    saldos.set(i, 0f);
                
            }

            processaGraficoMovimentacao(grafico, saldos, label);

        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        finally
        {
            saldos = null;
            movimentacoes = null;

        }
    }

    private  void processaGraficoMovimentacao(CartesianChartModel grafico,
            List<Float> saldo, String label)
    {
        String[] meses = MensagemUtil.MESES_ABREVIADOS; 

        ChartSeries series = new ChartSeries();
        series.setLabel(label);

        if (saldo.size() == meses.length)
        {
            for (Integer index = 0; index < saldo.size(); index++)
                series.set(meses[index], (Number) saldo.get(index));
            
        }

        grafico.addSeries(series);
    }
    
    public synchronized static CartesianChartModel criaGraficoBar(List<Number> despesas, List<Number> receitas,
            List<String> texto, String label1, String label2)
    {
        CartesianChartModel graficoBar = new CartesianChartModel();

        ChartSeries series1 = new ChartSeries();
        series1.setLabel(label1);

        ChartSeries series2 = new ChartSeries();
        series2.setLabel(label2);

        if (texto.size() == despesas.size() && (texto.size() == receitas.size()))
        {
            for (Integer index = 0; index < despesas.size(); index++)
            {
                series1.set(texto.get(index), (Number) despesas.get(index));
                series2.set(texto.get(index), (Number) receitas.get(index));
            }
        }

        graficoBar.addSeries(series1);
        graficoBar.addSeries(series2);

        return graficoBar;
    }

}
