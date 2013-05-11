package com.financeiro.sonolucro.web.navegacao.conta;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.util.MensagemUtil;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.chart.PieChartModel;

/**
 * @author Rodrigo Romero
 * @version 1.0
 * @since 13/03/2013
 */
public class GraficoContaUtil
{

    public PieChartModel criaGraficoDePizza(Float despesaSaldo,
                                            Float receitaSaldo)
    {
        PieChartModel grafico = new PieChartModel();

        String despesaStr = MensagemUtil.getMensagem("conta_tipo_despesa");
        String receitaStr = MensagemUtil.getMensagem("conta_tipo_receita");

        grafico.set(receitaStr, receitaSaldo);
        grafico.set(despesaStr, despesaSaldo);

        return grafico;
    }

    public PieChartModel criaGraficoDePizza(List<ContaBean> contas, List<MovimentacaoBean> movs, Integer tipo)
    {
        if (contas == null || movs == null)
            return null;

        PieChartModel grafico = new PieChartModel();

        List<ContaBean> contasAtivadas = new ArrayList<ContaBean>();

        for (ContaBean conta : contas)
        {
            if (conta.getStatus() && conta.getTipo().equals(tipo))
                contasAtivadas.add(conta);
        }

        for (ContaBean conta : contasAtivadas)
        {
            Float totalConta = 0.0f;

            for (MovimentacaoBean mov : movs)
            {
                if (mov.getConta().equals(conta))
                    totalConta += mov.getValor();
            }

            grafico.set(conta.getNome(), totalConta);
        }

        return grafico;
    }
}
