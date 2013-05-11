package com.financeiro.sonolucro.web.navegacao.index;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.CartesianChartModel;

import javax.inject.Named;

import com.financeiro.sonolucro.bean.api.MovimentacaoBean;
import com.financeiro.sonolucro.web.navegacao.cartao.CartaoBeanNavegacao;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import org.primefaces.model.ScheduleModel;

@Named(value = "indexNavegacao")
@SessionScoped
public class IndexNavegacao implements Serializable
{

    protected static final String BEAN_NAME = "indexNavegacao";
    private static final long serialVersionUID = 762829502223226084L;
    
    private Integer anoGrafico;
    private String nomeDoUsuario;
    private String ultimoAcesso;
    private Float saldoAtual;
    private Float saldoPrevisto;
    private List<MovimentacaoBean> ultimasMovimentacoes;
    private List<MovimentacaoBean> movimentacoesAVencer;
    private List<CartaoBeanNavegacao> cartoesDeCredito;
    private CartesianChartModel graficoLinha;
    private Date hoje;
    private Boolean permissaoUsuario;

    public String getNomeDoUsuario()
    {
        return nomeDoUsuario;
    }

    public void setNomeDoUsuario(String nomeDoUsuario)
    {
        this.nomeDoUsuario = nomeDoUsuario;
    }

    public String getUltimoAcesso()
    {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso)
    {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Float getSaldoAtual()
    {
        if (saldoAtual == null)
            setSaldoAtual(0.0f);

        return saldoAtual;
    }

    public void setSaldoAtual(Float saldoAtual)
    {
        this.saldoAtual = saldoAtual;
    }

    public Float getSaldoPrevisto()
    {
        return saldoPrevisto;
    }

    public void setSaldoPrevisto(Float saldoPrevisto)
    {
        this.saldoPrevisto = saldoPrevisto;
    }

    public List<MovimentacaoBean> getUltimasMovimentacoes()
    {
        if (ultimasMovimentacoes == null)
            setUltimasMovimentacoes(new ArrayList<MovimentacaoBean>());


        return ultimasMovimentacoes;
    }

    public void setUltimasMovimentacoes(
            List<MovimentacaoBean> ultimasMovimentacoes)
    {
        this.ultimasMovimentacoes = ultimasMovimentacoes;
    }

    public List<MovimentacaoBean> getMovimentacoesAVencer()
    {
        if (movimentacoesAVencer == null)
            setMovimentacoesAVencer(new ArrayList<MovimentacaoBean>());

        return movimentacoesAVencer;
    }

    public void setMovimentacoesAVencer(
            List<MovimentacaoBean> movimentacoesAVencer)
    {
        this.movimentacoesAVencer = movimentacoesAVencer;
    }

    public CartesianChartModel getGraficoLinha()
    {
        if (graficoLinha == null)
            setGraficoLinha(new CartesianChartModel());

        return graficoLinha;
    }

    public void setGraficoLinha(CartesianChartModel graficoLinha)
    {
        this.graficoLinha = graficoLinha;
    }

    public Integer getAnoGrafico()
    {
        return anoGrafico;
    }

    public void setAnoGrafico(Integer anoGrafico)
    {
        this.anoGrafico = anoGrafico;
    }

    public Date getHoje()
    {
        if (hoje == null)
            setHoje(new Date());

        return hoje;
    }

    public void setHoje(Date hoje)
    {
        this.hoje = hoje;
    }

    public List<CartaoBeanNavegacao> getCartoesDeCredito()
    {
        return cartoesDeCredito;
    }

    public void setCartoesDeCredito(List<CartaoBeanNavegacao> cartoesDeCredito)
    {
        this.cartoesDeCredito = cartoesDeCredito;
    }

    public Boolean getPermissaoUsuario()
    {
        return permissaoUsuario;
    }

    public void setPermissaoUsuario(Boolean permissaoUsuario)
    {
        this.permissaoUsuario = permissaoUsuario;
    }
}
