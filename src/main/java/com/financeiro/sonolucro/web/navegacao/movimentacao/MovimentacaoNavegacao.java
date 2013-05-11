package com.financeiro.sonolucro.web.navegacao.movimentacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.bean.impl.MovimentacaoBeanImpl;
import java.io.Serializable;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.CartesianChartModel;

/**
 *
 * @author rodrigo
 * @since 22/08/2012
 * @version 1.0.0
 */
@Named(value = "movimentacaoNavegacao")
@ViewAccessScoped  
public class MovimentacaoNavegacao implements Serializable
{

    private static final long serialVersionUID = 6034444030554046126L;
    protected static final String BEAN_NAME = "movimentacaoNavegacao";
  
    private List<MovimentacaoBean> movimentacoes;
    private List<SelectItem> contasItem;
    private List<SelectItem> cartoesItem;
    private Long contaItemSelecionado;
    private Long cartaoItemSelecionado;
    private Date dataInicial;
    private Date dataFinal;
    private Integer tipoDoGrafico;
    private MovimentacaoBean movimentacao;
    private CartesianChartModel graficoLinha;
    private CartesianChartModel graficoBarra;
    private Integer anoGrafico;
    private String valorStr; 
    private ScheduleModel eventModel; 
    private Boolean alteracao = false; 
 

    public MovimentacaoNavegacao()
    {
    }

    public List<MovimentacaoBean> getMovimentacoes()
    {
        if (movimentacoes == null)
            setMovimentacoes(new ArrayList<MovimentacaoBean>());

        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoBean> movimentacoes)
    {
        this.movimentacoes = movimentacoes;
    }

    public MovimentacaoBean getMovimentacao()
    {
        if (movimentacao == null)
            setMovimentacao(new MovimentacaoBeanImpl());

        return movimentacao;
    }

    public void setMovimentacao(MovimentacaoBean movimentacao)
    {
        this.movimentacao = movimentacao;
    }

    public List<SelectItem> getContasItem()
    {
        if (contasItem == null)
            setContasItem(new ArrayList<SelectItem>());

        return contasItem;
    }

    public void setContasItem(List<SelectItem> contasItem)
    {
        this.contasItem = contasItem;
    }

    public Long getContaItemSelecionado()
    {
        return contaItemSelecionado;
    }

    public void setContaItemSelecionado(Long contaItemSelecionado)
    {
        this.contaItemSelecionado = contaItemSelecionado;
    }

    public Date getDataInicial()
    {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial)
    {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal()
    {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal)
    {
        this.dataFinal = dataFinal;
    }

    public List<SelectItem> getCartoesItem()
    {
        if (cartoesItem == null)
            setCartoesItem(new ArrayList<SelectItem>());

        return cartoesItem;
    }

    public void setCartoesItem(List<SelectItem> cartoesItem)
    {
        this.cartoesItem = cartoesItem;
    }

    public Long getCartaoItemSelecionado()
    {
        return cartaoItemSelecionado;
    }

    public void setCartaoItemSelecionado(Long cartaoItemSelecionado)
    {
        this.cartaoItemSelecionado = cartaoItemSelecionado;
    }

    public Integer getTipoDoGrafico()
    {
        return tipoDoGrafico;
    }

    public void setTipoDoGrafico(Integer tipoDoGrafico)
    {
        this.tipoDoGrafico = tipoDoGrafico;
    }

    public CartesianChartModel getGraficoLinha()
    {
        return graficoLinha;
    }

    public void setGraficoLinha(CartesianChartModel graficoLinha)
    {
        this.graficoLinha = graficoLinha;
    }

    public CartesianChartModel getGraficoBarra()
    {
        return graficoBarra;
    }

    public void setGraficoBarra(CartesianChartModel graficoBarra)
    {
        this.graficoBarra = graficoBarra;
    }

    public Integer getAnoGrafico()
    {
        return anoGrafico;
    }

    public void setAnoGrafico(Integer anoGrafico)
    {
        this.anoGrafico = anoGrafico;
    }

    public String getValorStr()
    {
        return valorStr;
    }

    public void setValorStr(String valorStr)
    {
        this.valorStr = valorStr;
    }

    public ScheduleModel getEventModel()
    {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel)
    {
        this.eventModel = eventModel;
    }

    public Boolean getAlteracao()
    {
        return alteracao;
    }

    public void setAlteracao(Boolean alteracao)
    {
        this.alteracao = alteracao;
    }
    
}
