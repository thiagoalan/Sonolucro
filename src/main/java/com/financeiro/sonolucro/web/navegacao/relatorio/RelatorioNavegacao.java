package com.financeiro.sonolucro.web.navegacao.relatorio;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import org.primefaces.model.StreamedContent;

@Named(value = "relatorioNavegacao")
@ViewAccessScoped  
public class RelatorioNavegacao implements Serializable
{

    private static final String BEAN_NAME = "relatorioNavegacao";
    private StreamedContent arquivo;
    private Date dataInicialMovimentacao;
    private Date dataFinalMovimentacao;
    private Integer tipoRelatorio;
    @Inject
    private Conversation conversation;

    public StreamedContent getArquivo()
    {
        return arquivo;
    }

    public void setArquivo(StreamedContent arquivo)
    {
        this.arquivo = arquivo;
    }

    public Date getDataInicialMovimentacao()
    {
        return dataInicialMovimentacao;
    }

    public void setDataInicialMovimentacao(Date dataInicialMovimentacao)
    {
        this.dataInicialMovimentacao = dataInicialMovimentacao;
    }

    public Date getDataFinalMovimentacao()
    {
        return dataFinalMovimentacao;
    }

    public void setDataFinalMovimentacao(Date dataFinalMovimentacao)
    {
        this.dataFinalMovimentacao = dataFinalMovimentacao;
    }

    public Integer getTipoRelatorio()
    {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(Integer tipoRelatorio)
    {
        this.tipoRelatorio = tipoRelatorio;
    }

}
