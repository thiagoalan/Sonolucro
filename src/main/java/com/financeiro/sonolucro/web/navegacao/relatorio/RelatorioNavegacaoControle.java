package com.financeiro.sonolucro.web.navegacao.relatorio;

import java.util.HashMap;

import org.primefaces.model.StreamedContent;

import com.financeiro.sonolucro.util.BeanUtil;
import com.financeiro.sonolucro.util.RelatorioUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

@Named(value = "relatorioNavegacaoControle")
@ViewAccessScoped  
public class RelatorioNavegacaoControle implements Serializable
{

    private static final String TELA_RELATORIO = "relatorios";
    private static final String BEAN_NAME = "relatorioNavegacaoControle";
    @Inject
    private RelatorioNavegacao navegacao;
    @Inject
    private BeanUtil beanUtil;
    @Inject
    private Conversation conversation;

    public void processaArquivo()
    {
        StreamedContent arquivo = RelatorioUtil.criaRelatorio(
                geraParametrosDoRelatorio(), "movimentacoes", "usuario_movimentacoes",
                navegacao.getTipoRelatorio());

        navegacao.setArquivo(arquivo);
    }

    public HashMap geraParametrosDoRelatorio()
    {
        HashMap parametros = new HashMap();
        parametros.put("idUsuario", beanUtil.getUsuarioLogado().getId());
        parametros.put("dataInicial", navegacao.getDataInicialMovimentacao());
        parametros.put("dataFinal", navegacao.getDataFinalMovimentacao());

        return parametros;
    }

    public String irParaTelaRelatorio()
    {
        return TELA_RELATORIO;
    }
}
