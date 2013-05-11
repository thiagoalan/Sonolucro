package com.financeiro.sonolucro.web.navegacao.objetivo;

import com.financeiro.sonolucro.bean.api.ObjetivoValorGuardadoBean;
import com.financeiro.sonolucro.util.DataUtil;
import com.financeiro.sonolucro.util.MensagemUtil;
import com.financeiro.sonolucro.util.SonolucroViewException;
import com.financeiro.sonolucro.util.ValorUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Rodrigo Romero
 * @since 30/03/2013
 * @version 1.0
 */
public class ObjetivoNavegacaoUtil implements Serializable
{

    private static final long serialVersionUID = -3050890664164147956L;
    private ValorUtil valorUtil;
    private DataUtil dataUtil;

    public ObjetivoNavegacaoUtil()
    {
        valorUtil = new ValorUtil();
        dataUtil = new DataUtil();
    }

    public void processaStatusObjetivo(ObjetivoBeanNavegacao objetivo) throws SonolucroViewException
    {
        try
        {
            Date dataAtual = new Date();
            Date dataInicial = objetivo.getDataInicialPrevista();
            Date dataFinal = objetivo.getDataFinalPrevista();

            Integer totalDeDias = dataUtil.getTotalDeDias(dataInicial, dataFinal) + 1;
            Float metaTotalDia = objetivo.getValorTotal() / totalDeDias;
            objetivo.setMetaValorDia(metaTotalDia);

            if (dataInicial.equals(dataAtual) || dataInicial.before(dataAtual))
                montarMesAtualStr(objetivo, dataAtual);
            else
                montarMesAtualStr(objetivo, dataInicial);

            montarGraficoObjetivo(objetivo);
            validaStatus(objetivo);
            
            Float totalGuardado = getTotalGuardado(objetivo.getValoresGuardados());
            objetivo.setValorTotalGuardadoStr(valorUtil.getValoStr(totalGuardado));
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    public void processaProgressoObjetivo(ObjetivoBeanNavegacao objetivo) throws SonolucroViewException
    {
        try
        {
            Float totalGuardado = getTotalGuardado(objetivo.getValoresGuardados());

            Float statusProgresso = valorUtil.processaPorcentagem(objetivo.getValorTotal(),
                    totalGuardado);
            objetivo.setTotalPorcento(statusProgresso);

            String totalGuardadoStr = valorUtil.getValoStr(totalGuardado);
            objetivo.setValorTotalGuardadoStr(totalGuardadoStr);
        }
        catch (RuntimeException e)
        {
            throw new SonolucroViewException(e);
        }
    }

    private void montarMesAtualStr(ObjetivoBeanNavegacao objetivo, Date data)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);

        Integer mes = calendar.get(Calendar.MONTH) + 1;
        Integer ano = calendar.get(Calendar.YEAR);

        String[] mesesStr = MensagemUtil.MESES;
        StringBuilder dataStr = new StringBuilder();

        dataStr.append(mesesStr[mes - 1]);
        dataStr.append(" " + ano);

        objetivo.setMesAtualStr(dataStr.toString());

    }

    private void montarGraficoObjetivo(ObjetivoBeanNavegacao objetivo)
    {
        Integer totalDeMeses = dataUtil.getTotalDeMeses(objetivo.getDataInicialPrevista(), objetivo.getDataFinalPrevista()) + 1;

        ObjetivoMes[] meses = new ObjetivoMes[totalDeMeses];

        criarObjetivosMeses(objetivo, meses);

        if (objetivo.getStatus())
            calcularEfetivos(objetivo, meses);

        GraficoObjetivoUtil grafico = new GraficoObjetivoUtil();

        objetivo.setGraficoBarra(grafico.criaGraficoObjetivo(meses));
        objetivo.setGraficoLinha(grafico.criaGraficoObjetivo(meses));
    }

    private void criarObjetivosMeses(ObjetivoBeanNavegacao objetivo, ObjetivoMes[] meses)
    {
        Integer mesInicial = dataUtil.getMes(objetivo.getDataInicialPrevista());
        Integer ano = dataUtil.getAno(objetivo.getDataInicialPrevista());
        Date dataAtual = new Date();
        Integer mesAtual = dataUtil.getMes(dataAtual);
        Integer anoAtual = dataUtil.getAno(dataAtual);
        Integer size = meses.length;
        Integer count = 0;

        do
        {
            Float valorDia = objetivo.getMetaValorDia();

            Date dataInicial = new GregorianCalendar(ano.intValue(), mesInicial.intValue() - 1, 1).getTime();

            Integer totalDiasMes = dataUtil.getUltimoDiaMes(dataInicial);
            Date dataFinal = new GregorianCalendar(ano.intValue(), mesInicial.intValue() - 1, totalDiasMes.intValue()).getTime();

            Float valorMes = 0.0f;

            if (count == 0)
            {
                Integer dias = dataUtil.getTotalDeDias(objetivo.getDataInicialPrevista(), dataFinal) + 1;
                valorMes = valorDia * dias;
            }
            else if (count == size - 1)
            {
                Integer dias = dataUtil.getTotalDeDias(dataInicial, objetivo.getDataFinalPrevista()) + 1;
                valorMes = valorDia * dias;
            }
            else
            {
                Integer dias = dataUtil.getTotalDeDias(dataInicial, dataFinal) + 1;
                valorMes = valorDia * dias;
            }

            if (count == 0)
                objetivo.setMetaValorMes(valorMes);

            if (ano.intValue() == anoAtual.intValue() && mesInicial.intValue() == mesAtual.intValue())
                objetivo.setMetaValorMes(valorMes);

            Float totalGuardado = 0.0f;

            if (count == 0)
                totalGuardado = getTotalGuardado(new GregorianCalendar(1990, 3, 1).getTime(), dataFinal, objetivo.getValoresGuardados());
            else
                totalGuardado = getTotalGuardado(dataInicial, dataFinal, objetivo.getValoresGuardados());

            ObjetivoMes objMes = new ObjetivoMes(ano, mesInicial, totalDiasMes, String.valueOf(mesInicial), valorMes, totalGuardado);

            meses[count] = objMes;
            mesInicial++;
            count++;

            if (mesInicial > 12)
            {
                mesInicial = 1;
                ano++;
            }
        }
        while (count < size);
    }

    protected Float getTotalGuardado(Collection<ObjetivoValorGuardadoBean> valores) throws IllegalArgumentException
    {
        if (valores == null)
            throw new IllegalArgumentException();

        Float totalGuardado = 0.0f;

        for (ObjetivoValorGuardadoBean valorGuardado : valores)
            totalGuardado += valorGuardado.getValor();

        return totalGuardado;

    }

    protected Float getTotalGuardado(Date dataInicial, Date dataFinal, Collection<ObjetivoValorGuardadoBean> valores) throws IllegalArgumentException
    {
        if (valores == null)
            throw new IllegalArgumentException();

        Float total = 0.0f;

        for (ObjetivoValorGuardadoBean valor : valores)
        {
            if ((valor.getDataValor().after(dataInicial) || valor.getDataValor().equals(dataInicial))
                    && valor.getDataValor().before(dataFinal) || valor.getDataValor().equals(dataFinal))
            {
                total += valor.getValor();
            }
        }

        return total;
    }

    private void validaStatus(ObjetivoBeanNavegacao objetivo)
    {
        Float valorTotalGuardado = valorUtil.getValorReal(objetivo.getValorTotalGuardadoStr());

        if (valorTotalGuardado.floatValue() >= objetivo.getValorTotal().floatValue())
            objetivo.setStatus(false);
        else
            objetivo.setStatus(true);
    }

    private void calcularEfetivos(ObjetivoBeanNavegacao objetivo, ObjetivoMes[] meses)
    {
        Date dataAtual = new Date();

        Float valorAlcansado = 0.0f;
        Integer totalDeDias = 0;

        ObjetivoMes objMesAtual = getMesAtualObjetivo(objetivo, meses);

        if (dataAtual.after(objetivo.getDataInicialPrevista())
                || dataAtual.equals(objetivo.getDataInicialPrevista()))
        {
            totalDeDias = dataUtil.getTotalDeDias(objetivo.getDataInicialPrevista(), dataAtual) + 1;
            valorAlcansado = getTotalGuardado(objetivo.getDataInicialPrevista(), dataAtual, objetivo.getValoresGuardados());
        }
        else
        {
            Integer diaInicial = dataUtil.getDia(objetivo.getDataInicialPrevista());
            Integer ultimoDiaMes = dataUtil.getUltimoDiaMes(objetivo.getDataInicialPrevista());
            totalDeDias = ultimoDiaMes - diaInicial;
            valorAlcansado = meses[0].getMetaAlcansada();

            if (valorAlcansado.floatValue() == 0)
            {
                objetivo.setDataFinalEfetiva(objetivo.getDataFinalPrevista());
                objetivo.setMetaEfetivoDia(0.0f);
                objetivo.setMetaEfetivoMes(0.0f);
                return;
            }
        }

        montarValoresEfetivos(objetivo, objMesAtual, valorAlcansado, totalDeDias);
    }

    private void montarValoresEfetivos(ObjetivoBeanNavegacao objetivo, ObjetivoMes objMesAtual,
                                       Float valorAlcansado, Integer totalDeDias)
    {
        Float valorDiaEfetivo = valorAlcansado / totalDeDias;
        objetivo.setMetaEfetivoDia(valorDiaEfetivo);
        Float diasFlutuante = objetivo.getValorTotal() / valorDiaEfetivo;
        Integer diasEfetivos = diasFlutuante.intValue();
        Date dataFinalEfetiva = null;
        if (valorAlcansado != 0)
            dataFinalEfetiva = dataUtil.getDataApartir(objetivo.getDataInicialPrevista(), diasEfetivos);
        else
            dataFinalEfetiva = dataUtil.getDataApartir(new Date(), diasEfetivos);
        objetivo.setDataFinalEfetiva(dataFinalEfetiva);
        objetivo.setMetaEfetivoMes(objMesAtual.getMetaAlcansada());
    }

    private ObjetivoMes getMesAtualObjetivo(ObjetivoBeanNavegacao objetivo, ObjetivoMes[] meses)
    {
        Date dataAtual = new Date();

        Integer anoAtual = dataUtil.getAno(dataAtual);
        Integer mesAtual = dataUtil.getMes(dataAtual);

        if (meses.length > 0)
        {

            if (dataAtual.before(objetivo.getDataInicialPrevista()))
                return meses[0];

            for (ObjetivoMes mes : meses)
            {
                if (anoAtual.intValue() == mes.getAno().intValue()
                        && mesAtual.intValue() == mes.getMes())
                    return mes;
            }
        }

        return null;
    }

    protected class ObjetivoMes
    {

        private Integer ano;
        private Integer mes;
        private String nome;
        private Integer totalDias;
        private Float metaEstipulada;
        private Float metaAlcansada;

        public ObjetivoMes()
        {
        }

        public ObjetivoMes(Integer ano, Integer numero, Integer totalDias,
                           String nome, Float metaEstipulada, Float metaAlcansada)
        {
            setAno(ano);
            setMes(numero);
            setNome(nome);
            setTotalDias(totalDias);
            setMetaEstipulada(metaEstipulada);
            setMetaAlcansada(metaAlcansada);
        }

        public Integer getAno()
        {
            return ano;
        }

        public void setAno(Integer ano)
        {
            this.ano = ano;
        }

        public Integer getMes()
        {
            return mes;
        }

        public void setMes(Integer mes)
        {
            this.mes = mes;
        }

        public String getNome()
        {
            return nome;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public Integer getTotalDias()
        {
            return totalDias;
        }

        public void setTotalDias(Integer totalDias)
        {
            this.totalDias = totalDias;
        }

        public Float getMetaEstipulada()
        {
            return metaEstipulada;
        }

        public void setMetaEstipulada(Float metaEstipulada)
        {
            this.metaEstipulada = metaEstipulada;
        }

        public Float getMetaAlcansada()
        {
            return metaAlcansada;
        }

        public void setMetaAlcansada(Float metaAlcansada)
        {
            this.metaAlcansada = metaAlcansada;
        }
    }
}
