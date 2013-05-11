package com.financeiro.sonolucro.web.navegacao.conta;

import com.financeiro.sonolucro.bean.api.ContaBean;
import com.financeiro.sonolucro.bean.impl.ContaBeanImpl;
import java.util.ArrayList;
import java.util.List;


import javax.faces.model.SelectItem;
import javax.inject.Named;


import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.model.chart.PieChartModel;

/*
 * Recebe e envia informações da view Conta.
 * 
 * @author Rodrigo Romero
 * @since 10/08/2012
 * @version 1.0
 * 
 */
@Named(value = "contaNavegacao")
@ViewAccessScoped  
public class ContaNavegacao implements Serializable
{

    private static final long serialVersionUID = 5210715900851903899L;
    protected static final String BEAN_NAME = "contaNavegacao";
    
    @Inject
    private Conversation conversation;
    private ContaBean conta;
    private List<ContaBeanNavegacao> contas;
    private List<SelectItem> gruposItem;
    private Long grupoItemSelecionado;
    private Integer statusItemSelecionado;
    private String nomeDaConta;
    private PieChartModel grafico = new PieChartModel();
    private PieChartModel graficoDespesa = new PieChartModel();
    private PieChartModel graficoReceita = new PieChartModel();
    private Date dataInicialGrafico;
    private Date dataFinalGrafico;
    private Float saldoDespesa;
    private Float saldoReceita;
    private Integer tipoItemSelecionado;
    private List<SelectItem> statusItens;
    private List<SelectItem> tiposItem;

    public ContaNavegacao()
    {
        
    }

    public ContaBean getConta()
    {
        if (conta == null)
            this.setConta(new ContaBeanImpl());

        return conta;
    }

    public void setConta(ContaBean conta)
    {
        this.conta = conta;
    }

    public List<ContaBeanNavegacao> getContas()
    {
        if (contas == null)
            contas = new ArrayList<ContaBeanNavegacao>();


        return contas;
    }

    public void setContas(List<ContaBeanNavegacao> contas)
    {
        this.contas = contas;
    }

    public List<SelectItem> getGruposItem()
    {
        if (gruposItem == null)
            gruposItem = new ArrayList<SelectItem>();

        return gruposItem;
    }

    public void setGruposItem(List<SelectItem> gruposItem)
    {
        this.gruposItem = gruposItem;
    }

    public Long getGrupoItemSelecionado()
    {

        return grupoItemSelecionado;
    }

    public void setGrupoItemSelecionado(Long grupoItemSelecionado)
    {
        this.grupoItemSelecionado = grupoItemSelecionado;
    }

    public Integer getStatusItemSelecionado()
    {
        return statusItemSelecionado;
    }

    public void setStatusItemSelecionado(Integer statusItemSelecionado)
    {
        this.statusItemSelecionado = statusItemSelecionado;
    }

    public String getNomeDaConta()
    {
        return nomeDaConta;
    }

    public void setNomeDaConta(String nomeDaConta)
    {
        this.nomeDaConta = nomeDaConta;
    }

    public PieChartModel getGrafico()
    {
        return grafico;
    }

    public void setGrafico(PieChartModel grafico)
    {
        this.grafico = grafico;
    }

    public PieChartModel getGraficoDespesa()
    {
        return graficoDespesa;
    }

    public void setGraficoDespesa(PieChartModel graficoDespesa)
    {
        this.graficoDespesa = graficoDespesa;
    }

    public PieChartModel getGraficoReceita()
    {
        return graficoReceita;
    }

    public void setGraficoReceita(PieChartModel graficoReceita)
    {
        this.graficoReceita = graficoReceita;
    }

    public Date getDataInicialGrafico()
    {
        return dataInicialGrafico;
    }

    public void setDataInicialGrafico(Date dataInicialGrafico)
    {
        this.dataInicialGrafico = dataInicialGrafico;
    }

    public Date getDataFinalGrafico()
    {
        return dataFinalGrafico;
    }

    public void setDataFinalGrafico(Date dataFinalGrafico)
    {
        this.dataFinalGrafico = dataFinalGrafico;
    }

    public Float getSaldoDespesa()
    {
        return saldoDespesa;
    }

    public void setSaldoDespesa(Float saldoDespesa)
    {
        this.saldoDespesa = saldoDespesa;
    }

    public Float getSaldoReceita()
    {
        return saldoReceita;
    }

    public void setSaldoReceita(Float saldoReceita)
    {
        this.saldoReceita = saldoReceita;
    }

    public Integer getTipoItemSelecionado()
    {
        return tipoItemSelecionado;
    }

    public void setTipoItemSelecionado(Integer tipoItemSelecionado)
    {
        this.tipoItemSelecionado = tipoItemSelecionado;
    }

    public List<SelectItem> getTiposItem()
    {
        return tiposItem;
    }

    public void setTiposItem(List<SelectItem> tiposItem)
    {
        this.tiposItem = tiposItem;
    }

    public List<SelectItem> getStatusItens()
    {
        return statusItens;
    }

    public void setStatusItens(List<SelectItem> statusItens)
    {
        this.statusItens = statusItens;
    }
}
